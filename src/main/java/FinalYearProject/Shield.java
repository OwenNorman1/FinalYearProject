package FinalYearProject;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * An extension of the object program that holds the shield specifically
 * @author zjac122
 */
public class Shield extends Object{
  
  /**
   * The constructor
   */
  public Shield(){
    setName("Shield");
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/Resources/Shield.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
