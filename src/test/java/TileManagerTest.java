import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import FinalYearProject.GamePanel;
import FinalYearProject.TileManager;

class TileManagerTest {

  private TileManager tileM;
  private GamePanel gp;
  
  @BeforeEach
  public void setUp() {
    gp = new GamePanel();
    tileM = new TileManager(gp);
  }
  
  @Test
  void findSoleGreenSpot() {
    char[][] map = new char[gp.getWorldSizeCol()][gp.getWorldSizeRow()];
    for (char[] map1: map) {
      Arrays.fill(map1, 'W');
    }
    map[5][5]='G';
    int[] result = new int[2];
    result[0]=5;
    result[1]=5;
    assertEquals(Arrays.equals(tileM.findGreenSpot(map),result), true);
  }
}
