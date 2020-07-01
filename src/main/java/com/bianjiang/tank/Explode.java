package com.bianjiang.tank;

import java.awt.*;

public class Explode {


    public static final int WIDTH = ResourcesMgr.explodes[0].getWidth();

    public static final int HEIGHT = ResourcesMgr.explodes[0].getHeight();

    private int x, y;


    private boolean living = true;

    TankFrame tf = null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(() -> new Audio("audio/explode.wav").play()).run();
    }


    public void paint(Graphics g) {

        g.drawImage(ResourcesMgr.explodes[step++], x, y, null);
        if (step >= ResourcesMgr.explodes.length) {
            tf.explodes.remove(this);
        }
    }


}
