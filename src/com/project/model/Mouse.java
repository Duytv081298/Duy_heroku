package com.project.model;

import com.project.gui.SnakeFrame;
import until.ImageLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Mouse {
    private int x, y;
    private Image image = ImageLoader.getImage("chuot.png", getClass());


    private Random rd = new Random();

    public int getY() {
        return y;
    }


    public Mouse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }

    public boolean move() {
        if (GameManager.CHECKMOVE > 0) {
            y += 2;
            if (y >= SnakeFrame.H_FRAME) {
                return false;
            }
        }
        return true;
    }

    public void generate(ArrayList<Mouse> arr) {
        if (arr.size() <= 1) {
            int x = 50 + rd.nextInt(SnakeFrame.W_FRAME - 80);
            int y = -500 + rd.nextInt(200);
            Mouse mouse = new Mouse(x, y);
            arr.add(mouse);
        }
    }

    public Rectangle getRect() {
        Rectangle rect = new Rectangle(x + 7, y + 7, 50, 50);
        return rect;
    }
}
