/**********************************************
 *                  Doom 4                    *
 *class: Actor                                *
 *purpose: the base class for anything that   *
 *       moves in the game          *
 *author: Patrick                             *
 *                     *            *
 **********************************************/

import java.io.File;
import java.awt.*;

public class Actor extends GameEntity
{
  public int health;
  protected boolean isPunching;
  public boolean isLiving;
  public boolean isMovingLeft;
  public boolean isMovingRight;
  public boolean isStanding;
  public boolean reallyDead;
  private boolean isMovingReverse;
  private File dirPath;
  protected GameImage standing;
  protected GameImage punching;
  protected GameImage frameOne;
  protected GameImage frameTwo;
  protected GameImage frameThree;
  protected GameImage frameFour;
  protected GameImage death;
  protected GameImage dead;
  public  Rectangle bounds;
  public   Rectangle punch;
  private int currentMovementFrame = 1;
  private final int DEATH_FRAMES = 2;
  private int deathCount = 0;
  
  private int counter = 0;
  private int reallyDeadCounter = 0;
  public int moveSpeed;
  public int dMoveSpeed;
  public int damage;
  

  
  public Actor()
  {
    super();
    frameOne = new GameImage();
    frameTwo = new GameImage();
    frameThree = new GameImage();
    frameFour = new GameImage();
    standing = new GameImage();
    punching = new GameImage();
    death = new GameImage();
    dead = new GameImage();
    
  }
  //Default Actor loading
  //does not use resource managament
  public void initActor(String directoryPath)
  {
    health = 100;
    isLiving = true;
    reallyDead = false;
    
    
    dirPath = new File(directoryPath);
    String[] files = dirPath.list();
    for(int i = 0; i < files.length; i++)
    {
      if(files[i].equals("1.GIF"))
      {
        frameOne.loadImage(directoryPath + "\\" + files[i]);
      }
      if(files[i].equals("2.GIF"))
      {
        frameTwo.loadImage(directoryPath + "\\" + files[i]);
      }
      if(files[i].equals("3.GIF"))
      {
        frameThree.loadImage(directoryPath + "\\" + files[i]);
      }
      if(files[i].equals("4.GIF"))
      {
        frameFour.loadImage(directoryPath + "\\" + files[i]);
      }
      if(files[i].equals("1.GIF"))
      {
        standing.loadImage(directoryPath + "\\" + files[i]);
      }
      if(files[i].equals("punch.GIF"))
      {
        punching.loadImage(directoryPath + "\\" + files[i]);
      }
      if(files[i].equals("death.GIF"))
      {
        death.loadImage(directoryPath + "\\" + files[i]);
      }
      if(files[i].equals("dead.GIF"))
      {
        dead.loadImage(directoryPath + "\\" + files[i]);
      }
    }
    System.out.println("Hero Loaded...");
  }
  
  public void moveLeft()
  {
    if(isLiving)
    {
      isMovingLeft = true;
      isMovingRight = false;
      isPunching = false;
      isStanding = false;
    }
  }
  
  public void moveRight()
  {
    if(isLiving)
    {
      isMovingRight = true;
      isMovingLeft = false;
      isPunching = false;
      isStanding = false;
    }
  }
  
  public void punch()
  {
    if(isLiving)
    {
      isPunching = true;
      isStanding = false;
      isMovingLeft = false;
      isMovingRight = false;
    }
  }
  
  public void stand()
  {
    isPunching = false;
    isStanding = true;
    isMovingLeft = false;
    isMovingRight = false;
  }
  
  public void kill()
  {
    isLiving = false;
    damage = 0;
    stand();
  }
  
  public boolean attacked(int damage)
  {
    health -= damage;
    if(health <= 0)
    {
      kill();
      return true;      
    }
    return false;
  }
  
  public int returnState() /* STANDING=1 MOVINGLEFT=2 MOVINGRIGHT = 3 PUNCHING =4 */
  {
    if(isMovingLeft)
      return 2;
    else if(isPunching)
      return 4;
    else if(isStanding)
      return 1;
    else if(isMovingRight)
      return 3;
    else 
      return 0;
  }
  
  public void draw(Graphics g)
  {
    if (isLiving)
    {
      int temp = returnState();
    
      switch (temp)
      {
        case 1:
        {  
          g.drawImage(standing.image, X, Y, null);
          break;
        }
        case 2:
        case 3:
        {
          switch(currentMovementFrame)
          {
            case 1:
            {
              g.drawImage(frameOne.image, X, Y, null);
              currentMovementFrame++;
              counter++;
              break;
            }
            case 2:
            {
              g.drawImage(frameTwo.image,X,Y,null);
              currentMovementFrame++;
              counter++;
              break;
            }
            case 3:
            {
              g.drawImage(frameThree.image,X,Y,null);
              currentMovementFrame++;
              counter++;
              break;
            }
            case 4:
            {
              g.drawImage(frameFour.image,X,Y,null);
              currentMovementFrame++;
              counter++;
              break;
            }
            default:
            {
              g.drawImage(standing.image,X,Y,null);
            
            }
            
          }
          if(currentMovementFrame > 4)
          {
            currentMovementFrame = 1;
          }
          if(counter > 5)
          {
            counter = 0;
          }
          break;
        }
        case 4:
        {
          g.drawImage(punching.image, X, Y, null);
        }
      }
    }
    else if(deathCount <= DEATH_FRAMES)
    {

      g.drawImage(death.image,X,Y,null);
      deathCount++;
    }
    else
    {
      g.drawImage(dead.image,X,Y,null);
      if(reallyDeadCounter >= 6)
      {
        reallyDead = true;  
      }
      reallyDeadCounter++;
    }
  }
}
