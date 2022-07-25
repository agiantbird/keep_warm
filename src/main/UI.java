package main;

import objects.OBJ_PineConeLantern;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80_Bold;
    BufferedImage pineConeLanternImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80_Bold = new Font("Arial", Font.BOLD, 80);
        OBJ_PineConeLantern pineConeLantern = new OBJ_PineConeLantern(gp);
        pineConeLanternImage = pineConeLantern.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.BLACK);
        g2.drawImage(pineConeLanternImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasLantern, 74, 65);

        // MESSAGE
        if(messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize * 5);

            messageCounter += 1;

            // 120 frames = 2 seconds
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
