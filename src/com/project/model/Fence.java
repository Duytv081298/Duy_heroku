package com.project.model;

import com.project.gui.SnakeFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Fence {
    protected int x, y, h_fence, amountFence;

    public static final int W_FENCE = 5;
    private Random rd = new Random();

    public Fence(int x, int y, int h_fence) {
        this.x = x;
        this.y = y;
        this.h_fence = h_fence;
    }

    public Fence(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRoundRect(x, y, W_FENCE, h_fence, 5, 5);
    }

    public boolean move() {
        if (GameManager.CHECKMOVE > 0) {
            y += 2;
            if (y >= SnakeFrame.H_FRAME + h_fence) {
                return false;
            }
        }
        return true;
    }

    public Rectangle getRect() {
        Rectangle rect = new Rectangle(x, y, W_FENCE, h_fence);
        return rect;
    }

    public static boolean checkX(ArrayList<Integer> arr, int x) {
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i) == x) {
                return true;
            }
        }
        return false;
    }

    public void generate(ArrayList<Fence> arr) {
        ArrayList<Integer> intXs = new ArrayList<>();
        if (y <= 10) {
            return;
        }
        amountFence = 0 + rd.nextInt(3);
        for (int i = 0; i < amountFence; i++) {
            int x = 1 + rd.nextInt(4);
            int h_fence = 2 + rd.nextInt(2);
            boolean checkX = checkX(intXs, x);
            if (checkX == true) {
                i--;
            } else {
                intXs.add(x);
                Fence fence = new Fence(Block.W_BLOCK * x, -Block.H_BLOCK * 3, 50 * h_fence);
                arr.add(fence);
            }
        }
    }

}
