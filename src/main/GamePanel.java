package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile, default size of sprites/map tiles
    //// modern monitors have much higher resolution, so we need to scale up our tiles
    final int scale = 3;
    //// 16 * 3 = 48 pixels
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    //// screen dimensions
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // set player's default position and velocity
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // drawing from this component will be done in an offscreen painting buffer,
        // this can improve the game's rendering performance
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        // make sure panel is 'focused' to receive input
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.00/FPS; // 0.01666 seconds, so drawing 60FPS
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1) {
                // 1 UPDATE: update information such as character position
                update();

                // 2 DRAW: draw the screen with the updated information
                // repaint() is how you call paintComponent
                repaint();

                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // cast g as graphics 2D for additional functionality
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        // dispose this graphics context and release any system resources that it's using
        g2.dispose();
    }
}
