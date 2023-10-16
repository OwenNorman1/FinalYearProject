package FinalYearProject;

/**
 * This class creates the nodes that are used by the pathfinder class to pathfind
 * towards the player.
 * 
 * @author zjac122
 */
public class Node {
  
  Node parent;
  private int col;
  private int row;
  int startCost;
  int endCost;
  int totalCost;
  boolean solid;
  boolean open;
  boolean checked;
  
  /**
   * The constructor
   * @param col the column of the node
   * @param row the row of the node
   */
  public Node(int col, int row) {
    this.col=col;
    this.row=row;
  }
  
  public void setCol(int col) {
    this.col=col;
  }
  
  public int getCol() {
    return this.col;
  }
  
  public void setRow(int row) {
    this.row=row;
  }
  
  public int getRow() {
    return this.row;
  }
}
