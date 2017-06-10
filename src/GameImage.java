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
  
  
  public GameImage()
  {
    
  }
  
  public GameImage(String path)
  {
    loadImage(path);  
  }
  
  public void loadImage(String path)
  {
    System.out.println("Image: " + path);
    try { image = javax.imageio.ImageIO.read(new File(path)); }
    catch (Exception e) { System.out.println("Image \""+path+"\" could not be loaded..."); }
  }
  
} 
