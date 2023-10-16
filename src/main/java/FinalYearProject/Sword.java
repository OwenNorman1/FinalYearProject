package FinalYearProject;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * An extension of the object program that holds the sword specifically
 * @author zjac122
 */
public class Sword extends Object{
  
  /**
   * The constructor
   */
  public Sword(){
    setName("Sword");
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/Resources/Sword.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}