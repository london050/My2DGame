import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tileManager {

    GamePanel gp;
    tile[] tile;
    int mapTileNum[][];

    public tileManager(GamePanel gp) {

        this.gp = gp;
        tile = new tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/Maps/world01.txt");
    }
    public void loadMap(String filePath) {

        try {
            InputStream is= getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row =0;

            while(col <gp.maxWorldCol && row <gp.maxWorldRow){

                String line = br.readLine();

                while(col <gp.maxWorldCol){

                    String numbers[]= line.split(" ");

                    int num= Integer.parseInt(numbers[col]);

                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e) {

        }



    }
    public void getTileImage() {

        try {

            tile[0] =new tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tile/grass.png"));

            tile[1] =new tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tile/wall.png"));

            tile[2] =new tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tile/water.png"));

            tile[3] =new tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tile/earth.png"));

            tile[4] =new tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tile/tree.png"));

            tile[5] =new tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tile/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D g2){

       int worldCol = 0;
       int worldRow = 0;


       while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

           int tileNum = mapTileNum[worldCol][worldRow];

           int worldX = worldCol * gp.tileSize;
           int worldY = worldRow * gp.tileSize;
           int screenX= worldX - gp.Player.worldX + gp.Player.screenX;
           int screenY= worldY - gp.Player.worldY + gp.Player.screenY;

           g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
           worldCol++;


           if(worldCol ==gp.maxWorldCol) {
               worldCol=0;
               worldRow++;

           }
       }
    }
}
