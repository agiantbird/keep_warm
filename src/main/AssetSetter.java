package main;

import objects.OBJ_HotSpring;
import objects.OBJ_PineConeLantern;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_PineConeLantern(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_PineConeLantern(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_HotSpring(gp);
        gp.obj[2].worldX = 31 * gp.tileSize;
        gp.obj[2].worldY = 15 * gp.tileSize;
    }
}
