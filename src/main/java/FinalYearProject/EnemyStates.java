package FinalYearProject;

/**
 * This class creates an enum for the phases the enemy can be in
 * 
 * @author zjac122
 */
public enum EnemyStates {
  IDLE("Idle"),NODIRECTION("No Direction"),ACTIVE("Active"),ATTACKING("Attacking");
  
  private String StateName;
  
  /**
   * The constructor of the Symbol.
   * 
   * @param name the direction name in string form.
   */
  private EnemyStates(String name) {
    this.StateName=name;
  }
  
  /**
   * Converts the enum value to a string and returns it.
   * 
   * @return the direction name in a string
   */
  @Override
  public String toString() {
    return this.StateName;
  }
}
