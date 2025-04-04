import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTilesSize = 16;// 16x16 Tile
    final int scale= 3;

    final int tileSize= originalTilesSize * scale; // 48x48 tile
    final int maxScreenCol= 16;
    final int maxScreenRow= 12;
    final int screenWidth= tileSize * maxScreenCol; // 768 Pixel
    final int screenHeight= tileSize * maxScreenRow; // 576 Pixels

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }
    public void startGameThread(){
        gameThread= new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

    }
}
