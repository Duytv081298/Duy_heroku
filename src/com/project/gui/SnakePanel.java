package com.project.gui;

import com.project.model.GameManager;
import com.project.model.Snake;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class SnakePanel extends JPanel implements Runnable, KeyListener {
    private ArrayList<Integer> total_score = new ArrayList<>();
    public static int TOTAL_SCORE = 0;

    private int sleep;
    private GameManager manager = new GameManager();
    private boolean[] flags = new boolean[256];

    public SnakePanel() {
        setBackground(Color.gray);
        setLayout(null);
        manager.initGame();
        setFocusable(true);
        addKeyListener(this);

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        super.paintComponent(g);
        manager.draw(g2d);
        draw(g2d);

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.setFont(new Font(null, Font.BOLD, 30));
        g2d.drawString(TOTAL_SCORE + "", SnakeFrame.W_FRAME - 100, 50);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (flags[keyEvent.getKeyCode()] == true) {
            flags[keyEvent.getKeyCode()] = false;
        } else {
            flags[keyEvent.getKeyCode()] = true;
        }

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        flags[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        flags[keyEvent.getKeyCode()] = false;
    }

    private void setSleep() {

        if (TOTAL_SCORE < 50) {
            sleep = 9;
        } else if (TOTAL_SCORE < 150) {
            sleep = 8;
        } else if (TOTAL_SCORE < 250) {
            sleep = 7;
        } else if (TOTAL_SCORE < 500) {
            sleep = 6;
            Snake.speed = 2;
        } else if (TOTAL_SCORE < 750) {
            sleep = 5;
        } else if (TOTAL_SCORE < 850) {
            sleep = 4;
            Snake.speed = 1;
        } else {
            sleep = 3;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (flags[KeyEvent.VK_LEFT]) {
                manager.snakeMove((Snake.LEET));
            } else if (flags[KeyEvent.VK_RIGHT]) {
                manager.snakeMove((Snake.RIGHT));
            } else if (flags[KeyEvent.VK_SPACE]) {
                manager.setCHECKMOVE(0);
                manager.clip.stop();

            }
            manager.AI();

            repaint();
            if (manager.CHECKDIE == true) {
                if(total_score.size() == 0){
                    end("Điểm Cao Nhất", TOTAL_SCORE);
                    total_score.add(TOTAL_SCORE);
                }else {
                    Collections.sort(total_score);
                    if (TOTAL_SCORE >= total_score.get(total_score.size()-1)){
                        end("Điểm Cao Nhất", TOTAL_SCORE);
                        total_score.add(TOTAL_SCORE);
                    }else if(TOTAL_SCORE <= total_score.get(0)){
                        end("Bạn Người Tệ Hại", TOTAL_SCORE );
                        total_score.add(TOTAL_SCORE);
                    }else {
                        end("Điểm Của Bạn Là", TOTAL_SCORE);
                        total_score.add(TOTAL_SCORE);
                    }
                }
                manager.clip.stop();
                int result = JOptionPane.showConfirmDialog(null, "DO you wan to replay", "Game over",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    flags = new boolean[256];
                    TOTAL_SCORE = 0;
                    manager.initGame();

                } else {
                    MenuFrame frame = new MenuFrame();
                    frame.setVisible(true);
                    SwingUtilities.getWindowAncestor(this).dispose();
                    return;
                }
            }
            setSleep();
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    public void end(String a , int b){
        Graphics g = getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setColor(Color.white);
        g2d.setFont(new Font(null, Font.BOLD, 35));
        g2d.drawString(a, 120, 150);
        g2d.drawString(b +"", 230, 210);
    }


}
