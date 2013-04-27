/**********************************************
 *                  Doom 4                    *
 *class: Enemy                                *
 *purpose: class that contains enemy attribs  *
 *author: Patrick                             *
 *     										  *
 **********************************************/

import java.awt.Rectangle;

public class Enemy extends Actor
{	
	public enum EnemyType { pistolguy,shotguy,imp,pinky,flemoid,walker,cyclops,boss,cyberdemon, spoonj, bowser, chainguy }
	
	EnemyType type;
		
	public Enemy()
	{
		super();
	}
	
	public void initEnemy(ResourceManager rm,int t)
	{
		isLiving = true;
		
		switch(t)
		{
			case 0:
				type = EnemyType.flemoid;
				break;
			case 1:
				type = EnemyType.shotguy;
				break;
			case 2:
				type = EnemyType.imp;
				break;
			case 3:
				type = EnemyType.pinky;
				break;
			case 4:
				type = EnemyType.pistolguy;
				break;
			case 5:
				type = EnemyType.walker;
				break;
			case 6:
				type = EnemyType.cyclops;
				break;
			case 7:
				type = EnemyType.boss;
				break;
			case 8:
				type = EnemyType.cyberdemon;
				break;
			case 9:
				type = EnemyType.spoonj;
				break;
			case 10:
				type = EnemyType.bowser;
				break;
			case 11:
				type = EnemyType.chainguy;
				break;
			
		}
		
		
		switch (type)
		{
			case flemoid:
			{
				health = 20;
				bounds = new Rectangle(X+80,Y,57,120);
				punch = new Rectangle(X-5,Y+50,7,16);
				moveSpeed = 16;
				dMoveSpeed = 8;
				damage = 5;
				frameOne = rm.getImage("Flem1");
				frameTwo = rm.getImage("Flem2");
				frameThree = rm.getImage("Flem3");
				frameFour = rm.getImage("Flem4");
				standing = rm.getImage("FlemS");
				punching = rm.getImage("FlemP");
				death = rm.getImage("FlemDeath");
				dead = rm.getImage("FlemDead");
				break;
			}
			
			case cyclops:
			{
				health = 30;
				bounds = new Rectangle(X+43,Y,81,108);
				punch = new Rectangle(X+11,Y+40,10,20);
				moveSpeed = 14;
				dMoveSpeed = 7;
				damage = 10;
				frameOne = rm.getImage("Cycl1");
				frameTwo = rm.getImage("Cycl2");
				frameThree = rm.getImage("Cycl3");
				frameFour = rm.getImage("Cycl4");
				standing = rm.getImage("CyclS");
				punching = rm.getImage("CyclP");
				death = rm.getImage("CyclDeath");
				dead = rm.getImage("CyclDead");
				break;
			}

			case cyberdemon:
			{
				health = 80;
				moveSpeed = 10;
				dMoveSpeed = 5;
				damage = 20;
				bounds = new Rectangle(X+18,Y+50,92,214);
				punch = new Rectangle(X,Y+71,10,10);
				frameOne = rm.getImage("Cyber1");
				frameTwo = rm.getImage("Cyber2");
				frameThree = rm.getImage("Cyber3");
				frameFour = rm.getImage("Cyber4");
				standing = rm.getImage("CyberS");
				punching = rm.getImage("CyberP");
				death = rm.getImage("CyberDeath");
				dead = rm.getImage("CyberDead");
				break;
				
			}

			case spoonj:
			{
				health = Integer.MAX_VALUE;
				moveSpeed = 8;
				dMoveSpeed = 4;
				damage = 5;
				bounds = new Rectangle(X+16, Y,68,111 );
				punch = new Rectangle(X,Y+47,14,14);
				frameOne = rm.getImage("Spoo1");
				frameTwo = rm.getImage("Spoo2");
				frameThree = rm.getImage("Spoo3");
				frameFour = rm.getImage("Spoo4");
				standing = rm.getImage("SpooS");
				punching = rm.getImage("SpooP");
				death = rm.getImage("SpooDeath");
				dead = rm.getImage("SpooDead");
				break;
			}

			case bowser:
			{
				health = 50;
				moveSpeed = 12;
				dMoveSpeed = 6;
				damage = 10;
				bounds = new Rectangle(X+5, Y, 80, 91);
				punch = new Rectangle(X, Y+38,10,10);
				frameOne = rm.getImage("Bowser1");
				frameTwo = rm.getImage("Bowser2");
				frameThree = rm.getImage("Bowser3");
				frameFour = rm.getImage("Bowser4");
				standing = rm.getImage("BowserS");
				punching = rm.getImage("BowserP");
				death = rm.getImage("BowserDeath");
				dead = rm.getImage("BowserDead");
				break;	
			}
			
			
			case imp:
			{
				health = 20;
				moveSpeed = 16;
				dMoveSpeed = 8;
				damage = 5;
				bounds = new Rectangle(X,Y,63,104);
				punch = new Rectangle(X-10,Y+29,17,30);
				frameOne = rm.getImage("Imp1");
				frameTwo = rm.getImage("Imp2");
				frameThree = rm.getImage("Imp3");
				frameFour = rm.getImage("Imp4");
				standing = rm.getImage("ImpS");
				punching = rm.getImage("ImpP");
				death = rm.getImage("ImpDeath");
				dead = rm.getImage("ImpDead");
				break;
			}
			
			case chainguy:
			{
				health = 30;
				moveSpeed = 12;
				dMoveSpeed = 6;
				damage = 10;
				bounds = new Rectangle(X+7,Y,50,119);
				punch = new Rectangle(X,Y+45,7,10);
				frameOne = rm.getImage("Chain1");
				frameTwo = rm.getImage("Chain2");
				frameThree = rm.getImage("Chain3");
				frameFour = rm.getImage("Chain4");
				standing = rm.getImage("ChainS");
				punching = rm.getImage("ChainP");
				death = rm.getImage("ChainDeath");
				dead = rm.getImage("ChainDead");
				break;
			}

			case shotguy:
			{
				health = 25;
				break;
			}
			
			case pinky:
			{
				health = 35;
				break;
			}
			
			case pistolguy:
			{
				health = 40;
				break;
			}
			
			case walker:
			{
				health= 45;
				break;
			}
			
			
			case boss:
			{
				health = 80;
				break;
			}
			
		}
	}
	
	public void updateBounds()
	{
		switch (type)
		{
			case flemoid:
			{
				bounds.setLocation(X,Y);
				punch.setLocation(X+5,Y+50);
				break;
			}
			case cyclops:
			{
				bounds.setLocation(X+43,Y);
				punch.setLocation(X+11,Y+40);
				break;
			}
			
			case cyberdemon:
			{
				bounds.setLocation(X+18,Y+50);
				punch.setLocation(X,Y+71);
				break;
			}
			case spoonj:
			{
				bounds.setLocation(X+16,Y);
				punch.setLocation(X,Y+47);
				break;
			}
			case bowser:
			{
				bounds.setLocation(X+5, Y);
				punch.setLocation(X, Y+38);
				break;
			}
			case imp:
			{
				bounds.setLocation(X,Y);
				punch.setLocation(X-10,Y+29);
				break;
			}
			case chainguy:
			{
				bounds.setLocation(X+7,Y);
				punch.setLocation(X,Y+45);
			}
		}
	}
	
	public boolean isCyberdemon()
	{
		return (type == EnemyType.cyberdemon)? true : false;
	}
	
}

