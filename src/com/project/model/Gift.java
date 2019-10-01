package com.project.model;

import com.project.gui.SnakeFrame;
import until.ImageLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Gift {
    private int x, y, point;
    private int W_Gift = 35;
    private Image image = ImageLoader.getImage("Banh.png", getClass());


    private Random rd = new Random();

    public int getY() {
        return y;
    }

    public int getPoint() {
        return point;
    }

    public Gift(int x, int y, int point) {
        this.x = x;
        this.y = y;
        this.point = point;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, W_Gift, W_Gift, null);

        g2d.setColor(Color.white);
        g2d.setFont(new Font(null, Font.BOLD, 15));
        g2d.drawString(point + "", x + W_Gift / 2 - 5, y + W_Gift / 2 + 2);
    }

    public boolean move() {
        if (GameManager.CHECKMOVE > 0) {
            y += 2;
            if (y >= SnakeFrame.H_FRAME + W_Gift) {
                return false;
            }
        }
        return true;
    }

    public boolean moveAuto() {
        y += 2;
        if (y >= SnakeFrame.H_FRAME + W_Gift) {
            return false;
        }
        return true;
    }

    public void generate(ArrayList<Gift> arr) {
        if (arr.get(arr.size() - 1).getY() <= 350) {
            return;
        }
        for (int i = 0; i < 2; i++) {
            int x = 50 + rd.nextInt(SnakeFrame.W_FRAME - 80);
            int y = -200 + rd.nextInt(200);
            point = 1 + rd.nextInt(11);
            Gift gift = new Gift(x, y, point);
            arr.add(gift);
        }
    }

    public Rectangle getRect() {
        Rectangle rect = new Rectangle(x + 7, y + 7, W_Gift - 14, W_Gift - 14);
        return rect;
    }
}
