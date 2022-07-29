package entity;

import main.GamePanel;

public class NPC_SnowMan extends Entity {
    public NPC_SnowMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }

    public void getImage() {
        up1 = setup("npc/snowman_up_1");
        up2 = setup("npc/snowman_up_2");
        down1 = setup("npc/snowman_down_1");
        down2 = setup("npc/snowman_down_1");
        left1 = setup("npc/snowman_left_1");
        left2 = setup("npc/snowman_left_2");
        right1 = setup("npc/snowman_right_1");
        right2 = setup("npc/snowman_right_2");
    }
}