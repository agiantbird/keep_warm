package objects;

        import main.GamePanel;

        import javax.imageio.ImageIO;
        import java.io.IOException;

public class OBJ_HotSpring extends SuperObject {
    GamePanel gp;

    public OBJ_HotSpring(GamePanel gp) {
        name = "HotSpring";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/hot_spring.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}

