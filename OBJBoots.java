import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class OBJBoots extends SuperObject {

    GamePanel gp;

    public OBJBoots(GamePanel gp) {
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/boots.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
