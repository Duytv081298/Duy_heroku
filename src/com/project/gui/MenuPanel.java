package com.project.gui;

import com.project.model.Block;
import until.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MenuPanel extends JPanel implements ActionListener {
    private Random rd = new Random();
    private JButton btnOk;
    private JButton btnExit;
    private Image bg = ImageLoader.getImage("Menu.png", getClass());
    private ArrayList<Block> blocks;

    public MenuPanel() {
        blocks = new ArrayList<>();
        setLayout(null);
        initComponents();
    }

    private void initComponents() {

        btnOk = new JButton();
        btnOk.setIcon(new ImageIcon(ImageLoader.getImage("Play.png", getClass())));
        btnOk.setBounds(40, 400, 149, 54);
        add(btnOk);
        btnOk.addActionListener(this);

        btnExit = new JButton();
        btnExit.setIcon(new ImageIcon(ImageLoader.getImage("cancel1.png", getClass())));
        btnExit.setBounds(40, 500, 150, 47);
        add(btnExit);
        btnExit.addActionListener(this);

        for (int j = 0; j <= 4; j++) {
            int point = 1 + rd.nextInt(45);
            Block block = new Block(Block.W_BLOCK * j, 645, point);
            blocks.add(block);
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        super.paintComponent(g);

        for (Block b : blocks
        ) {
            b.draw(g2d);
        }
        g2d.setColor(Color.blue);
        g2d.setFont(new Font("Bernard MT Condensed", Font.BOLD, 70));
        g2d.drawString("Doraemon", 100, 100);
        g2d.drawString("Vs", 200, 200);
        g2d.drawString("Block", 150, 300);

        g2d.drawImage(bg, SnakeFrame.W_FRAME / 3, 350, 300, 300, null);
        g2d.setColor(new Color(193, 140, 0));
        for (int i = 0; i < 4; i++) {
            g2d.fillRoundRect(50 + 15 * i, 715 + i * 15, 380 - 30 * i, 5, 10, 10);


        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnOk)) {
            SnakeFrame frame = new SnakeFrame();
            frame.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose();
        } else {
            System.exit(0);
        }
    }
}
