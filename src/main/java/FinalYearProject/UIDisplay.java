package FinalYearProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class creates the UI display for things that need to be shown in the
 * game, such as the players health, treasure and the current floor
 * 
 * @author zjac122
 */
public class UIDisplay {
  private BufferedImage FullHeart, ThreeHeart, HalfHeart, QuarterHeart, NoHeart;
  private GamePanel gp;
  private int health, treasure, floor;
  
  /**
   * The constructor
   * @param gp the game panel
   * @param health the players health
   * @param treasure the players treasure
   * @param floor the current floor
   */
  public UIDisplay(GamePanel gp, int health, int treasure, int floor){
    this.gp = gp;
    this.health=health;
    this.treasure=treasure;
    this.floor=floor;
    try {
      this.ThreeHeart=ImageIO.read(getClass().getResourceAsStream("/Resources/HeartThree.png"));
      this.HalfHeart=ImageIO.read(getClass().getResourceAsStream("/Resources/HeartHalf.png"));
      this.QuarterHeart=ImageIO.read(getClass().getResourceAsStream("/Resources/HeartQuarter.png"));
      this.NoHeart=ImageIO.read(getClass().getResourceAsStream("/Resources/HeartEmpty.png"));
      this.FullHeart=ImageIO.read(getClass().getResourceAsStream("/Resources/HeartFull.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Sets the health of the player for the UI.
   * @param newHealth the players new health
   */
  public void setHealth(int newHealth) {
    this.health = newHealth;
  }
  
  /**
   * Sets the treasure of the player for the UI
   * @param treasure the players current treasure
   */
  public void setTreasure(int treasure) {
    this.treasure=treasure;
  }
  
  /**
   * Sets the floor the player is currently on for the UI
   * @param floor the current floor
   */
  public void setFloor(int floor) {
    this.floor=floor;
  }
  
  /**
   * Draws the hearts onto the game screen.
   * @param heart the graphics for drawing the heart.
   */
  public void draw(Graphics2D ui) {
    int yplace=gp.getTilesize();
    int currentHealth=this.health;
    for(int x = 1; x<=5; x++) {
      if (currentHealth >= 4) {
        ui.drawImage(FullHeart, yplace, gp.getTilesize(), gp.getTilesize(), gp.getTilesize(), null);
        currentHealth=currentHealth-4;
      } else {
        int heartSize = currentHealth%4;
        switch (heartSize) {
          case 0:
            ui.drawImage(NoHeart, yplace, gp.getTilesize(), gp.getTilesize(), gp.getTilesize(), null);
            break;
          case 1:
            ui.drawImage(QuarterHeart, yplace, gp.getTilesize(), gp.getTilesize(), gp.getTilesize(), null);
            currentHealth=0;
            break;
          case 2:
            ui.drawImage(HalfHeart, yplace, gp.getTilesize(), gp.getTilesize(), gp.getTilesize(), null);
            currentHealth=0;
            break;
          case 3:
            ui.drawImage(ThreeHeart, yplace, gp.getTilesize(), gp.getTilesize(), gp.getTilesize(), null);
            currentHealth=0;
            break;
        }
      }
      yplace=yplace+gp.getTilesize();
      ui.setColor(Color.YELLOW);
      ui.setFont(ui.getFont().deriveFont(Font.PLAIN,28F));
      ui.drawString("Treasure: "+ treasure, gp.getTilesize(), gp.getTilesize()*11);
      ui.setColor(Color.BLACK);
      ui.drawString("B"+floor+"F", gp.getWidthScreen()-2*gp.getTilesize(), gp.getTilesize());
    }
  }
}
