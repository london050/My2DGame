import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJChest extends SuperObject {

    GamePanel gp;

    public OBJChest(GamePanel gp) {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

