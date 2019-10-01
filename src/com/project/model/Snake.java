package com.project.model;

import com.project.gui.SnakeFrame;
import com.project.gui.SnakePanel;
import until.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private int x;
    public static final int W_SNAKE = 60;
    private int y;
    public static final int H_SNAKE = 95;
    private int orient;
    public static final int LEET = 0;
    private int point;
    public static final int RIGHT = 1;
    private Image[] images;
    public static int speed = 3;
    private GameManager manager = new GameManager();


    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        this.point = 5;
        images = new Image[4];
        images[LEET] = ImageLoader.getImage("Doraemon_dau.png", getClass());
        images[RIGHT] = ImageLoader.getImage("Doraemon.png", getClass());
    }


    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.setFont(new Font(null, Font.BOLD, 20));
        g2d.drawString(point + "", x + W_SNAKE / 2 - 10, y - 5);

        if (GameManager.CHECKMOVE == 1) {
            g2d.drawImage(images[GameManager.CHECKMOVE], x, y, W_SNAKE, H_SNAKE, null);
            return;
        }
        g2d.drawImage(images[GameManager.CHECKMOVE], x, y, W_SNAKE, W_SNAKE + 10, null);


    }


    public void move() {
        boolean CheckLeft = manager.SnakeMoveLeft();
        boolean CheckRight = manager.SnakeMoveRight();
        int xR = x;
        switch (orient) {

            case LEET:
                if (CheckLeft == false) {
                    x -= speed;
                    break;
                }
                break;
            case RIGHT:
                if (CheckRight == false) {
                    x += speed;
                    break;
                }
                break;
        }
        if (x <= 0 || x >= SnakeFrame.W_FRAME - 72) {
            x = xR;
        }
    }

    public void chaneOrient(int newOrient) {
        this.orient = newOrient;
    }

    public Rectangle getRect() {
        Rectangle rect = new Rectangle(x, y, W_SNAKE, W_SNAKE);
        return rect;
    }

    public Rectangle getRectUp() {
        Rectangle rect = new Rectangle(x + 5, y, W_SNAKE - 10, 5);
        return rect;
    }

    public Rectangle getRectRight() {
        Rectangle rect = new Rectangle(x + W_SNAKE - 5, y + 5, 5, W_SNAKE - 10);
        return rect;
    }

    public Rectangle getRectLeft() {
        Rectangle rect = new Rectangle(x, y + 5, 5, W_SNAKE - 10);
        return rect;
    }

}
