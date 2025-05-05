import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJDoor extends SuperObject{

    GamePanel gp;

public OBJDoor(GamePanel gp) {

    name = "Door";
    try {
        image = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));
        uTool.scaleImage(image, gp.tileSize, gp.tileSize);

    }catch(IOException e){
        e.printStackTrace();

        }
    collision= true;
    }
}


