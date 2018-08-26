package com.springboot.websocket.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Name: MergeImage
 * @Desc: TODO
 * @User: gxing
 * @Date: 2018-07-26 15:45
 **/
public class MergeImage {

    public static void main(String[] args) {
        BufferedImage img = new BufferedImage(1080, 1920, BufferedImage.TYPE_INT_RGB);//创建图片
        img.setRGB(32,217,120);
        try {
            BufferedImage teamImg = ImageIO.read(new File("d:/image/qrcode_team.PNG"));//读取互联网图片
            BufferedImage logImg = ImageIO.read(new File("d:/image/qrcode_new.PNG"));//读取本地图片
            BufferedImage textImg = ImageIO.read(new File("d:/image/qrcode_text.PNG"));//读取本地图片

            //开启画图
            Graphics g = img.getGraphics();
            g.setColor(Color.decode("#EEAD0E"));
            g.fillRect(0,0,1080,1920);//填充整个屏幕


            g.drawImage(teamImg.getScaledInstance(1080,320, Image.SCALE_DEFAULT), 0, 250, null); // 绘制缩小后的图
            g.drawImage(logImg.getScaledInstance(800,250, Image.SCALE_DEFAULT), 120, 600, null); // 绘制缩小后的图
            g.drawImage(textImg.getScaledInstance(800,250, Image.SCALE_DEFAULT), 120, 1500, null); // 绘制缩小后的图

            g.setFont(new Font("黑体", Font.BOLD, 72));//设置字体
            g.setColor(Color.white);//绘制文字
            g.drawString("最 高", 280, 100);//绘制文字

            g.setFont(new Font("微软雅黑", Font.BOLD, 84));//设置字体
            g.setColor(Color.black);
            g.drawString("3%", 500, 100);//绘制文字

            g.setFont(new Font("黑体", Font.BOLD, 72));//设置字体
            g.setColor(Color.white);//绘制文字
            g.drawString("佣 金", 650, 100);//绘制文字

            g.dispose();
            ImageIO.write(img, "PNG", new File("D:\\image\\new.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}