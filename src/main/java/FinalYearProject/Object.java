package FinalYearProject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This class holds the objects that can be created, whether they be the treasure,
 * swords, potions or shields.
 * 
 * @author zjac122
 */
public class Object {
  public BufferedImage image;
  private String name;
  private boolean collision = false;
  private int XAxis, YAxis;
  
  /**
   * Draws the object on the screen
   * @param tile the graphics for drawing the tile
   * @param gp the game panel
   */
  public void draw(Graphics2D tile, GamePanel gp) {
    int xScreen = XAxis - (gp.playerInfo.getXScreen() + gp.playerInfo.getXAxis());
    int yScreen = YAxis - (gp.playerInfo.getYScreen() + gp.playerInfo.getYAxis());
    tile.drawImage(image, xScreen, yScreen, gp.getTilesize(), gp.getTilesize(), null);
  }
  
  public int getXAxis() {
    return this.XAxis;
  }
  
  public int getYAxis() {
    return this.YAxis;
  }
  
  public void setXAxis(int xaxis) {
    this.XAxis = xaxis;
  }
  
  public void setYAxis(int yaxis) {
    this.YAxis=yaxis;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String Name) {
    this.name=Name;
  }
  
  public boolean getCollision() {
    return this.collision;
  }
}
