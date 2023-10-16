import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import FinalYearProject.GamePanel;
import FinalYearProject.Player;

class PlayerTest {
  
  private GamePanel gp;
  private Player player;

  @BeforeEach
  public void setUp() {
    gp = new GamePanel();
    player = new Player(gp, null);
    gp.run();
  }
  
  @Test
  void pickUpTreasure() {
    player.pickUpItems(0);
    assertEquals(player.getTreasure(), 20);
  }
  
  @Test
  void pickUpTreasures() {
    player.pickUpItems(0);
    player.pickUpItems(1);
    assertEquals(player.getTreasure(), 40);
  }
  
  @Test
  void pickUpPotionFull() {
    player.pickUpItems(7);
    assertEquals(player.getTreasure(), 10);
    assertEquals(player.getHealth(), 20);
  }
  
  @Test
  void pickUpPotionHurt() {
    player.setHealth(1);
    player.pickUpItems(7);
    assertEquals(player.getTreasure(), 0);
    assertEquals(player.getHealth(), 6);
  }
  
  @Test
  void pickUpPotionNearFull() {
    player.setHealth(17);
    player.pickUpItems(7);
    assertEquals(player.getTreasure(), 0);
    assertEquals(player.getHealth(), 20);
  }
  
  @Test
  void pickUpSword() {
    player.pickUpItems(5);
    assertEquals(player.getAttack(), 15);
  }
  
  @Test
  void pickUpShield() {
    player.pickUpItems(6);
    assertEquals(player.getDefense(), 15);
  }
}
