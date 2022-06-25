package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Keep Warm ⛄");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // .pack causes window to be sized to fit preferred size
        // and layouts of its subcomponents (gamePanel, in this case)
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
