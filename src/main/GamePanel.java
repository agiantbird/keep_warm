package main;

import entity.Player;
import objects.SuperObject;
import tile.TileManager;

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
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

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

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
        gameState = playState;
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
        if(gameState == playState) {
            player.update();
        }
        if(gameState == pauseState) {
            // nothing for now
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // cast g as graphics 2D for additional functionality
        Graphics2D g2 = (Graphics2D)g;
        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }
        // tile
        tileM.draw(g2);
        // object
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        // player
        player.draw(g2);
        // UI
        ui.draw(g2);

        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long elapsed = drawEnd - drawStart;
            g2.setColor(Color.black);
            g2.drawString("Draw Time: " + elapsed, 10, 400);
            System.out.println("Draw Time: " + elapsed);
        }
        // dispose this graphics context and release any system resources that it's using
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
