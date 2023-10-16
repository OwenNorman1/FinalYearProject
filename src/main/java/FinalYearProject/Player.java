package FinalYearProject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains all the information on the player character, such as their current
 * co-ordinates and health.
 * 
 * @author zjac122
 */
public class Player {
  
  private int XAxis;
  private int YAxis;
  private int health = 20;
  private int attack = 10;
  private int defense = 10;
  private GamePanel gp;
  private KeyHandler keyHandler;
  public CollisionZone collisionZone;
  private final int XScreen;
  private final int YScreen;
  public BufferedImage PlayerDown, PlayerLeft, PlayerRight, PlayerUp, 
  PlayerDown2, PlayerLeft2, PlayerRight2, PlayerUp2, PlayerDown3, PlayerLeft3,
  PlayerRight3, PlayerUp3, PlayerDownAttack, PlayerLeftAttack,
  PlayerRightAttack, PlayerUpAttack;
  private Directions direction = Directions.DOWN;
  private boolean colliding = false;
  private int onTreasure, withEnemy;
  private boolean onStairs;
  private boolean moving;
  private int treasure = 0;
  private int SpriteNumber = 1;
  private int addedTreasure=20;
  private boolean attacking = false;
  private boolean invincibility = false;
  private int invFrames;
  private int tilesize;
  
  /**
   * Gets the images for the player in the resources folder.
   * 
   * @catch Catches IOexceptions caused when the player model does not exist.
   */
  public void getImage() {
    try {
      PlayerDown = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerDown.png"));
      PlayerLeft = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerLeft.png"));
      PlayerRight = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerRight.png"));
      PlayerUp = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerUp.png"));
      PlayerDown2 = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerDown2.png"));
      PlayerLeft2 = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerLeft2.png"));
      PlayerRight2 = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerRight2.png"));
      PlayerUp2 = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerUp2.png"));
      PlayerDown3 = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerDown3.png"));
      PlayerLeft3 = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerLeft3.png"));
      PlayerRight3 = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerRight3.png"));
      PlayerUp3 = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerUp3.png"));
      PlayerDownAttack = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerDownAttack.png"));
      PlayerLeftAttack = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerLeftAttack.png"));
      PlayerRightAttack = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerRightAttack.png"));
      PlayerUpAttack = ImageIO.read(getClass().getResourceAsStream("/Resources/PlayerUpAttack.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Gets the X Axis
   * 
   * @return the position of the player on the XAxis
   */
  public int getXAxis() {
    return XAxis;
  }
  
  /**
   * Gets the X Axis of the Screen
   * 
   * @return the position of the player on the Screen on the XAxis
   */  
  public int getXScreen() {
    return XScreen;
  }
  
  /**
   * Gets the Y Axis of the Screen
   * 
   * @return the position of the player on the Screen on the YAxis
   */  
  public int getYScreen() {
    return YScreen;
  }
  
  /**
   * Sets the position of the player on the XAxis to a new value
   * 
   * @param newXAxis, the new value of the XAxis
   */
  public void setXAxis(int newXAxis) {
    XAxis = newXAxis;
  }
  
  /**
   * Gets the Y Axis
   * 
   * @return the position of the player on the YAxis
   */
  public int getYAxis() {
    return YAxis;
  }
  
  /**
   * Sets the position of the player on the YAxis to a new value
   * 
   * @param newYAxis, the new value of the YAxis
   */
  public void setYAxis(int newYAxis) {
    YAxis = newYAxis;
  }
  
  /**
   * Sets the health of the player
   * 
   * @param newHealth the new health of the player.
   */
  public void setHealth(int newHealth) {
    health = newHealth;
    if (newHealth <= 0) {
      gp.setPhase(GamePhase.GAMEOVER);
    }
    gp.ui.setHealth(newHealth);
  }
  
  /**
   * Gets the Health
   * 
   * @return the health of the player
   */
  public int getHealth() {
    return health;
  }
  
  /**
   * Gets the attack stat of the player
   * @return the players attack stat
   */
  public int getAttack() {
    return attack;
  }

  /**
   * Sets the attack stat of the player
   * @param attack the players new attack stat
   */
  public void setAttack(int attack) {
    this.attack = attack;
  }
  
  /**
   * Gets the defense stat of the player
   * @return the defense of the player
   */
  public int getDefense() {
    return defense;
  }

  /**
   * Sets the defense of the player
   * @param defense the players new defense stat
   */
  public void setDefense(int defense) {
    this.defense = defense;
  }
  
  /**
   * Gets the total treasure the player has
   * @return the players treasure
   */
  public int getTreasure() {
    return this.treasure;
  }
  
  /**
   * Sets the total treasure the player has
   * @param treasure the players new amount of treasure
   */
  public void setTreasure(int treasure) {
    this.treasure = treasure;
  }
  
  /**
   * Gets how much treasure is added after a piece of treasure is picked up
   * @return the amount of treasure added
   */
  public int getAddedTreasure() {
    return this.addedTreasure;
  }
  
  /**
   * Sets how much treasure is added after a piece of treasure is picked up
   * @param addedTreasure the new amount of treasure added
   */
  public void setAddedTreasure(int addedTreasure) {
    this.addedTreasure=addedTreasure;
  }
  
  /**
   * Gets if the enemy has invicibility frames or not
   * @return whether the enemy is invincible or not.
   */
  public boolean getInvincibility() {
    return this.invincibility;
  }
  
  /**
   * Sets the invincibility frames of the enemy
   * @param invincibility
   */
  public void setInvincibility(boolean invincibility) {
    this.invincibility=invincibility;
  }
  
  public Directions getDirection() {
    return this.direction;
  }
  
  public void setDirection(Directions direction) {
    this.direction=direction;
  }
  
  public boolean getColliding() {
    return this.colliding;
  }
  
  public void setColliding(boolean Colliding) {
    this.colliding=Colliding;
  }
  
  /**
   * The constructor
   * @param gp the game panel
   * @param keyHandler the key handler for keyboard inputs
   */
  public Player(GamePanel gp, KeyHandler keyHandler) {
    this.gp = gp;
    this.keyHandler = keyHandler;
    this.XScreen = gp.getWidthScreen()/2;
    this.YScreen = gp.getHeightScreen()/2;
    this.collisionZone = new CollisionZone();
    this.tilesize=gp.getTilesize();
  }
  
  /**
   * Sets the spawnpoint for the player in terms of the X and Y Axis
   * 
   * @param spawnPoint a random grass tile that the player can spawn on.
   */
  public void setSpawnPoint(int[] spawnPoint) {
    XAxis = (tilesize*spawnPoint[0])-gp.getWidthScreen();
    YAxis = (tilesize*spawnPoint[1])-gp.getHeightScreen();
  }
  
  /**
   * Tells which treasure is getting picked up
   * 
   * @param i the number of the treasure that has been picked up
   */
  public void pickUpItems(int i) {
    gp.objects[i]=null;
    if (i == 5) {
      this.setAttack(this.getAttack()+5);
    } else if (i==6) {
      this.setDefense(this.getDefense()+5);
    } else if (i==7) {
      if (this.getHealth() == 20) {
        treasure=treasure+addedTreasure/2;
      } else if (this.getHealth() >= 15) {
        this.setHealth(20);
      } else {
        this.setHealth(this.getHealth()+5);
      }
    } else {
      treasure=treasure+addedTreasure;
    }
    gp.playSound(4);
    gp.ui.setTreasure(treasure);
  }
  
  /**
   * Lets the player attack the enemy character
   * @param enemy the enemy the player is attacking.
   */
  public void attack(Enemy enemy) {
    if (enemy.getInvincibility() == false) {
      int enemyDefense=enemy.getDefense();
      int damage = this.getAttack()-enemyDefense;
      if (damage <= 0) {
        damage = 1;
      }
      gp.playSound(2);
      enemy.setHealth(enemy.getHealth()-damage);
      enemy.setInvincibility(true);
    }
  }
  
  /**
   * Updates the position of the player and checks if there is a collison
   * 
   * @param collision the collision class which checks if a collision is 
   * occuring.
   */
  public void update(Collision collision) {
    if (keyHandler.upPressed) {
      direction=Directions.UP;
    }
    else if (keyHandler.leftPressed) {
      direction=Directions.LEFT;
    }
    else if (keyHandler.downPressed) {
      direction=Directions.DOWN;
    }
    else if (keyHandler.rightPressed) {
      direction=Directions.RIGHT;
    }
    if (keyHandler.enterPressed) {
      attacking=true;
    } else {
      attacking=false;
    }
    onTreasure = -1;
    withEnemy = -1;
    colliding=false;
    onStairs=false;
    moving=false;
    if (XAxis != 0 && YAxis != 0) {
      collision.checkTile(this);
      withEnemy=collision.checkEnemy(this);
      onTreasure=collision.checkTreasure(this);
      onStairs=collision.checkStairs(this);
    }
    if (withEnemy != -1 && attacking == true) {
      attack(gp.enemies[withEnemy]);
    }
    if (onTreasure != -1) {
      this.pickUpItems(onTreasure);
    }
    if (onStairs == true && gp.askAgain==true) {
      gp.setPhase(GamePhase.ONSTAIRS);
    }
    if (colliding == false) {
      if (keyHandler.upPressed) {
        moving=true;
        this.setYAxis(this.getYAxis()-2);
      }
      else if (keyHandler.leftPressed) {
        moving=true;
        this.setXAxis(this.getXAxis()-2);
      }
      else if (keyHandler.downPressed) {
        moving=true;
        this.setYAxis(this.getYAxis()+2);
      }
      else if (keyHandler.rightPressed) {
        moving=true;
        this.setXAxis(this.getXAxis()+2);
      }
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
   * Draws the player on the screen
   * 
   * @param player the graphics for drawing the player
   */
  public void draw(Graphics2D player) {
    BufferedImage image = null;
    switch(direction) {
      case UP:
        if (SpriteNumber == 0) {
          image = PlayerUp;
        } else if (SpriteNumber == 1) {
          image = PlayerUp2;
        } else {
          image = PlayerUp3;
        }
        if (attacking == true) {
          image = PlayerUpAttack;
        }
        break;
      case LEFT:
        if (SpriteNumber == 0) {
          image = PlayerLeft;
        } else if (SpriteNumber == 1) {
          image = PlayerLeft2;
        } else {
          image = PlayerLeft3;
        }
        if (attacking == true) {
          image = PlayerLeftAttack;
        }
        break;
      case DOWN:
        if (SpriteNumber == 0) {
          image = PlayerDown;
        } else if (SpriteNumber == 1) {
          image = PlayerDown2;
        } else {
          image = PlayerDown3;
        }
        if (attacking == true) {
          image = PlayerDownAttack;
        }
        break;
      case RIGHT:
        if (SpriteNumber == 0) {
          image = PlayerRight;
        } else if (SpriteNumber == 1) {
          image = PlayerRight2;
        } else {
          image = PlayerRight3;
        }
        if (attacking == true) {
          image = PlayerRightAttack;
        }
        break;
    }
    if (attacking == true) {
      if (direction == Directions.UP || direction == Directions.DOWN) {
        player.drawImage(image, XScreen, YScreen, tilesize, 2*tilesize, null);
      } else {
        player.drawImage(image, XScreen, YScreen, 2*tilesize, tilesize, null);
      }
    } else {
      player.drawImage(image, XScreen, YScreen, tilesize, tilesize, null);
    }
  }
}