package main;

import javax.swing.*;

public class gameMain {
    public static void main(String[] args){
        // JFrame for window
        JFrame window = new JFrame();

        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setTitle("2D Hra");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
