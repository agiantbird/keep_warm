package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PineConeLantern extends SuperObject {
    public OBJ_PineConeLantern() {
        name = "PineConeLantern";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/pine_cone_lantern.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
