package com.bianjiang.tank;

import java.awt.*;
import java.util.Random;

//封装坦克属性成一个坦克的对象
public class Tank {

    private int x, y;

    private Dir dir;

    private static final int SPEED = 2;

    public static final int WIDTH = ResourcesMgr.tankD.getWidth();

    public static final int HEIGHT = ResourcesMgr.tankD.getHeight();

    private Random random = new Random();

    private boolean moving = true;

    private boolean living = true;

    private Group group = Group.BAD;

    private TankFrame tf = null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {

        //如果坦克死亡的话，移除list里面的坦克
        if (!living) tf.tanks.remove(this);

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
        if (random.nextInt(10) > 8) {
            this.fire();
        }
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 6 + Bullet.WIDTH / 6;
        int bY = this.y + Tank.HEIGHT / 6 + Bullet.HEIGHT / 6;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
        new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    //坦克死亡
    public void die() {
        this.living = false;
    }
}
