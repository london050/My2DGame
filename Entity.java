import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreasDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String Dialogues[]= new String[20];
    int dialogueIndex = 0;

    //Character Status
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
    }
    public void speak(){

        if(Dialogues[dialogueIndex]== null){
            dialogueIndex =0;
        }
        gp.ui.currentDialogue= Dialogues[dialogueIndex];
        dialogueIndex++;
        switch(gp.Player.direction){
            case "up":
                direction="down";
                break;
            case "down":
                direction="up";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;

        }

    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkPlayer(this);

        if(collisionOn == false){

            switch(direction){
                case "up":
                    worldY -=speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        int screenX = worldX - gp.Player.worldX + gp.Player.screenX;
        int screenY = worldY - gp.Player.worldY + gp.Player.screenY;

        if (worldX + gp.tileSize > gp.Player.worldX - gp.Player.screenX &&
                worldX - gp.tileSize < gp.Player.worldX + gp.Player.screenX &&
                worldY + gp.tileSize > gp.Player.worldY - gp.Player.screenY &&
                worldY - gp.tileSize < gp.Player.worldY + gp.Player.screenY) {

            switch(direction){
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                    break;

            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}


