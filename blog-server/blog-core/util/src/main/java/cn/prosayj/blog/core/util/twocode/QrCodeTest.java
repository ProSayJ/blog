package cn.prosayj.blog.core.util.twocode;

/**
 * 基础工具类
 *
 * @author yangjian@bubi.cn
 * @date 2020-02-22 下午 04:41
 * @since 1.0.0
 */
public class QrCodeTest {
    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "https://github.com/prosayj";
        // 嵌入二维码的图片路径
        String imgPath = "C:\\a\\4.png";
        // 生成的二维码的路径及名称
        String destPath = "C:\\a\\123.jpg";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }
}
