/**********************************************
 *                  Doom 4                    *
 *class: Hero                                 *
 *purpose: controlled by player               *
 *author: Patrick                             *
 *                           *
 **********************************************/

import java.awt.Rectangle;

public class Hero extends Actor
{
  public HeroType type;
  
  public enum HeroType {chex, doom}
  
  GameSound punchSound;
  
  public Hero()
  {
    super();
    stand();
    type = HeroType.chex;
    setBounds();
    
  }
  
  public Hero(HeroType ht)
  {
    super();
    stand();
    type = ht;
    setBounds();
    punchSound = new GameSound("data/audio/punch.wav");
  }
  
  public void setBounds()
  {
    switch (type)
    {
      case chex:
      {
        bounds = new Rectangle(X+25,Y,40,108);
        punch = new Rectangle(X+81,Y+30,5,14);
        moveSpeed = 20;
        dMoveSpeed = 10;
        damage = 15;
        break;
      }
      
      case doom:
      {
        bounds = new Rectangle(X+14,Y,30,102);
        punch = new Rectangle(X+62,Y+33,22,20);
        moveSpeed = 20;
        dMoveSpeed = 10;
        damage = 15;
        break;  
      }
    }
  }
  
  public void punch()
  {
    if(isLiving)
    {
      punchSound.play();
      isPunching = true;
      isStanding = false;
      isMovingLeft = false;
      isMovingRight = false;
    }
  }  
  
  public void updateBounds()
  {
    switch (type)
    {
      case chex:
      {
        bounds.setLocation(X+25,Y);
        punch.setLocation(X+81,Y+30);
        break;
      }
      
      case doom:
      {
        bounds.setLocation(X+14,Y);
        punch.setLocation(X+62,Y+33);
        break;  
      }
    }
  }
  
}
