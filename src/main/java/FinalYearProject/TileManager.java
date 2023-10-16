package FinalYearProject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Manages the tiles for the floor of the game, setting them to grass, walls or stairs
 * 
 * @author zjac122
 */
public class TileManager {
  
  private GamePanel gp;
  public BufferedImage GrassTile, WallTile, StairsTile;
  private char[][] map;
  private int[] stair;
  boolean debugPath = false;
  private int tilesize;
  
  /**
   * The constructor
   * @param gp the gamepanel
   */
  public TileManager(GamePanel gp) {
    this.gp=gp;
    this.stair = new int[2];
    this.tilesize=gp.getTilesize();
  }
  
  /**
   * Gets the images for the tiles in the resources folder.
   * 
   * @catch Catches IOexceptions caused when the tile model does not exist.
   */
  public void getImage() {
    try {
      GrassTile=ImageIO.read(getClass().getResourceAsStream("/Resources/GrassTile.png"));
      WallTile=ImageIO.read(getClass().getResourceAsStream("/Resources/WallTile.png"));
      StairsTile=ImageIO.read(getClass().getResourceAsStream("/Resources/StairsTile.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  public int[] getStair() {
    return this.stair;
  }
  
  public void draw(Graphics2D tile, char[][] map) {
    int column = 0;
    int row = 0;
    BufferedImage currenttile;
    while (column <gp.getWorldSizeCol() && row  < gp.getWorldSizeRow()) {
      int x = column * tilesize;
      int y = row * tilesize;
      int xScreen = x - (gp.playerInfo.getXScreen() + gp.playerInfo.getXAxis());
      int yScreen = y - (gp.playerInfo.getYScreen() + gp.playerInfo.getYAxis());
      if (map[column][row] == 'G' || map[column][row]== 'P') {
        currenttile = GrassTile;
      } else if (map[column][row] == 'S') {
        stair[0]=column;
        stair[1]=row;
        currenttile = StairsTile;
      } else {
        currenttile = WallTile;
      }
      tile.drawImage(currenttile, xScreen, yScreen, tilesize, tilesize, null);
      column++;
      if (column == gp.getWorldSizeCol()) {
        column = 0;
        row ++;
      }
      tile.setColor(Color.RED);
      if (debugPath == true) {
        for (int i = 0; i < gp.enemies.length; i++) {
          for (int j = 0; j < gp.enemies[i].getPathfinder().pathNodes.size(); j++) {
            x = gp.enemies[i].getPathfinder().pathNodes.get(j).getCol() * gp.getTilesize();
            y = gp.enemies[i].getPathfinder().pathNodes.get(j).getRow() * gp.getTilesize();
            xScreen = x - (gp.playerInfo.getXScreen() + gp.playerInfo.getXAxis());
            yScreen = y - (gp.playerInfo.getYScreen() + gp.playerInfo.getYAxis());
            tile.fillRect(xScreen, yScreen, tilesize, tilesize);
          }
        }
      }
    }
  }
  
  /**
   * Finds a spot that is a grass tile
   * 
   * @param map the complete map of the floor
   * @return a position which is grass.
   */
  public int[] findGreenSpot(char[][] map) {
    setMap(map);
    boolean pointPlaced = false;
    int column;
    int row;
    int[] spawnPoint = new int[2];
    while(pointPlaced == false) {
      column = (int)(Math.random()*(gp.getWorldSizeCol()));
      row = (int)(Math.random()*(gp.getWorldSizeRow()));
      if (map[column][row]=='G') {
        pointPlaced = true;
        spawnPoint[0]=column;
        spawnPoint[1]=row;
      }
    }
    return spawnPoint;
  }
  
  public void setMap(char[][] map) {
    this.map = map;
  }
  
  public char getTile(int num1, int num2) {
    return this.map[num1][num2];
  }
}
