package FinalYearProject;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class handles the music and whether to play them, stop them or loop through it.
 * 
 * @author zjac122
 */
public class Music {

  Clip clip;
  URL[] Sounds = new URL[5];
  
  /**
   * The constructor
   */
  public Music() {
    Sounds[0] = getClass().getResource("/Resources/Sounds/AmbientMusic.wav");
    Sounds[1] = getClass().getResource("/Resources/Sounds/DownStairs.wav");
    Sounds[2] = getClass().getResource("/Resources/Sounds/EnemyHit.wav");
    Sounds[3] = getClass().getResource("/Resources/Sounds/PlayerHit.wav");
    Sounds[4] = getClass().getResource("/Resources/Sounds/PickupItem.wav");
  }
  
  /**
   * Plays the music
   */
  public void play() {
    clip.start();
  }
  
  /**
   * Stops playing the music
   */
  public void stop() {
    clip.stop();
  }
  
  /**
   * Loops the music back to the start
   */
  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }
  
  /**
   * Sets the sound to whatever is needed for the task
   * 
   * @param i the number of the sound which is kept in the sounds list.
   */
  public void setSound(int i) {
    try {
      AudioInputStream audio = AudioSystem.getAudioInputStream(Sounds[i]);
      clip=AudioSystem.getClip();
      clip.open(audio);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
