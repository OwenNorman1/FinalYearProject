package FinalYearProject;

/**
 * Places the objects in a random location on the map
 * 
 * @author zjac122
 */
public class ObjectPlacer {
  private GamePanel gp;
  
  /**
   * The constructor
   * 
   * @param gp the game panel
   */
  public ObjectPlacer(GamePanel gp) {
    this.gp = gp;
  }
  
  /**
   * The function that randomly places the objects throughout the floor
   * 
   * @param map the map of the floor.
   */
  public void placeObjects(char[][] map) {
    int[] spot = new int[1];
    for (int x=0; x<5; x++) {
      gp.objects[x] = new Treasure();
      spot=gp.tileM.findGreenSpot(map);
      gp.objects[x].setXAxis(spot[0]*gp.getTilesize());
      gp.objects[x].setYAxis(spot[1]*gp.getTilesize());
    }
    gp.objects[5] = new Sword();
    spot=gp.tileM.findGreenSpot(map);
    gp.objects[5].setXAxis(spot[0]*gp.getTilesize());
    gp.objects[5].setYAxis(spot[1]*gp.getTilesize());
    gp.objects[6] = new Shield();
    spot=gp.tileM.findGreenSpot(map);
    gp.objects[6].setXAxis(spot[0]*gp.getTilesize());
    gp.objects[6].setYAxis(spot[1]*gp.getTilesize());
    gp.objects[7] = new Potion();
    spot=gp.tileM.findGreenSpot(map);
    gp.objects[7].setXAxis(spot[0]*gp.getTilesize());
    gp.objects[7].setYAxis(spot[1]*gp.getTilesize());
  }
}
