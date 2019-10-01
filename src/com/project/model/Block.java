package com.project.model;

import com.project.gui.SnakeFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Block {
    protected int x, y;
    public static final int W_BLOCK = SnakeFrame.W_FRAME / 5 - 3;
    public static final int H_BLOCK = SnakeFrame.H_FRAME / 12;
    protected int point;
    public static int amountBlock;
    private Random rd = new Random();

    public static final Color COLORS[] = {new Color(0, 245, 255), new Color(0, 255, 127), new Color(255, 193, 193),
            new Color(255, 106, 106), new Color(255, 127, 36), new Color(255, 64, 64),
            new Color(255, 174, 185), new Color(255, 20, 147), Color.red};
    public static final int intX[] = {0, W_BLOCK, W_BLOCK * 2, W_BLOCK * 3, W_BLOCK * 4};

    public Block(int x, int y, int point) {
        this.x = x;
        this.y = y;
        this.point = point;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPoint() {
        return point;
    }

    public static boolean checkPoint(ArrayList<Integer> arr, int point) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) == point) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics2D g2d) {
        if (point <= 10) {
            g2d.setColor(COLORS[0]);
        } else if (point <= 15) {
            g2d.setColor(COLORS[1]);
        } else if (point <= 20) {
            g2d.setColor(COLORS[2]);
        } else if (point <= 25) {
            g2d.setColor(COLORS[3]);
        } else if (point <= 30) {
            g2d.setColor(COLORS[4]);
        } else if (point <= 35) {
            g2d.setColor(COLORS[5]);
        } else if (point <= 40) {
            g2d.setColor(COLORS[6]);
        } else if (point <= 45) {
            g2d.setColor(COLORS[7]);
        } else if (point <= 50) {
            g2d.setColor(COLORS[8]);
        }
        g2d.fillRoundRect(x, y, W_BLOCK, H_BLOCK, 25, 25);
        g2d.setColor(Color.black);

        g2d.setFont(new Font(null, Font.BOLD, 26));
        g2d.drawString(point + "", x + W_BLOCK / 2 - 15, y + H_BLOCK / 3 * 2 - 1);
    }


    public boolean move() {
        if (GameManager.CHECKMOVE > 0) {
            y += 2;
            if (y >= SnakeFrame.H_FRAME + H_BLOCK) {
                return false;
            }
        }
        return true;
    }

    public void generate(ArrayList<Block> arr) {
        ArrayList<Integer> intXs = new ArrayList<>();
        int kc[] = {50, 150, 125, 200, 175};
        int kct = rd.nextInt(5);
        if (y <= 10) {
            return;
        }
        amountBlock = 3 + rd.nextInt(3);
        for (int j = 0; j < amountBlock; j++) {
            point = 1 + rd.nextInt(50);
            int intX = rd.nextInt(5);
            boolean checkPoint = Block.checkPoint(intXs, intX);
            if (checkPoint == true) {
                j--;
            } else {
                intXs.add(intX);
                Block block = new Block(W_BLOCK * intX, -H_BLOCK * 4, point);
                arr.add(block);
            }
        }
    }

    public Rectangle getRectDown() {
        Rectangle rect = new Rectangle(x + 5, y + H_BLOCK - 5, W_BLOCK - 10, 5);
        return rect;
    }

    public Rectangle getRectRight() {
        Rectangle rect = new Rectangle(x + W_BLOCK - 5, y + 5, 5, H_BLOCK - 10);
        return rect;
    }

    public Rectangle getRectLeft() {
        Rectangle rect = new Rectangle(x, y + 5, 5, H_BLOCK - 10);
        return rect;
    }


}
