package FinalYearProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * This class is the Game Panel for the game, it contains the settings for
 * the game and how it runs, updates and starts up.
 * 
 * @author zjac122
 */

/**
 * @author zjac122
 *
 */
/**
 * @author zjac122
 *
 */
public class GamePanel extends JPanel implements Runnable{
  private static final long serialVersionUID = 1L;
  private final int tileSizeNoScale=16;
  private final int scale = 3;
  private final int tileSize = scale * tileSizeNoScale;
  private final int Columns = 16;
  private final int Rows = 12;
  private final int WidthScreen = Columns * tileSize;
  private final int HeightScreen = Rows * tileSize;
  private final int WorldSizeCol = 56;
  private final int WorldSizeRow = 28;
  private GamePhase phase = GamePhase.MENU;
  private int[] enemyStats = {20,5,5};
  private int floor = 1;
  
  Thread gameThread;
  KeyHandler keyHandler = new KeyHandler();
  Player playerInfo;
  TileManager tileM;
  FloorGenerator floorGen;
  Collision collision;
  ObjectPlacer objectPlacer;
  EnemyPlacer enemyPlacer;
  char[][] generatedMap;
  int[] spawnPoint;
  int[] enspawnPoint;
  boolean askAgain = true;
  boolean drawDialogue = false;
  Object objects[] = new Object[8];
  Enemy enemies[] = new Enemy[5];
  Menu menu;
  UIDisplay ui;
  Music music;
  
  public int getWidthScreen() {
    return this.WidthScreen;
  }
  
  public int getHeightScreen() {
    return this.HeightScreen;
  }
  
  public int getTilesize() {
    return this.tileSize;
  }
  
  public int getWorldSizeCol() {
    return this.WorldSizeCol;
  }
  
  public int getWorldSizeRow() {
    return this.WorldSizeRow;
  }
  
  public int getFloor() {
    return this.floor;
  }
  
  /**
   * The constructor for the class
   */
  public GamePanel() {
    this.setPreferredSize(new Dimension(WidthScreen, HeightScreen));
    this.setBackground(Color.gray);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandler);
    this.setFocusable(true);
    this.generatedMap = null;
  }
  
  /**
   * Creates the thread that the game runs on.
   */
  public void Startup() {
    gameThread = new Thread(this);
    gameThread.start();
  }
  
  /**
   * Sets the phase for the game
   * 
   * @param newPhase the games new phase
   */
  public void setPhase(GamePhase newPhase) {
    phase=newPhase;
  }
  
  /**
   * Resets the floor for the next floor of the game.
   */
  public void resetFloor() {
    int addedTreasure = playerInfo.getAddedTreasure();
    generatedMap = null;
    phase=GamePhase.LOADGAME;
    for (int x=0; x<3; x++) {
      if (Math.random() < 0.5) {
        enemyStats[x]=enemyStats[x]+5;
        addedTreasure=addedTreasure+2;
      }
    }
    floorGen = new FloorGenerator();
    floor++;
    playerInfo.setAddedTreasure(addedTreasure);
    ui.setFloor(floor);
  }
  
  /**
   * The function used for setting and playing the sounds in the game
   * @param soundNo the sound needed to be played
   */
  public void playMusic(int soundNo) {
    music.setSound(soundNo);
    music.play();
    music.loop();
  }
  
  /**
   * Stops the music that is being played.
   */
  public void stopSound() {
    music.stop();
  }
  
  /**
   * Similar to the find music function, however this time the sound is not
   * going to be looped
   * @param soundNo the sound needed to be played
   */
  public void playSound(int soundNo) {
    music.setSound(soundNo);
    music.play();
  }

  /**
   * The Main game loop
   */
  public void run() {
    double DrawInt = 1000000000/60;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    long drawCount = 0;
    menu = new Menu(this);
    music = new Music();
    playerInfo = new Player(this, keyHandler);
    enemyPlacer = new EnemyPlacer(this);
    floorGen = new FloorGenerator();
    tileM = new TileManager(this);
    collision = new Collision(this);
    objectPlacer = new ObjectPlacer(this);
    ui = new UIDisplay(this, playerInfo.getHealth(), playerInfo.getTreasure(), floor);
    playMusic(0);
    while (gameThread != null) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / DrawInt;
      timer += (currentTime - lastTime);
      lastTime = currentTime;
      if (delta >= 1) {
        update();
        repaint();
        delta--;
        drawCount++;
      }
      if (timer >= 1000000000) {
        if (collision.checkStairs(playerInfo) == false) {
          askAgain=true;
        }
        System.out.println("FPS: "+ drawCount);
        drawCount=0;
        timer=0;
      }
    }
  }
  
  /**
   * Updates and changes the game, calls necessary updates from relevant
   * classes.
   */
  public void update() {
    if (phase==GamePhase.INGAME) {
      playerInfo.update(collision);
      for (int x=0; x<enemies.length; x++) {
        if (enemies[x].getDead() == false) {
          enemies[x].update(collision);
        }
      }
    } else if (phase==GamePhase.ONSTAIRS){
      if (askAgain == true) {
        drawDialogue = true;
      }
      askAgain = false;
      if (keyHandler.yesPressed) {
        resetFloor();
        drawDialogue = false;
        playSound(1);
      } else if (keyHandler.noPressed) {
        drawDialogue = false;
        phase=GamePhase.INGAME;
      }
    }
  }
  
  /**
   * Draws the needed information onto the screen of the GUI.
   * 
   * @param g, the Graphics from the Java GUI Swing, needs to be inherited
   * from for this component.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graph = (Graphics2D)g;
    playerInfo.getImage();
    if (phase==GamePhase.MENU) {
      menu.draw(graph);
    } else if (phase==GamePhase.GAMEOVER){
      menu.gameOver(graph);
    }
    else {
      tileM.getImage();
      if (phase==GamePhase.LOADGAME) {
        generatedMap = floorGen.GenerateMap(this);
        spawnPoint = tileM.findGreenSpot(generatedMap);
        playerInfo.setSpawnPoint(spawnPoint);
        objectPlacer.placeObjects(generatedMap);
        enemyPlacer.placeObjects(generatedMap, enemyStats);
        phase=GamePhase.INGAME;
      }
      tileM.draw(graph, generatedMap);
      for (int x=0; x<objects.length; x++) {
        if (objects[x]!=null) {
          objects[x].draw(graph, this);
        }
      }
      playerInfo.draw(graph);
      for (int x=0; x<enemies.length; x++) {
        if (enemies[x].getDead()==false) {
          enemies[x].getImage();
          enemies[x].draw(graph);
        }
      }
      ui.draw(graph);
      if (drawDialogue == true) {
        graph.setColor(Color.BLACK);
        graph.fillRoundRect(tileSize*2, tileSize/2, WidthScreen-(tileSize*4), tileSize*5, 35, 35);
        graph.setColor(Color.WHITE);
        graph.setFont(graph.getFont().deriveFont(Font.PLAIN,32F));
        graph.drawString("You have found the stairs.", tileSize*4, tileSize*2);
        graph.drawString("Do you want to go down?", tileSize*4, tileSize*3);
      }
    }
    graph.dispose();
  }
}
