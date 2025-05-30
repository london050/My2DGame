import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction="down";
        speed=1;

        getImage();
        setDialogue();
    }
    public void getImage(){

        up1 = setup("/NPC/oldman_up_1");
        up2 = setup("/NPC/oldman_up_2");
        down1 = setup("/NPC/oldman_down_1");
        down2 = setup("/NPC/oldman_down_2");
        left1 = setup("/NPC/oldman_left_1");
        left2 = setup("/NPC/oldman_left_2");
        right1 = setup("/NPC/oldman_right_1");
        right2 = setup("/NPC/oldman_right_2");
    }
    public void setDialogue(){

        Dialogues[0]="Hello, lad.";
        Dialogues[1]="So you've come to this island to \nfind the treasure?";
        Dialogues[2]="I used to be a great wizard but now ... \nI'm a bit too old for taking an adventure.";
        Dialogues[3]="Well, good luck on you.";
    }
    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();

            int i = random.nextInt(100)+1; //pick up a number from 1 to 100

            if(i <= 25){
                direction="up";
            }
            else if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    public void speak(){
        super.speak();
    }
}
