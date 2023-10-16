package FinalYearProject;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * An extension of the object class specifically for the treasure
 * @author zjac122
 */
public class Treasure extends Object{
  
  /**
   * The treasure constructor
   */
  public Treasure(){
    setName("Treasure");
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/Resources/Treasure.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
