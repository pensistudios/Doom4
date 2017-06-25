/**********************************************
 *                  Doom 4                    *
 *class: GameImage                            *
 *purpose: wrapper class for a image          *
 *author: Patrick                             *
 *                           *
 **********************************************/

import java.awt.image.*;
import java.io.File;

public class GameImage
{
  public BufferedImage image;
  boolean loaded = false;
  public String initPath;
  
  
  public GameImage()
  {
    
  }
  
  public GameImage(String path)
  {
    loadImage(path);  
  }
  
  public void loadImage(String path)
  {
    
    initPath = path;
    System.out.println("Image: " + path);
    try {
      image = javax.imageio.ImageIO.read(this.getClass().getResourceAsStream("/" + path));
      loaded = true;
    }
    catch (Exception e) {
      System.out.println("ERROR: Image \""+path+"\" could not be loaded...");
    }
  }

  public boolean success() { return loaded; }
  
} 
