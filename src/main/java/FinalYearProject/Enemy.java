package FinalYearProject;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains all the information on the enemies, such as their current
 * co-ordinates and health.
 * 
 * @author zjac122
 */
public class Enemy {
  
  private int XAxis, YAxis;
  private int health;
  private int attack;
  private int defense;
  private int maxHealth;
  private BufferedImage EnemyDown, EnemyLeft, EnemyRight, EnemyUp, EnemyDown2,
  EnemyDown3, EnemyLeft2, EnemyLeft3, EnemyRight2, EnemyRight3, EnemyUp2,
  EnemyUp3;
  private GamePanel gp;
  private Directions direction = Directions.UP;
  private CollisionZone collisionZone;
  private EnemyStates state = EnemyStates.NODIRECTION;
  private Pathfinder pathFinder;
  private boolean colliding;
  private boolean moving=true;
  private boolean dead=false;
  private int SpriteNumber=1;
  private int nextCol, nextRow = 0;
  private boolean invincibility = false;
  private int cooldown = 0;
  private int invFrames = 0;
  private int tilesize;
  
  /**
   * Gets the images for the enemy in the resources folder.
   * 
   * @catch Catches IOexceptions caused when the enemy model does not exist.
   */
  public void getImage() {
    try {
      EnemyDown = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyDown.png"));
      EnemyDown2 = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyDown2.png"));
      EnemyDown3 = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyDown3.png"));
      EnemyLeft = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyLeft.png"));
      EnemyLeft2 = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyLeft2.png"));
      EnemyLeft3 = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyLeft3.png"));
      EnemyRight = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyRight.png"));
      EnemyRight2 = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyRight2.png"));
      EnemyRight3 = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyRight3.png"));
      EnemyUp = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyUp.png"));
      EnemyUp2 = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyUp2.png"));
      EnemyUp3 = ImageIO.read(getClass().getResourceAsStream("/Resources/EnemyUp3.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Getters and setters
   */
  public int getXAxis() {
    return XAxis;
  }

  public void setXAxis(int newXAxis) {
    XAxis = newXAxis;
  }

  public int getYAxis() {
    return YAxis;
  }
  
  public void setYAxis(int newYAxis) {
    YAxis = newYAxis;
  }
  
  public void setHealth(int newHealth) {
    health = newHealth;
    if (health <= 0) {
      dead=true;
    }
  }
  
  public int getHealth() {
    return health;
  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public int getDefense() {
    return defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }
  
  public boolean getInvincibility() {
    return this.invincibility;
  }
  
  public void setInvincibility(boolean invincibility) {
    this.invincibility=invincibility;
  }
  
  public Directions getDirection() {
    return this.direction;
  }
  
  public void setDireciton(Directions direction) {
    this.direction=direction;
  }
  
  public CollisionZone getCollisionZone() {
    return this.collisionZone;
  }
  
  public void setCollisionZone(CollisionZone collisionZone) {
    this.collisionZone=collisionZone;
  }
  
  public boolean getColliding() {
    return this.colliding;
  }
  
  public void setColliding(boolean Colliding) {
    this.colliding=Colliding;
  }
  
  public boolean getDead() {
    return this.dead;
  }
  
  public void setDead(boolean Dead) {
    this.dead=Dead;
  }
  
  public Pathfinder getPathfinder() {
    return this.pathFinder;
  }

  /**
   * The constructor for the Enemy
   * @param gp the gamepanel
   * @param enemyStats the health, attack and defense of the enemy stored in a list
   */
  public Enemy(GamePanel gp, int[] enemyStats) {
    this.gp = gp;
    this.collisionZone = new CollisionZone();
    this.health=enemyStats[0];
    this.maxHealth=enemyStats[0];
    this.attack=enemyStats[1];
    this.defense=enemyStats[2];
    this.pathFinder= new Pathfinder(this.gp);
    this.tilesize=gp.getTilesize();
  }
  
  /**
   * Sets the spawnpoint for the enemy in terms of the X and Y Axis
   * 
   * @param spawnPoint a random grass tile that the enemy can spawn on.
   */
  public void setSpawnPoint(int[] spawnPoint) {
    XAxis = spawnPoint[0]*tilesize;
    YAxis = spawnPoint[1]*tilesize;
  }
  
  /**
   * Updates the enemies movement
   * 
   * @param collision
   */
  public void update(Collision collision) {
    switch (state) {
      case IDLE:
        if (playerInSight(gp.playerInfo,25)) {
          state = EnemyStates.ACTIVE;
        }
        colliding=false;
        if (XAxis != 0 && YAxis != 0) {
          collision.checkTile(this);
        }
        if (colliding == false) {
          if (direction == Directions.UP) {
            YAxis=YAxis-2;
          }
          else if (direction == Directions.LEFT) {
            XAxis=XAxis-2;
          }
          else if (direction == Directions.DOWN) {
            YAxis=YAxis+2;
          }
          else if (direction == Directions.RIGHT) {
            XAxis=XAxis+2;
          }
        } else if (colliding == true) {
          state = EnemyStates.NODIRECTION;
        }
        break;
      case NODIRECTION:
        if (playerInSight(gp.playerInfo,25)) {
          state = EnemyStates.ACTIVE;
        }
        int i = (int)(Math.random()*4);
        if (i==0) {
          direction = Directions.UP;
        } else if (i == 1) {
          direction = Directions.LEFT;
        } else if (i == 2) {
          direction = Directions.DOWN;
        } else if (i == 3) {
          direction = Directions.RIGHT;
        }
        state = EnemyStates.IDLE;
        break;
      case ACTIVE:
        int goalCol = (gp.playerInfo.getXAxis()+gp.getWidthScreen());
        int goalRow = (gp.playerInfo.getYAxis()+gp.getHeightScreen());
        searchForPath(goalCol, goalRow, collision);
        if (playerInSight(gp.playerInfo,25) == false) {
          state=EnemyStates.NODIRECTION;
        }
        break;
      case ATTACKING:
        if (playerInSight(gp.playerInfo,4)) {
          if (cooldown == 100) {
            cooldown=0;
            int PlayerHealth = attack(gp.playerInfo);
            gp.playerInfo.setHealth(PlayerHealth);
          } else {
            cooldown++;
          }
        } else {
          state=EnemyStates.ACTIVE;
        }
        break;
    }
    if (moving==true) {
      if (SpriteNumber == 2) {
        SpriteNumber=0;
      } else {
        SpriteNumber++;
      }
    } else {
      SpriteNumber=0;
    }
    if (invincibility) {
      if (invFrames == 100) {
        invincibility = false;
      } else {
        invFrames++;
      }
    }
  }
  
  /**
   * Uses the pathfinder search to get the best path, then performs the
   * neessary actions to execute that search.
   * 
   * @param GoalCol the column the enemy is trying to reach
   * @param GoalRow the row the enemy is trying to reach
   * @param collision checks if the enemy is colliding with anything
   */
  public void searchForPath(int GoalCol, int GoalRow, Collision collision) {
    int startCol = XAxis /tilesize;
    int startRow = YAxis /tilesize;
    pathFinder.setNodes(startCol, startRow, GoalCol/tilesize, GoalRow/tilesize);
    if (pathFinder.Search() == true) {
      nextCol=pathFinder.pathNodes.get(0).getCol();
      nextRow=pathFinder.pathNodes.get(0).getRow();
    }
    if (nextCol != 0 && nextRow != 0) {
      int x = 0;
      if (nextCol == XAxis/tilesize && nextRow == YAxis/tilesize) {
        if (x == pathFinder.pathNodes.size()) {
          state = EnemyStates.ATTACKING;
        } else {
          x++;
          nextCol=pathFinder.pathNodes.get(0).getCol();
          nextRow=pathFinder.pathNodes.get(0).getRow();
        }
      }
      if (nextCol < XAxis/tilesize) {
        direction = Directions.LEFT;
        XAxis=XAxis-2;
      }
      else if (nextCol > XAxis/tilesize) {
        direction = Directions.RIGHT;
        XAxis=XAxis+2;
      }
      else if (nextRow < YAxis/tilesize) {
        direction = Directions.UP;
        YAxis=YAxis-2;
      }
      else if (nextRow > YAxis/tilesize) {
        direction = Directions.DOWN;
        YAxis=YAxis+2;
      }
    }
  }
  
  /**
   * Checks whether the player is close enough to trigger the enemy
   * @param Player the player character
   * @return whether the player is close enough or not
   */
  public boolean playerInSight(Player Player, int closeness) {  
    int playerX = (Player.getXAxis()+gp.getWidthScreen())/tilesize;
    int playerY = (Player.getYAxis()+gp.getHeightScreen())/tilesize;
    int diffX= playerX-(XAxis/tilesize);
    int diffY= playerY-(YAxis/tilesize);
    if (diffX*diffX + diffY*diffY <= closeness) {
      return true;
    }
    return false;
  }
  
  /**
   * Allows the enemy to attack the player, reducing their HP
   * @param player, the player getting attacked
   * @return the new health of the player
   */
  public int attack(Player player) {
    if (player.getInvincibility()==true) {
      return player.getHealth();
    }
    int playerDefense=player.getDefense();
    int damage = this.getAttack()-playerDefense;
    if (damage <= 0) {
      damage = 1;
    }
    gp.playSound(3);
    player.setInvincibility(true);
    return player.getHealth()-damage;
  }
  
  /**
   * Draws the enemy on the screen
   * @param enemy the graphics for drawing the enemy
   */
  public void draw(Graphics2D enemy) {
    BufferedImage image = null;
    int xScreen = XAxis - (gp.playerInfo.getXScreen() + gp.playerInfo.getXAxis());
    int yScreen = YAxis - (gp.playerInfo.getYScreen() + gp.playerInfo.getYAxis());
    switch(direction) {
      case UP:
        if (SpriteNumber == 0) {
          image = EnemyUp;
        } else if (SpriteNumber == 1) {
          image = EnemyUp2;
        } else {
          image = EnemyUp3;
        }
        break;
      case LEFT:
        if (SpriteNumber == 0) {
          image = EnemyLeft;
        } else if (SpriteNumber == 1) {
          image = EnemyLeft2;
        } else {
          image = EnemyLeft3;
        }
        break;
      case DOWN:
        if (SpriteNumber == 0) {
          image = EnemyDown;
        } else if (SpriteNumber == 1) {
          image = EnemyDown2;
        } else {
          image = EnemyDown3;
        }
        break;
      case RIGHT:
        if (SpriteNumber == 0) {
          image = EnemyRight;
        } else if (SpriteNumber == 1) {
          image = EnemyRight2;
        } else {
          image = EnemyRight3;
        }
        break;
    }
    enemy.drawImage(image, xScreen, yScreen, tilesize, tilesize, null);
    enemy.setColor(Color.black);
    enemy.fillRect(xScreen, yScreen-20, tilesize+2, 10);
    double healthPixel = (double)tilesize/maxHealth;
    double healthPercent = healthPixel*health;
    enemy.setColor(Color.red);
    enemy.fillRect(xScreen+1, yScreen-19, (int)healthPercent, 8);
  }
}
