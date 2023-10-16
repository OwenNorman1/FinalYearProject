package FinalYearProject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class handles the various key inputs that may be used in the various phases
 * of the game
 * 
 * @author zjac122
 */
public class KeyHandler implements KeyListener {
  
  public boolean upPressed, downPressed, leftPressed, rightPressed, 
    yesPressed, noPressed, enterPressed;

  /**
   * Checks if the key has been pressed
   */
  public void keyPressed(KeyEvent e) {
    int keycode = e.getKeyCode();  
    if (keycode == KeyEvent.VK_W) {
      upPressed = true;
    }
    if (keycode == KeyEvent.VK_A) {
      leftPressed = true;
    }
    if (keycode == KeyEvent.VK_S) {
      downPressed = true;
    }
    if (keycode == KeyEvent.VK_D) {
      rightPressed = true;
    }
    if (keycode == KeyEvent.VK_Y) {
      yesPressed = true;
    }
    if (keycode == KeyEvent.VK_N) {
      noPressed = true;
    }
    if (keycode == KeyEvent.VK_ENTER) {
      enterPressed = true;
    }
  }

  /**
   * Checks if the key has been released
   */
  public void keyReleased(KeyEvent e) {
    int keycode = e.getKeyCode();  
    if (keycode == KeyEvent.VK_W) {
      upPressed = false;
    }
    if (keycode == KeyEvent.VK_A) {
      leftPressed = false;
    }
    if (keycode == KeyEvent.VK_S) {
      downPressed = false;
    }
    if (keycode == KeyEvent.VK_D) {
      rightPressed = false;
    }
    if (keycode == KeyEvent.VK_Y) {
      yesPressed = false;
    }
    if (keycode == KeyEvent.VK_N) {
      noPressed = false;
    }
    if (keycode == KeyEvent.VK_ENTER) {
      enterPressed = false;
    }
  }

  public void keyTyped(KeyEvent e) {
  }
  
}
