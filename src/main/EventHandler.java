package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle noKeyInputEventRect;
    Rectangle keyInputEventRect;
    int noKeyInputEventRectDefaultX, noKeyInputEventRectDefaultY;
    int keyInputEventRectDefaultX, keyInputEventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        this.noKeyInputEventRect = new Rectangle();
        noKeyInputEventRect.x = 23;
        noKeyInputEventRect.y = 23;
        noKeyInputEventRect.width = 2;
        noKeyInputEventRect.height = 2;
        noKeyInputEventRectDefaultX = noKeyInputEventRect.x;
        noKeyInputEventRectDefaultY = noKeyInputEventRect.y;

        this.keyInputEventRect = new Rectangle();
        keyInputEventRect.x = 23;
        keyInputEventRect.y = 23;
        keyInputEventRect.width = 48;
        keyInputEventRect.height = 48;
        keyInputEventRectDefaultX = keyInputEventRect.x;
        keyInputEventRectDefaultY = keyInputEventRect.y;

    }

    public void checkEvent() {
        if(noKeyInputEventHit(24, 21, "right")) { sharpIce(gp.dialogueState); }
        if(keyInputEventHit(25, 21, "any")) { healingTree(gp.dialogueState); }
        if(keyInputEventHit(25, 20, "down")) { healingTree(gp.dialogueState); }
    }

    public boolean noKeyInputEventHit(int eventCol, int eventRow, String reqDirection) {
        boolean noKeyInputEventHit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        noKeyInputEventRect.x = eventCol * gp.tileSize + noKeyInputEventRect.x;
        noKeyInputEventRect.y = eventRow * gp.tileSize + noKeyInputEventRect.y;

        if(gp.player.solidArea.intersects(noKeyInputEventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                noKeyInputEventHit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        noKeyInputEventRect.x = noKeyInputEventRectDefaultX;
        noKeyInputEventRect.y = noKeyInputEventRectDefaultY;

        return noKeyInputEventHit;
    }

    public boolean keyInputEventHit(int eventCol, int eventRow, String reqDirection) {
        boolean keyInputEventHit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        keyInputEventRect.x = eventCol * gp.tileSize + keyInputEventRect.x;
        keyInputEventRect.y = eventRow * gp.tileSize + keyInputEventRect.y;

        if(gp.player.solidArea.intersects(keyInputEventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                keyInputEventHit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        keyInputEventRect.x = keyInputEventRectDefaultX;
        keyInputEventRect.y = keyInputEventRectDefaultY;

        return keyInputEventHit;
    }

    public void sharpIce(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You step on sharp ice!";
        gp.player.life -= 1;
    }

    public void healingTree(int gameState) {
        if(gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You look at this tree. \n...it heals you for some reason?";
            gp.player.life = gp.player.maxLife;
        }
    }
}
















