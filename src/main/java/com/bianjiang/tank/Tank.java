package com.bianjiang.tank;

import java.awt.*;

//封装坦克属性成一个坦克的对象
public class Tank {

    private int x, y;

    private Dir dir;

    private static final int SPEED = 10;

    public static final int WIDTH = ResourcesMgr.tankD.getWidth();

    public static final int HEIGHT = ResourcesMgr.tankD.getHeight();

    private boolean moving = false;

    private TankFrame tf = null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {

        switch (dir) {
            case LEFT:
                g.drawImage(ResourcesMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourcesMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourcesMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourcesMgr.tankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 + Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 + Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
    }
}
