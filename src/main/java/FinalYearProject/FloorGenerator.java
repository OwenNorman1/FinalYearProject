package FinalYearProject;

import java.util.Arrays;

/**
 * This class randomly generates the floors of the game, establishing rooms
 * and corridors, then the map is sent to the Tile Manager to be generated.
 * 
 * @author zjac122
 */
public class FloorGenerator {
  private final int maxRooms = 10;
  private final int min = 2;
  private int column = 1;
  private int row = 1;
  private final int max = 10;

  /**
   * This class generates the rooms for the map
   * 
   * @param gp The GamePanel Class
   * @return the randomly generated map, which the TileManager can then
   * display
   */
  public char[][] GenerateMap(GamePanel gp) {
    char[][] map = new char[gp.getWorldSizeCol()+1][gp.getWorldSizeRow()+1];
    int roomlength[] = new int[maxRooms];
    int roomwidth[] = new int[maxRooms];
    int roomX[] = new int[maxRooms];
    int roomY[] = new int[maxRooms];
    boolean thingsPlaced = false;
    for (int x = 0; x < maxRooms; x++) {
      roomlength[x] = (int)(Math.random() * (max-min+1) + min);
      roomwidth[x] = (int)(Math.random() * (max-min+1) + min);
      roomX[x]= (int)(Math.random() * (gp.getWorldSizeCol()-roomwidth[x])+1);
      roomY[x]= (int)(Math.random() * (gp.getWorldSizeRow()-roomlength[x])+1);
      if (checkIntersect(x, roomY, roomX, roomwidth, roomlength) == true) {
        roomlength[x] = 0;
        roomwidth[x] = 0;
        roomX[x]= 0;
        roomY[x]= 0;
      }
    }
    for (char[] map1: map) {
      Arrays.fill(map1, 'W');
    }
    while(column < gp.getWorldSizeCol() && row  < gp.getWorldSizeRow()) {
      for (int x = 0; x < maxRooms; x++) {
        if (column >= roomX[x] && column <= roomX[x]+roomwidth[x]
            && row >= roomY[x] && row <= roomY[x]+roomlength[x]
            && roomlength[x] != 0) {
          map[column][row]='G';
        }
      }
      column++;
      if (column == gp.getWorldSizeCol()) {
        column = 0;
        row ++;
      }
    }
    while(thingsPlaced == false) {
      column = (int)(Math.random()*(gp.getWorldSizeCol()));
      row = (int)(Math.random()*(gp.getWorldSizeRow()));
      if (map[column][row]=='G') {
        map[column][row]='S';
        thingsPlaced = true;
      }
    }
    map = addRoads(map, roomX, roomY, roomwidth, roomlength);
    return map;
  }
  
  /**
   * This function adds roads inbetween the rooms in the class, allowing
   * for travel between them by the player. It finds the centre of each room
   * and connects them that way.
   * 
   * @param map the current map without roads
   * @param roomX the X values of the top left corner of each room
   * @param roomY the Y values of the top left corner of each room
   * @param roomwidth the width of the room
   * @param roomlength the length of the room
   * @return the updated map with roads added
   */
  public char[][] addRoads(char[][] map, int[] roomX, int[] roomY,
      int[] roomwidth, int[] roomlength) {
    int[][] roomcentre = new int[maxRooms][2];
    int x = 0;
    for (int n = 0; n < maxRooms; n++) {
      if (roomlength[n] != 0) {
        roomcentre[x][0] = (roomX[n] + (roomX[n] + roomwidth[n]))/2;
        roomcentre[x][1] = (roomY[n] +(roomY[n] + roomlength[n]))/2;
        x++;
      }
    }
    for (int n = 0; n < x-1; n++) {
      if (roomcentre[n][0] != 0 && roomcentre[n][1] != 0) {
        int randomway = (int)(Math.random()*10);
        if (randomway%2 == 0) {
          row = roomcentre[n][0];
          column = roomcentre[n+1][1];
        } else {
          row = roomcentre[n+1][0];
          column = roomcentre[n][1];
        }
        int tempx = roomcentre[n+1][0];
        int tempy = roomcentre[n+1][1];
        while(roomcentre[n][0] != row || roomcentre[n][1] != column ||
            tempx != row || tempy != column) {
          if (map[roomcentre[n][0]][roomcentre[n][1]] != 'S') {
            map[roomcentre[n][0]][roomcentre[n][1]]='P';
          } 
          if (map[tempx][tempy] != 'S') {
            map[tempx][tempy]='P';
          }
          if (roomcentre[n][0] > row) {
            roomcentre[n][0]--;
          } else if (roomcentre[n][0] < row) {
            roomcentre[n][0]++;
          } else if (roomcentre[n][1] > column) {
            roomcentre[n][1]--;
          } else if (roomcentre[n][1] < column) {
            roomcentre[n][1]++;
          }
          if (tempx > row) {
            tempx--;
          } else if (tempx < row) {
            tempx++;
          } else if (tempy > column) {
            tempy--;
          } else if (tempy < column) {
            tempy++;
          }
        }
      }
    }      
    return map;
  }

  
  /**
   * Checks if the current room intersects with a previously generated room
   * 
   * @param x the current room number
   * @param roomX the X values of the top left corner of each room
   * @param roomY the Y values of the top left corner of each room
   * @param roomwidth the width of the room
   * @param roomlength the length of the room
   * @return whether or not the room intersects with another room
   */
  public boolean checkIntersect(int x, int[] roomY, int[] roomX,
      int[] roomwidth, int[] roomlength) {
    for (int n = 0; n < x; n++) {
      int x1 = roomX[n]-1;
      int x2 = roomX[n]+roomwidth[n]+1;
      int x3 = roomX[x]-1;
      int x4 = roomX[x]+roomwidth[x]+1;
      int y1 = roomY[n]-1;
      int y2 = roomY[n]+roomlength[n]+1;
      int y3 = roomY[x]-1;
      int y4 = roomY[x]+roomlength[x]+1;
      if (x1 < x4 && x2 > x3 && y1 < y4 && y2 > y3) {
        return true;
      }
    }
    return false;
  }
}

