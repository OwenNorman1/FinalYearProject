package FinalYearProject;


/**
 * This class contains an enum for the various directions in the game.
 * 
 * @author zjac122
 */
public enum Directions {
  UP("Up"), DOWN("Down"), LEFT("Left"), RIGHT("Right");

  private String DirectionName;
  
  /**
   * The constructor of the Symbol.
   * 
   * @param name the direction name in string form.
   */
  private Directions(String name) {
    this.DirectionName=name;
  }
  
  /**
   * Converts the enum value to a string and returns it.
   * 
   * @return the direction name in a string
   */
  @Override
  public String toString() {
    return this.DirectionName;
  }
}
