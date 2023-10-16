package FinalYearProject;

import javax.swing.*;

/**
 * The main class which calls the game, this is the function called to start
 * the game.
 * 
 * @author zjac122
 */
public class DungeonCrawler {

  JFrame frame= new JFrame();
  private GamePanel gamePanel;
  
  /**
   * Builds and views the game.
   */
  public DungeonCrawler() {
    gamePanel = new GamePanel();
    frame.add(gamePanel);
    frame.setTitle("Dungeon Crawler");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.pack();
    frame.setVisible(true);
    gamePanel.Startup();
    gamePanel.run();
  }
  
  public static void main(String[] args) {
    new DungeonCrawler();
  }

}
