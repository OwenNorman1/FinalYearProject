package FinalYearProject;

/**
 * This class detects if an entity in the game, such as the player or an enemy
 * is colliding with either each other or the wall.
 * 
 * @author zjac122
 */
public class Collision {

  private GamePanel gp;
  private int tilesize;
  
  /**
   * The constructor for the class
   * @param gp the gamepanel
   */
  public Collision(GamePanel gp) {
    this.gp = gp;
    this.tilesize = gp.getTilesize();
  }
  
  /**
   * Checks if the player is colliding with the wall.
   * 
   * @param player the player character
   */
  public void checkTile(Player player) {
    int leftCheck=(player.getXAxis()+player.collisionZone.getTopX()+gp.getWidthScreen());
    int rightCheck=(player.getXAxis()+player.collisionZone.getTopY()+gp.getWidthScreen());
    int topCheck=(player.getYAxis()+player.collisionZone.getBottomX()+gp.getHeightScreen());
    int bottomCheck=(player.getYAxis()+player.collisionZone.getBottomY()+gp.getHeightScreen());
    int leftTile = divideByTileSize(leftCheck);
    int rightTile = divideByTileSize(rightCheck);
    int topTile = divideByTileSize(topCheck);
    int bottomTile = divideByTileSize(bottomCheck);
    char tile1 = '@', tile2 = '@';
    switch(player.getDirection()) {
      case UP:
        topTile = divideByTileSize(topCheck-2);
        tile1 = gp.tileM.getTile(leftTile, topTile);
        tile2 = gp.tileM.getTile(rightTile, topTile);
        break;
      case DOWN:
        bottomTile = divideByTileSize(bottomCheck-2);
        tile1 = gp.tileM.getTile(leftTile, bottomTile);
        tile2 = gp.tileM.getTile(rightTile, bottomTile);
        break;
      case LEFT:
        leftTile = divideByTileSize(leftCheck-2);
        tile1 = gp.tileM.getTile(leftTile, topTile);
        tile2 = gp.tileM.getTile(leftTile, bottomTile);
        break;
      case RIGHT:
        rightTile = divideByTileSize(rightCheck-2);
        tile1 = gp.tileM.getTile(rightTile, topTile);
        tile2 = gp.tileM.getTile(rightTile, bottomTile);
        break;
    }
    if (tile1 == 'W' && tile2 == 'W') {
      player.setColliding(true);
    }
  }
  
  /**
   * Checks if the enemy is colliding with the wall
   * 
   * @param enemy the enemy in question
   */
  public void checkTile(Enemy enemy) {
    int leftCheck=enemy.getXAxis()+enemy.getCollisionZone().getTopX();
    int rightCheck=enemy.getXAxis()+enemy.getCollisionZone().getTopY();
    int topCheck=enemy.getYAxis()+enemy.getCollisionZone().getBottomX();
    int bottomCheck=enemy.getYAxis()+enemy.getCollisionZone().getBottomY();
    int leftTile = divideByTileSize(leftCheck);
    int rightTile = divideByTileSize(rightCheck);
    int topTile = divideByTileSize(topCheck);
    int bottomTile = divideByTileSize(bottomCheck);
    char tile1 = '@', tile2 = '@';
    switch(enemy.getDirection()) {
      case UP:
        topTile = divideByTileSize(topCheck-1);
        tile1 = gp.tileM.getTile(leftTile, topTile);
        tile2 = gp.tileM.getTile(rightTile, topTile);
        break;
      case DOWN:
        bottomTile = divideByTileSize(bottomCheck-1);
        tile1 = gp.tileM.getTile(leftTile, bottomTile);
        tile2 = gp.tileM.getTile(rightTile, bottomTile);
        break;
      case LEFT:
        leftTile = divideByTileSize(leftCheck-1);
        tile1 = gp.tileM.getTile(leftTile, topTile);
        tile2 = gp.tileM.getTile(leftTile, bottomTile);
        break;
      case RIGHT:
        rightTile = divideByTileSize(rightCheck-1);
        tile1 = gp.tileM.getTile(rightTile, topTile);
        tile2 = gp.tileM.getTile(rightTile, bottomTile);
        break;
    }
    if (tile1 == 'W' && tile2 == 'W') {
      enemy.setColliding(true);
    }
  }
  
  /**
   * Checks if the player and the enemy are colliding.
   * 
   * @param player the player character
   */
  public int checkEnemy(Player player) {
    int enemyXAxis1;
    int enemyYAxis1;
    int enemyXAxis2;
    int enemyYAxis2;
    int playerXAxis1 = player.getXAxis();
    int playerYAxis1 = player.getYAxis();
    int playerXAxis2 = playerXAxis1+tilesize;
    int playerYAxis2 = playerYAxis1+tilesize;
    for (int x = 0; x < gp.enemies.length; x++) {
      if (gp.enemies[x].getDead()==false) {
        enemyXAxis1 = gp.enemies[x].getXAxis()-gp.getWidthScreen();
        enemyYAxis1 = gp.enemies[x].getYAxis()-gp.getHeightScreen();
        enemyXAxis2 = enemyXAxis1+tilesize;
        enemyYAxis2 = enemyYAxis1+tilesize;
        if (playerXAxis1 < enemyXAxis2 && playerXAxis2 > enemyXAxis1 &&
            playerYAxis1 < enemyYAxis2 && playerYAxis2 > enemyYAxis1) {
          player.setColliding(true);
          gp.enemies[x].setColliding(true);
          return x;
        }
      }
    }
    return -1;
  }
  
  /**
   * Checks if the player is on treasure, if so return true
   * 
   * @param player the player character.
   * @return the treasure the player is intersecting.
   */
  public int checkTreasure(Player player) {
    int x1 = player.getXAxis()+gp.getWidthScreen();
    int x2 = x1+tilesize;
    int y1 = player.getYAxis()+gp.getHeightScreen();
    int y2 = y1+tilesize;
    for (int x=0; x<gp.objects.length; x++) {
      if (gp.objects[x]==null) {
        continue;
      }
      int x3 =gp.objects[x].getXAxis();
      int y3 =gp.objects[x].getYAxis();
      int x4 = x3+tilesize;
      int y4 = y3+tilesize;
      if (x1 < x4 && x2 > x3 && y1 < y4 && y2 > y3) {
        return x;
      }
    }
    return -1;
  }
  
  /**
   * Checks if the player is on the stairs
   * 
   * @param player the location of the player character
   * @return whether or not the player is on the stairs
   */
  public boolean checkStairs(Player player) {
    int x1 = player.getXAxis()+gp.getWidthScreen();
    int x2 = x1+tilesize;
    int y1 = player.getYAxis()+gp.getHeightScreen();
    int y2 = y1+tilesize;
    int x3 = gp.tileM.getStair()[0]*tilesize;
    int y3 = gp.tileM.getStair()[1]*tilesize;
    int x4 = x3+tilesize;
    int y4 = y3+tilesize;
    if (x1 < x4 && x2 > x3 && y1 < y4 && y2 > y3) {
      return true;
    }
    return false;
  }
  
  /**
   * Divides the number being checked by the tilesize to get the tile the value
   * is on
   * 
   * @param num the value that needs to be divided
   * @return the number divided by the tilesize, giving us the tile this value
   * is on
   */
  public int divideByTileSize(int num) {
    return num/tilesize;
  }
}
