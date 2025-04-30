import javax.swing.*;
        import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTilesSize = 16;// 16x16 Tile
    final int scale= 3;

    public final int tileSize= originalTilesSize * scale; // 48x48 tile
    public final int maxScreenCol= 16;
    public final int maxScreenRow= 12;
    public final int screenWidth= tileSize * maxScreenCol; // 768 Pixel
    public final int screenHeight= tileSize * maxScreenRow; // 576 Pixels

    //World Settings
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth= tileSize * maxWorldCol;
    public final int worldHeight= tileSize * maxWorldRow;

    //FPS
    int FPS= 60;

    tileManager tileM= new tileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player Player = new Player(this,keyH);



    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void startGameThread(){
        gameThread= new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval= 1000000000/FPS;
        double delta= 0;
        long lastTime= System.nanoTime();
        long currentTime= 0;
        long timer= 0;
        int drawCount= 0;

        while(gameThread != null){

            currentTime= System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer = (currentTime - lastTime);
            lastTime= currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount= 0;
                timer= 0;
            }
        }
    }
    public void update(){

        Player.update();

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        Player.draw(g2);

        g2.dispose();
    }
}
