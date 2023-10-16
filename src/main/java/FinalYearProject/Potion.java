package FinalYearProject;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * An extension of the object program that holds the potion specifically
 * @author zjac122
 */
public class Potion extends Object{
  
  /**
   * The constructor for potion
   */
  public Potion(){
    setName("Potion");
    try {
      image=ImageIO.read(getClass().getResourceAsStream("/Resources/Potion.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
