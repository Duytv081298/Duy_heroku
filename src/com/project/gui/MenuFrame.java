package com.project.gui;

import javax.swing.*;

public class MenuFrame extends JFrame {
    public MenuFrame() {
        setSize(SnakeFrame.W_FRAME, SnakeFrame.H_FRAME);
        setTitle("Snake Vs Block");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MenuPanel panel = new MenuPanel();
        add(panel);
    }
}
