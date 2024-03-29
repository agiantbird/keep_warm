package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_SnowMan extends Entity {
    public NPC_SnowMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("npc/snowman_up_1");
        up2 = setup("npc/snowman_up_2");
        down1 = setup("npc/snowman_down_1");
        down2 = setup("npc/snowman_down_2");
        left1 = setup("npc/snowman_left_1");
        left2 = setup("npc/snowman_left_2");
        right1 = setup("npc/snowman_right_1");
        right2 = setup("npc/snowman_right_2");
    }

    public void setDialogue() {
        dialogues[0] = "...Is it odd to walk in snow when I am snow?";
        dialogues[1] = "...You walk on carbon and you are carbon.";
        dialogues[2] = "It, all of it, is all the same thing,\nlooking at itself.";
        dialogues[3] = "...A squirrel stole my carrot nose.";
    }

    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // generate a number from 1 through 100

            if(i <= 25) {
                direction = "up";
            }

            if(i > 25 && i <= 50) {
                direction = "down";
            }

            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}