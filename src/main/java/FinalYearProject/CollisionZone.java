package FinalYearProject;

/**
 * This class holds the areas of the player and enemy class that will collide with the
 * walls or each other, as otherwise the entire cell will collide and make the game
 * much harder to operate.
 * 
 * @author zjac122
 */
public class CollisionZone {
  
  private final int topx = 8;
  private final int topy = 16;
  private final int bottomx = 24;
  private final int bottomy = 32;
  
  /**
   * Gives the x value of the top-left corner
   * @return the x value of the top-left corner
   */
  public int getTopX() {
    return this.topx;
  }
  
  /**
   * Gives the y value of the top-left corner
   * @return the y value of the top-left corner
   */
  public int getTopY() {
    return this.topy;
  }
  
  /**
   * Gives the x value of the bottom right corner
   * @return the x value for the bottom right corner
   */
  public int getBottomX() {
    return this.bottomx;
  }
  
  /**
   * Gives the y value for the bottom right corner
   * @return the y value for the bottom right corner
   */
  public int getBottomY() {
    return this.bottomy;
  }

}
