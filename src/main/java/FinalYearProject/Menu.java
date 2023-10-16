package FinalYearProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * This class creates the menu needed for both the title screen and the game over
 * screen.
 * 
 * @author zjac122
 */
public class Menu {
  
  private GamePanel gp;
  private int commandNum;
  private int counter;
  
  /**
   * The constructor for the menu
   * 
   * @param gp the gamepanel
   */
  public Menu(GamePanel gp) {
    this.gp=gp;
    this.commandNum=0;
    this.counter=0;
  }
  
  /**
   * Creates the title screen
   * 
   * @param Menu the graphics that makes the screen
   */
  public void draw (Graphics2D Menu) {
    Menu.setColor(Color.BLACK);
    Menu.fillRect(0, 0, gp.getWidthScreen(), gp.getHeightScreen());
    Menu.setColor(Color.WHITE);
    Menu.setFont(Menu.getFont().deriveFont(Font.PLAIN,32F));
    Menu.drawString("Dungeon Delvers", gp.getWidthScreen()/3, gp.getHeightScreen()/4);
    if (this.counter <= 10) {
      Menu.drawImage(gp.playerInfo.PlayerDown2, (gp.getWidthScreen()/2)-gp.getTilesize(), (gp.getHeightScreen()/2)-gp.getTilesize(), gp.getTilesize()*2, gp.getTilesize()*2, null);
    } else {
      Menu.drawImage(gp.playerInfo.PlayerDown3, (gp.getWidthScreen()/2)-gp.getTilesize(), (gp.getHeightScreen()/2)-gp.getTilesize(), gp.getTilesize()*2, gp.getTilesize()*2, null);
    }
    if (this.counter==20) {
      this.counter=0;
    } else {
      this.counter++;
    }
    Menu.drawString("Start", (gp.getWidthScreen()/2)-gp.getTilesize(), (gp.getHeightScreen()/4)*3);
    Menu.drawString("Quit", (gp.getWidthScreen()/2)-gp.getTilesize(), (gp.getHeightScreen()/4)*3+gp.getTilesize());
    if (commandNum==0) {
      Menu.drawString(">", (gp.getWidthScreen()/2)-(2*gp.getTilesize()), (gp.getHeightScreen()/4)*3);
    } else {
      Menu.drawString(">", (gp.getWidthScreen()/2)-(2*gp.getTilesize()), (gp.getHeightScreen()/4)*3+gp.getTilesize());
    }
    if (gp.keyHandler.enterPressed == true) {
      if (commandNum == 0) {
        gp.setPhase(GamePhase.LOADGAME);
      } else {
        System.exit(0);
      }
    }
    if (gp.keyHandler.downPressed == true) {
      commandNum=1;
    }
    if (gp.keyHandler.upPressed == true) {
      commandNum=0;
    }
  }
  
  /**
   * Creates the game over screen
   * 
   * @param Menu the graphics that makes the screen
   */
  public void gameOver(Graphics2D Menu) {
    int treasure= gp.playerInfo.getTreasure();
    int floor = gp.getFloor();
    int totalScore = floor*treasure;
    Menu.setColor(Color.BLACK);
    Menu.fillRect(0, 0, gp.getWidthScreen(), gp.getHeightScreen());
    Menu.setColor(Color.WHITE);
    Menu.setFont(Menu.getFont().deriveFont(Font.PLAIN,32F));
    Menu.drawString("Game Over", gp.getWidthScreen()/3, gp.getHeightScreen()/4);
    Menu.drawString("Total Treasure:" + treasure, (gp.getWidthScreen()/2)-(3*gp.getTilesize()), (gp.getHeightScreen()/2));
    Menu.drawString("Floor Reached:" + floor, (gp.getWidthScreen()/2)-(3*gp.getTilesize()), (gp.getHeightScreen()/2)+gp.getTilesize());
    Menu.drawString("Total Score:" + totalScore, (gp.getWidthScreen()/2)-(3*gp.getTilesize()), (gp.getHeightScreen()/2)+(2*gp.getTilesize()));
  }
  
}
