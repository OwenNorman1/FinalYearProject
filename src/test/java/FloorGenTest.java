import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import FinalYearProject.FloorGenerator;

class FloorGenTest {
  private FloorGenerator floorgen;
  private int[] x = new int[10];
  private int[] y = new int[10];
  private int[] width = new int [10];
  private int[] height = new int[10];
  
  @BeforeEach
  public void setUp() {
    floorgen = new FloorGenerator();
  }
  
  @Test
  void checkIntersect1Room() {
    x[0]=0;
    y[0]=0;
    width[0]=5;
    height[0]=5;
    assertEquals(floorgen.checkIntersect(0,x,y,width,height), false);
  }
  
  @Test
  void checkIntersectNoIntersect() {
    x[0]=0;
    x[1]=15;
    y[0]=0;
    y[1]=15;
    width[0]=5;
    width[1]=5;
    height[0]=5;
    height[1]=5;
    assertEquals(floorgen.checkIntersect(1, y, x, width, height), false);
  }

  @Test
  void checkIntersectEntirely() {
    x[0]=0;
    x[1]=5;
    y[0]=0;
    y[1]=5;
    width[0]=15;
    width[1]=5;
    height[0]=15;
    height[1]=5;
    assertEquals(floorgen.checkIntersect(1, y, x, width, height), true);
  }
  
  @Test 
  void IntersectPartTop() {
    x[0]=2;
    x[1]=0;
    y[0]=0;
    y[1]=0;
    width[0]=5;
    width[1]=5;
    height[0]=5;
    height[1]=5;
    assertEquals(floorgen.checkIntersect(1, y, x, width, height), true);
  }
  
  @Test
  void IntersectPartSide() {
    x[0]=0;
    x[1]=0;
    y[0]=2;
    y[1]=0;
    width[0]=5;
    width[1]=5;
    height[0]=5;
    height[1]=5;
    assertEquals(floorgen.checkIntersect(1, y, x, width, height), true);
  }
  
  @Test
  void IntersectManyRooms() {
    x[0]=0;
    x[1]=25;
    x[2]=22;
    y[0]=0;
    y[1]=25;
    y[2]=22;
    width[0]=5;
    width[1]=5;
    width[2]=5;
    height[0]=5;
    height[1]=5;
    height[2]=5;
    assertEquals(floorgen.checkIntersect(2, y, x, width, height), true);
  }
  
  @Test
  void NoIntersectManyRooms() {
    x[0]=0;
    x[1]=25;
    x[2]=35;
    y[0]=0;
    y[1]=25;
    y[2]=35;
    width[0]=5;
    width[1]=5;
    width[2]=5;
    height[0]=5;
    height[1]=5;
    height[2]=5;
    assertEquals(floorgen.checkIntersect(2, y, x, width, height), false);
  }
}
