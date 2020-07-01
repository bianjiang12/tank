package com.bianjiang.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourcesMgr {

    public static BufferedImage tankL, tankU, tankR, tankD;

    public static BufferedImage bulletL, bulletU, bulletR, bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            //把坦克图片从硬盘加载到内存
            tankL = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));

            //把子弹图片从硬盘加载到内存
            bulletL = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            //把爆炸图片从硬盘加载到内存
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
