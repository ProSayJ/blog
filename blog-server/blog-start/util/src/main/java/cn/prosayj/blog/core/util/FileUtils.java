package cn.prosayj.blog.core.util;

import cn.prosayj.blog.core.common.constants.constant.SysConstants;
import org.apache.commons.lang.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


/**
 * 文件IO操作工具类
 * <p>
 * URL resource = this.getClass().getClassLoader().getResource(fireName);
 * URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
 * <p>
 * 为了避免在项目中加载不到本项目中静态资源文件的BUG发生.
 * 调用静态资源的classLoader最好用Thread.currentThread().getContextClassLoader()方法来获取，
 * 因为一般同一个项目中java代码和其静态资源文件都是同一个classLoader来加载的，
 * 以此确保通过此classLoader也能加载到本项目中的资源文件。
 *
 * @author yangjian@bubi.cn
 * @date 2020-05-10 下午 09:21
 * @since 1.0.0
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 上传文件
     *
     * @param file      文件流
     * @param srcImgDes 文件目录
     * @param fileName  文件名称
     */
    public static void transferImg(MultipartFile file, String srcImgDes, String fileName) {
        //创建Resources下面的静态资源目录
        File srcImgDesPath = new File(srcImgDes);
        if (!srcImgDesPath.exists() || !srcImgDesPath.isDirectory()) {
            if (srcImgDesPath.mkdirs()) {
                logger.error("创建目录失败：目录路径：{}", srcImgDesPath);
            }
        }
        //创建Resources下面的静态资源目录中待上传的文件
        File targetImgDes = new File(srcImgDesPath.getAbsolutePath(), fileName);
        if (!targetImgDes.exists()) {
            try {
                targetImgDes.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //保存文件到Resources下面的静态资源目录中
        try {
            file.transferTo(targetImgDes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 输入流转字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] inputStream2ByteArray(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                baos.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            baos.flush();
            return baos.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    /**
     * 图片到byte数组
     *
     * @param path 图片路径
     * @return
     */
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    /**
     * byte数组到图片
     *
     * @param data              图片二进制文件
     * @param fullPathAndSuffix 图片全路径(有名称有后缀)
     */


    /**
     * @param data     图片二进制文件
     * @param path     图片存储绝对路径
     * @param fileName 图片名称(有后缀)
     */
    public static void byte2image(byte[] data, String path, String fileName) {
        logger.info("二进制2图片,图片路径：{}，图片名称：{}", path, fileName);
        try {
            File fdir = new File(path);
            if (!fdir.isDirectory() || !fdir.exists()) {
                fdir.mkdirs();
            }
            File emptyFile = new File(fdir, fileName);
            FileImageOutputStream imageOutput = new FileImageOutputStream(emptyFile);
            imageOutput.write(data, SysConstants.ZERO, data.length);
            imageOutput.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * byte数组到16进制字符串
     *
     * @param data
     * @return
     */
    public static String byte2string(byte[] data) {
        if (data == null || data.length <= 1) {
            return "0x";
        }
        if (data.length > 200000) {
            return "0x";
        }
        StringBuffer sb = new StringBuffer();
        int buf[] = new int[data.length];
        //byte数组转化成十进制
        for (int k = 0; k < data.length; k++) {
            buf[k] = data[k] < 0 ? (data[k] + 256) : (data[k]);
        }
        //十进制转化成十六进制
        for (int k = 0; k < buf.length; k++) {
            if (buf[k] < 16) {
                sb.append("0" + Integer.toHexString(buf[k]));
            } else {
                sb.append(Integer.toHexString(buf[k]));
            }
        }
        return "0x" + sb.toString().toUpperCase();
    }

    /**
     * 文件上传到項目的类路径
     *
     * @param fileMultipart
     * @param fileName
     * @throws IOException
     */
    public static void upload2ClassPath(MultipartFile fileMultipart, String fileName) throws IOException {
        //获取根目录
        File classpath = new File(ResourceUtils.getURL(SysConstants.CLASSPATH).getPath());
        if (!classpath.exists()) {
            classpath = new File(SysConstants.ENPTY_STRING);
        }
        //获取根目录的绝对路径D:\workspace\git\springbootstudy\blog\target\classes
        String absoluteClassPath = classpath.getAbsolutePath();

        //处理路径指定到Resources下面的静态资源位置：D:\workspace\git\springbootstudy\blog/src/main/resources/static/images/upload
        String srcImgDes = new StringBuffer()
                .append(absoluteClassPath.substring(SysConstants.ZERO, absoluteClassPath.indexOf(SysConstants.TARGET)))
                .append(SysConstants.RESOURCE_PATH).toString();

        FileUtils.transferImg(fileMultipart, srcImgDes, fileName);

        //在target下新建文件目录
        File classImgDesPath = new File(absoluteClassPath, SysConstants.IMG_SRC);
        if (!classImgDesPath.exists() || !classImgDesPath.isDirectory()) {
            classImgDesPath.mkdirs();
        }

        FileUtils.transferImg(fileMultipart, classImgDesPath.getAbsolutePath(), fileName);
    }

    /**
     * 备份hblog的所有博客上传的图片文件
     *
     * @param targetImgPath 目标根目录
     * @throws FileNotFoundException
     */
    public static void copayBolgImgs2Forder(String targetImgPath) throws IOException {
        //获取源文件夹
        final StrBuilder srcPathStr = new StrBuilder(System.getProperties().getProperty("user.home")).append("/halo/upload/");
        final File srcPath = new File(srcPathStr.toString());
        final File[] files = srcPath.listFiles();
        // 遍历文件
        if (null != files) {
            File classpath = new File(ResourceUtils.getURL(SysConstants.CLASSPATH).getPath());
            if (!classpath.exists()) {
                classpath = new File(SysConstants.ENPTY_STRING);
            }
            //生成目标文件根目录：
            String absoluteClassPath = classpath.getAbsolutePath();
            String desImgFatherDes = new StringBuffer()
                    .append(absoluteClassPath.substring(SysConstants.ZERO, absoluteClassPath.indexOf(SysConstants.TARGET)))
                    .append(SysConstants.RESOURCE_BACKUP_PATH).append(SysConstants.RESOURCE_BACKUP_BLOG_IMG).toString();
            for (File file : files) {
                //拷贝文件
                copyAllFiles(file, desImgFatherDes);
            }
        }
    }


    /**
     * 递归copy获取文件加下面所有的文件到指定的目录
     *
     * @param file            待拷贝的文件(夹)
     * @param desImgFatherDes 目标文件夹
     */
    private static void copyAllFiles(File file, String desImgFatherDes) throws IOException {
        if (file == null) {
            return;
        }
        if (file.isFile()) {
            String sourcePath = file.getAbsolutePath();
            String subPath = sourcePath.substring(sourcePath.indexOf("\\halo"), sourcePath.length());

            copyFile(file, new File(desImgFatherDes + subPath));
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileInner : files) {
                copyAllFiles(fileInner, desImgFatherDes);
            }
        }
    }

    /**
     * 复制文件,使用nio以提高性能
     *
     * @param src  - 源文件
     * @param dest - 目标文件
     */
    public static void copyFile(File src, File dest) throws IOException {
        if (!src.exists()) {
            throw new FileNotFoundException(src.getAbsolutePath() + "==>找不到被拷贝的源文件");
        }
        if (!dest.exists()) {
            //处理目标目录
            String absolutePath = dest.getAbsolutePath();
            String fileNameAndSuffix = absolutePath.substring(absolutePath.lastIndexOf("\\") + 1, absolutePath.length());
            String parentPath = absolutePath.substring(0, absolutePath.lastIndexOf("\\"));

            System.out.println(absolutePath);
            System.out.println(fileNameAndSuffix);
            System.out.println(parentPath);

            //创建文件所在的目录
            File desImgDesPath = new File(parentPath);
            if (!desImgDesPath.exists() || !desImgDesPath.isDirectory()) {
                desImgDesPath.mkdirs();
            }
            //创建文件
            File targetImgDes = new File(desImgDesPath.getAbsolutePath(), fileNameAndSuffix);
            if (!targetImgDes.exists()) {
                targetImgDes.createNewFile();
            }
            try {
                dest.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel in = null;
            FileChannel out = null;
            try {
                fis = new FileInputStream(src);
                fos = new FileOutputStream(dest);
                // 得到对应的文件通道
                in = fis.getChannel();
                // 得到对应的文件通道
                out = fos.getChannel();
                // 连接两个通道，并且从in通道读取，然后写入out通道
                in.transferTo(0, in.size(), out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in.isOpen()) {
                    try {
                        fis.close();
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out.isOpen()) {
                    try {
                        fos.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getText(String fileName) {
        //URL resource = this.getClass().getClassLoader().getResource(fileName);
        //String file = this.getClass().getResource(fileName).getFile();
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(resource.getPath()))));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

//        updateFileName("E:\\netty\\123");
        //updateFileName01("E:\\netty\\我的解析任务1908141149");
        // updateFileName02("E:\\netty\\我的解析任务1908141148\\05");

        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println(resource.getPath());
    }


    public static void updateFileName01(String fireDir) {
        File fileDir = new File(fireDir);
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
                String newFileFullPath = file.getParent() + "\\" + file.getName().substring(file.getName().lastIndexOf("】") + 1);
                System.out.println(newFileFullPath);
                file.renameTo(new File(newFileFullPath));

            }
        }
    }

    public static void updateFileName02(String fireDir) {
        File fileDir = new File(fireDir);
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            for (File file : files) {
                String fileNo = file.getName().substring(file.getName().lastIndexOf("(五)") + 4, file.getName().lastIndexOf("[高清版]"));
                String newFileFullPath = file.getParent() + "\\" + fileNo + "_" + file.getName().substring(file.getName().lastIndexOf("】") + 1);
                System.out.println(newFileFullPath);
                file.renameTo(new File(newFileFullPath));

            }
        }
    }


    /**
     * 基于ResponseEntity的实现的局限性还是很大，从代码中可以看出这种下载方式是一种一次性读取的下载方式，
     * 在文件较大的时候会直接抛出内存溢出（我自己亲测一个1.8G的文件在执行下载操作的时候直接抛出了内存溢出）
     *
     * @param fileName
     * @param isFromTemplateForder
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> downloadFile(String fileName, boolean isFromTemplateForder) throws IOException {
        String trueFileName = fileName;
        if (isFromTemplateForder) {
            fileName = "templates/" + fileName;
        }
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
        if (resource != null) {
            try (InputStream is = new FileInputStream(new File(resource.getPath()))) {
                byte[] body = new byte[is.available()];
                is.read(body);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", URLEncoder.encode(trueFileName, "UTF-8"));
                return new ResponseEntity<>(body, headers, HttpStatus.OK);
            }
        }
        return null;
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArrayBigFile(String filename) throws IOException {
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * NIO
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArrayNIO(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
