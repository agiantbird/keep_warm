package objects;

        import javax.imageio.ImageIO;
        import java.io.IOException;

public class OBJ_HotSpring extends SuperObject {
    public OBJ_HotSpring() {
        name = "HotSpring";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/hot_spring.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}

