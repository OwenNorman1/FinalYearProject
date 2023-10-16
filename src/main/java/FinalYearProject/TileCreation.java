package FinalYearProject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TileCreation {

  JFrame frame= new JFrame();
  JLabel[][] grid;
  
  public TileCreation(int width, int length) {
    frame.setLayout(new GridLayout(width,length));
    grid = new JLabel[width][length];
    for (int y=0; y<length; y++) {
      for (int x=0; x<width; x++) {
        grid[x][y]=new JLabel("(" +x+ "," +y+ ")");
        grid[x][y].setPreferredSize(new Dimension(75,75));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        grid[x][y].setBorder(border);
        frame.add(grid[x][y]);
      }
    }
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
  
  public static void main(String[] args) {
    new TileCreation(9,9);
  }

}