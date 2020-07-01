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
            tankU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD = ImageUtil.rotateImage(tankU,180);

            //把子弹图片从硬盘加载到内存
            bulletU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            //把爆炸图片从硬盘加载到内存
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
