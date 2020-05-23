package cn.prosayj.blog.core.util.easycaptchautil;

import java.awt.*;
import java.io.*;


/**
 * @author yangjian@bubi.cn
 * @date 2020-02-22 下午 04:41
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) throws IOException {
        getGif();
        getPng();
        getChineseGif();
        getChinesPng();
    }

    private static void getChinesPng() throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream(new File("C:/a/4.png"));
        // 三个参数分别为宽、高、位数
        ChineseCaptcha chineseCaptcha = new ChineseCaptcha(130, 48, 4);
        // 设置字体。有默认字体，可以不用设置
        chineseCaptcha.setFont(new Font("楷体", Font.PLAIN, 28));
        // 生成的验证码
        String code = chineseCaptcha.text();
        System.out.println(code);
        // 输出图片流
        chineseCaptcha.out(outputStream);
    }

    private static void getChineseGif() throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream(new File("C:/a/3.gif"));
        // 三个参数分别为宽、高、位数
        ChineseGifCaptcha chineseGifCaptcha = new ChineseGifCaptcha(130, 48, 4);
        // 设置字体。有默认字体，可以不用设置
        chineseGifCaptcha.setFont(new Font("楷体", Font.PLAIN, 28));
        // 生成的验证码
        String code = chineseGifCaptcha.text();
        System.out.println(code);
        // 输出图片流
        chineseGifCaptcha.out(outputStream);
    }

    private static void getPng() throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream(new File("C:/a/2.png"));
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        // 生成的验证码
        String code = specCaptcha.text();
        System.out.println(code);
        // 输出图片流
        specCaptcha.out(outputStream);
    }

    private static void getGif() throws IOException {

        File file = new File("C:" + "/a/1.gif");
        if (!file.exists()) {
            new File("C:" + "/a").mkdirs();
            boolean newFile = file.createNewFile();

        }


        OutputStream outputStream = new FileOutputStream(file);
        // 三个参数分别为宽、高、位数
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 5);
        // 设置字体
        gifCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        gifCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        // 生成的验证码
        String code = gifCaptcha.text();
        System.out.println(code);
        // 输出图片流
        gifCaptcha.out(outputStream);

    }
}
