package FinalYearProject;

/**
 * This class places the enemies in random spots throughout the floor of the level.
 * 
 * @author zjac122
 */
public class EnemyPlacer {
  private GamePanel gp;
  
  /**
   * The constructor of the class
   * @param gp the gamepanel
   */
  public EnemyPlacer(GamePanel gp) {
    this.gp = gp;
  }
  
  /**
   * Places the enemies location across the floor
   * @param map the map of the floor
   * @param enemyStats the stats of the enemy which are needed to create them
   */
  public void placeObjects(char[][] map, int[] enemyStats) {
    for (int x=0; x<5; x++) {
      int[] spot = new int[1];
      gp.enemies[x] = new Enemy(gp, enemyStats);
      spot=gp.tileM.findGreenSpot(map);
      gp.enemies[x].setSpawnPoint(spot);
    }
  }
}
