/**********************************************
 *                  Doom 4                    *
 *class: GameSound                            *
 *purpose: wrapper class for sound            *
 *author: Patrick                             *
 *                           *
 **********************************************/

import java.applet.*;
import java.io.File;
import java.util.ArrayList;
import java.net.URL;

public class GameSound implements Runnable
{
  AudioClip sound;
  
  public GameSound(String path)
  {
    System.out.println("Sound: " + path);
    URL url = this.getClass().getResource("/" + path);
    try { sound = Applet.newAudioClip(url); }
    catch (Exception e) { System.out.println("ERROR: Sound \""+path+"\" could not be loaded..."); }
    
  }
  
  public void play()
  {
    run();
  }
  
  public void run()
  {
    sound.play();
  }
  
  public void stop()
  {
    sound.stop();
    
  }
  
  public void loop()
  {
    sound.loop();
  }
  
}
