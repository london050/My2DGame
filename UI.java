import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica,pursiaB;
    BufferedImage heart_full,heart_half,heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNumber = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/Font/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/Font/Purisa Bold.ttf");
            pursiaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Create HUD Object
        SuperObject heart = new OBJ_Heart(gp);
        heart_full= heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {

        this.g2= g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        //Title State
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //Play State
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        //Pause state
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
            drawPlayerLife();
        }
        //Dialogue State
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
            drawPlayerLife();
        }
    }
    public void drawPlayerLife(){

        gp.Player.life=6;

        int x =gp.tileSize/2;
        int y = gp.tileSize/2;
        int i= 0;

        //Draw max heart
        while(i < gp.Player.maxLife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+=gp.tileSize;
        }
        //Reset
        x =gp.tileSize/2;
        y = gp.tileSize/2;
        i= 0;

        //Draw current life
        while(i<gp.Player.life){
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i < gp.Player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x+=gp.tileSize;
        }

    }



    public void drawTitleScreen() {
        //Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text= "Sprite Quest";
        int x = getXForCenteredText(text);
        int y = gp.tileSize *3;

        //Main Color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);

        //Blue Boy Image
        x= gp.screenWidth/2 -(gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.Player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        g2.setColor(Color.white);
        text = "New Game";
        x = getXForCenteredText(text);
        y+= gp.tileSize*3.5;
        g2.drawString(text,x,y);
        if(commandNumber == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text = "Load Game";
        x = getXForCenteredText(text);
        y+= gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNumber == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text = "Quit";
        x = getXForCenteredText(text);
        y+= gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNumber == 2){
            g2.drawString(">",x-gp.tileSize,y);
        }

    }
    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y= gp.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen() {

        //Window
        int x = gp.tileSize *2;
        int y= gp.tileSize/2;
        int width= gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize *4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x+= gp.tileSize;
        y+= gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y+= 40;
        }


    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c= new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
    public int getXForCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}