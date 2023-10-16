package FinalYearProject;

import java.util.ArrayList;

/**
 * This class creates the pathfinding for the enemies so that they can find the player
 * using A* search.
 * 
 * @author zjac122
 */
public class Pathfinder {
  
  GamePanel gp;
  Node[][] nodes;
  boolean goal = false;
  Node startNode, endNode, currentNode;
  ArrayList<Node> openNodes;
  ArrayList<Node> pathNodes;
  int step = 0;
  
  /**
   * The constructor
   * 
   * @param gp the gamepanel
   */
  public Pathfinder(GamePanel gp) {
    this.gp=gp;
    this.openNodes=new ArrayList<Node>();
    this.pathNodes=new ArrayList<Node>();
    this.createNodes();
  }
  
  /**
   * Creates the Nodes needed on each sqaure of the game
   */
  public void createNodes() {
    nodes = new Node[gp.getWorldSizeCol()][gp.getWorldSizeRow()];
    int currentcol=0;
    int currentrow=0;
    while (currentcol < gp.getWorldSizeCol() && currentrow < gp.getWorldSizeRow()) {
      nodes[currentcol][currentrow] = new Node(currentcol, currentrow);
      currentcol++;
      if (currentcol == gp.getWorldSizeCol()) {
        currentcol=0;
        currentrow++;
      }
    }
  }
  
  /**
   * Resets the nodes after a pathfind is complete
   */
  public void resetNodes() {
    int currentcol=0;
    int currentrow=0;
    while (currentcol < gp.getWorldSizeCol() && currentrow < gp.getWorldSizeRow()) {
      nodes[currentcol][currentrow].open=false;
      nodes[currentcol][currentrow].checked=false;
      nodes[currentcol][currentrow].solid=false;
      currentcol++;
      if (currentcol == gp.getWorldSizeCol()) {
        currentcol=0;
        currentrow++;
      }
    }
    openNodes.clear();
    pathNodes.clear();
    goal = false;
    step = 0;
  }
  
  /**
   * Sets the value of each node, including the start and end node and sets a 
   * node to solid if a creature cannot go through it.
   * 
   * @param startcol the column of the start node
   * @param startrow the row of the start node
   * @param endcol the column of the end node
   * @param endrow the row of the end node
   */
  public void setNodes(int startcol, int startrow, int endcol, int endrow) {
    resetNodes();
    startNode=nodes[startcol][startrow];
    endNode=nodes[endcol][endrow];
    currentNode=startNode;
    openNodes.add(currentNode);
    int currentcol=0;
    int currentrow=0;
    while (currentcol < gp.getWorldSizeCol() && currentrow < gp.getWorldSizeRow()) {
      if (gp.tileM.getTile(currentcol, currentrow) == 'W') {
        nodes[currentcol][currentrow].solid = true;
      }
      setNodeCost(nodes[currentcol][currentrow]);
      currentcol++;
      if (currentcol == gp.getWorldSizeCol()) {
        currentcol=0;
        currentrow++;
      }
    }
  }
  
  /**
   * Sets the cost of a node for the search algorithm
   * 
   * @param node the current node
   */
  public void setNodeCost(Node node) {
    int distX = node.getCol() - startNode.getCol();
    int distY = node.getRow() - startNode.getRow();
    node.startCost=distX+distY;
    distX = node.getCol() - endNode.getCol();
    distY = node.getRow() - endNode.getRow();
    node.endCost=distX+distY;
    node.totalCost=node.startCost+node.endCost;
  }
  
  /**
   * Searches through the nodes to find the quickest way from the start node to
   * the end node
   * 
   * @return whether or not a way to the node was found.
   */
  public boolean Search() {
    try {
      while (goal == false && step < 200) {
        int currentcol = currentNode.getCol();
        int currentrow = currentNode.getRow();
        currentNode.checked=true;
        openNodes.remove(currentNode);
        if (currentrow-1 >= 0) {
          openUp(nodes[currentcol][currentrow-1]);
        }
        if (currentcol-1 >= 0) {
          openUp(nodes[currentcol-1][currentrow]);
        }
        if (currentrow+1 < gp.getWorldSizeRow()) {
          openUp(nodes[currentcol][currentrow+1]);
        }
        if (currentcol+1 < gp.getWorldSizeCol()) {
          openUp(nodes[currentcol+1][currentrow]);
        }
        int bestNode=0;
        int bestNodeScore=999;
        for (int i = 0; i < openNodes.size(); i++) {
          if (openNodes.get(i).totalCost < bestNodeScore) {
            bestNode=i;
            bestNodeScore=openNodes.get(i).totalCost;
          }
          if (openNodes.get(i).totalCost == bestNodeScore && 
              openNodes.get(i).endCost < openNodes.get(bestNode).endCost) {
            bestNode=i;
          }
        }
        if (openNodes.size() == 0) {
          return false;
        }
        currentNode = openNodes.get(bestNode);
        if (currentNode == endNode) {
          retrace();
          return true;
        }
        step++;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  /**
   * Puts a node in the open list so that it can be checked if it is allowed
   * 
   * @param node the node to be opened
   */
  public void openUp(Node node) {
    if (node.open == false && node.checked == false && node.solid == false) {
      node.open = true;
      node.parent=currentNode;
      openNodes.add(node);
    }
  }
  
  /**
   * Traces through each node to find the path from the start node to the end
   * node
   */
  public void retrace() {
    Node current = endNode;
    while (current != startNode) {
      pathNodes.add(0, current);
      current=current.parent;
    }
  }
}
