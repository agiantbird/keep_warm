package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_HotSpring extends Entity {

    public OBJ_HotSpring(GamePanel gp) {
        super(gp);

        name = "HotSpring";
        down1 = setup("objects/hot_spring");
        collision = true;
        direction = "down";

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}

