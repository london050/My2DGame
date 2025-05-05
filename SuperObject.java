import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreasDefaultY = 0;
    public UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.Player.worldX + gp.Player.screenX;
        int screenY = worldY - gp.Player.worldY + gp.Player.screenY;

        if (worldX + gp.tileSize > gp.Player.worldX - gp.Player.screenX &&
                worldX - gp.tileSize < gp.Player.worldX + gp.Player.screenX &&
                worldY + gp.tileSize > gp.Player.worldY - gp.Player.screenY &&
                worldY - gp.tileSize < gp.Player.worldY + gp.Player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}