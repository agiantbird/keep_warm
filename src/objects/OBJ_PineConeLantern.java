package objects;

        import entity.Entity;
        import main.GamePanel;

public class OBJ_PineConeLantern extends Entity {

    public OBJ_PineConeLantern(GamePanel gp) {
        super(gp);

        name = "PineConeLantern";
        down1 = setup("objects/pine_cone_lantern");
    }
}