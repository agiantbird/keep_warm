package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PineConeLantern extends SuperObject {
    GamePanel gp;
    public OBJ_PineConeLantern(GamePanel gp) {
        name = "PineConeLantern";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/pine_cone_lantern.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
