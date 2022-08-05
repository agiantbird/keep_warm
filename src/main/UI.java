package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
//    Font arial_40, arial_80_Bold;
    Font maruMonica, purisaB;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;


    public UI(GamePanel gp) {
        this.gp = gp;
        // arial_40 = new Font("Arial", Font.PLAIN, 40);
        // arial_80_Bold = new Font("Arial", Font.BOLD, 80);
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa_Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        // g2.setFont(arial_40);
        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);
        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            // Do playState stuff later
        }
        // PAUSE STATUE
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {
        // BACKGROUND COLOR
//        g2.setColor(new Color(0, 54, 27));
        Color color1 = new Color(68, 17, 18);
        Color color2 = new Color(0, 5, 26);
        GradientPaint redToBlack = new GradientPaint(568, 0, color1,
                568, 476, color2);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Keep Warm";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;
        // SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);
        // MAIN TEXT COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // MAIN CHARACTER IMAGE
        x = (gp.screenWidth / 2) - gp.tileSize;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        int dialogueX = x + gp.tileSize;
        int dialogueY = y + gp.tileSize;

        // break up long dialogue to fit dialogue window
        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, dialogueX, dialogueY);
            // move a row's worth of pixels down for next line of dialogue
            dialogueY += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        // WINDOW BACKGROUND
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        // WINDOW OUTLINE
        c = new Color(255, 255, 255);
        g2.setColor(c);
        int subWindowOutlineWidth = 5;
        g2.setStroke(new BasicStroke(subWindowOutlineWidth));
        g2.drawRoundRect(
                x + subWindowOutlineWidth,
                y + subWindowOutlineWidth,
                width - (2 * subWindowOutlineWidth),
                height - (2 * subWindowOutlineWidth),
                25,
                25

        );
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = ((gp.screenWidth/2) - (length/2));
        return x;
    }
}
