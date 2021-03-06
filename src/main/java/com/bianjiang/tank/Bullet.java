package com.bianjiang.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;

    public static final int WIDTH = ResourcesMgr.bulletD.getWidth();

    public static final int HEIGHT = ResourcesMgr.bulletD.getHeight();

    Rectangle rect = new Rectangle();

    private int x, y;

    private Dir dir;

    private boolean living = true;

    private TankFrame tf = null;

    private Group group = Group.BAD;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourcesMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourcesMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourcesMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourcesMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
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

        //更新rect
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    //判断子弹和坦克是否相交
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
        //Rectangle rectangle1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        //Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(new Explode(eX, eY, tf));
        }
    }

    //子弹死亡
    private void die() {
        this.living = false;
    }
}
