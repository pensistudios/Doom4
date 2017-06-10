/**********************************************
 *                  Doom 4                    *
 *class: Background                           *
 *purpose: a special type of image that has   *
 *       side-scrolling code in it  *
 *author: Patrick                             *
 *                     *            *
 **********************************************/

import java.awt.*;
import java.awt.image.*;

public class Background extends GameImage
{
  public int width;      
   public int pWidth = 1024;
   private int pHeight = 768;    

    public int moveSize;       
    private boolean isMovingRight;  
    private boolean isMovingLeft;
    public int xImHead;
    
    public int bounds;
    public int topbounds;
    
    public void moveRight()
    { 
      isMovingRight = true;
      isMovingLeft = false;
    }

    public void moveLeft()
    {
      isMovingRight = false;
      isMovingLeft = true;
    }
    
    public void stayStill()
    { 
      isMovingRight = false;
      isMovingLeft = false;
    }
    
    public void update()
    {
      if (isMovingRight)
          xImHead = (xImHead + moveSize) % width;
      else if (isMovingLeft)
          xImHead = (xImHead - moveSize) % width;
    }
    
    public void display(Graphics g)
    {
      if (xImHead == 0)
      {   
          draw(g, image,0, pWidth, 0, pWidth);
        }
      else if ((xImHead > 0) && (xImHead < pWidth)) 
      {  
          draw(g, image, 0, xImHead, width-xImHead, width);   
          draw(g, image, xImHead, pWidth, 0, pWidth-xImHead);  
      }
      
      else if (xImHead >= pWidth)   
      {
          draw(g, image, 0, pWidth, width-xImHead, width-xImHead+pWidth);  
        }
      else if ((xImHead < 0) && (xImHead >= pWidth-width))
      {
          draw(g, image, 0, pWidth, -xImHead, pWidth-xImHead);  
        }
      else if (xImHead < pWidth-width) 
      {
          draw(g, image, 0, width+xImHead, -xImHead, width);  
          draw(g, image, width+xImHead, pWidth, 
                  0, pWidth-width-xImHead);  
      }
    }
    
    private void draw(Graphics g, BufferedImage im, int scrX1, int scrX2, int imX1, int imX2)
    { 
      g.drawImage(im, scrX1, 0, scrX2, pHeight, imX1, 0,  imX2, pHeight, null);
    } 
 
}
