package FinalYearProject;

/**
 * This class holds all of the enums for the various phases that the game can be in.
 * @author zjac122
 */
public enum GamePhase {
  INGAME("In Game"), ONSTAIRS ("On Stairs"), LOADGAME("Load Game"), MENU("Menu"),
  GAMEOVER("Game Over");
  
  private String GamePhase;
  
  /**
   * The constructor for the class
   * @param name the string version of the GamePhase
   */
  private GamePhase(String name) {
    this.GamePhase=name;
  }
  
  /**
   * Returns the string version of the gamephase
   */
  @Override
  public String toString() {
    return this.GamePhase;
  }
}
