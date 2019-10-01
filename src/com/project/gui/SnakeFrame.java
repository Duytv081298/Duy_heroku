package com.project.gui;

import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {
    public static final int W_FRAME = 500;
    public static final int H_FRAME = 810;
    public SnakeFrame() {
        setTitle("Snake Vs Block");
        setSize(W_FRAME,H_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        SnakePanel snakePanel = new SnakePanel();
        add(snakePanel);
    }
}
