import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import FinalYearProject.Enemy;
import FinalYearProject.GamePanel;
import FinalYearProject.Player;

class EnemyTest {
  private Player player;
  private Enemy enemy;
  private GamePanel gp;
  
  @BeforeEach
  public void setUp() {
    gp = new GamePanel();
    player = new Player(gp, null);
    enemy = new Enemy(gp, new int[] {20,5,5});
    enemy.setXAxis(20*gp.getTilesize());
    enemy.setYAxis(20*gp.getTilesize());
  }
  
  @Test
  void rightTrue() {
    player.setXAxis((15*gp.getTilesize())-gp.getWidthScreen());
    player.setYAxis((20*gp.getTilesize())-gp.getHeightScreen());
    assertEquals(enemy.playerInSight(player,25), true);
  }
  
  @Test
  void rightFalse() {
    player.setXAxis((35*gp.getTilesize())-gp.getWidthScreen());
    player.setYAxis((20*gp.getTilesize())-gp.getHeightScreen());
    assertEquals(enemy.playerInSight(player,25), false);
  }
  
  @Test
  void leftTrue() {
    player.setXAxis((25*gp.getTilesize())-gp.getWidthScreen());
    player.setYAxis((20*gp.getTilesize())-gp.getHeightScreen());
    assertEquals(enemy.playerInSight(player,25), true);
  }
  
  @Test
  void leftFalse() {
    player.setXAxis((5*gp.getTilesize())-gp.getWidthScreen());
    player.setYAxis((20*gp.getTilesize())-gp.getHeightScreen());
    assertEquals(enemy.playerInSight(player,25), false);
  }
  
  @Test
  void upTrue() {
    player.setXAxis((20*gp.getTilesize())-gp.getWidthScreen());
    player.setYAxis((25*gp.getTilesize())-gp.getHeightScreen());
    assertEquals(enemy.playerInSight(player,25), true);
  }
  
  @Test
  void upFalse() {
    player.setXAxis((20*gp.getTilesize())-gp.getWidthScreen());
    player.setYAxis((5*gp.getTilesize())-gp.getHeightScreen());
    assertEquals(enemy.playerInSight(player,25), false);
  }
  
  @Test
  void downTrue() {
    player.setXAxis((20*gp.getTilesize())-gp.getWidthScreen());
    player.setYAxis((15*gp.getTilesize())-gp.getHeightScreen());
    assertEquals(enemy.playerInSight(player,25), true);
  }
  
  @Test
  void downFalse() {
    player.setXAxis((20*gp.getTilesize())-gp.getWidthScreen());
    player.setYAxis((35*gp.getTilesize())-gp.getHeightScreen());
    assertEquals(enemy.playerInSight(player,25), false);
  }

}
