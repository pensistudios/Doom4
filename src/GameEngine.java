/**********************************************
 *                  Doom 4                    *
 *class: GameEngine                           *
 *purpose: performs all the game logic        *
 *author: Patrick                             *
 *     										  *
 **********************************************/

import java.util.ArrayList;

public class GameEngine
{
	ArrayList enemies;
	ArrayList items;  
	Background background;
	int currentHeroId = 0;
	int currentHeroY = 670;
	int currentHeroX = 30;
	Hero heroOne;
	Hero heroTwo;
	int BOUNDS;
	int TOPBOUNDS;
	final int LEFTBOUNDS = 0;
	int ENDBOUNDS = 0;
	
	public GameEngine()
	{
		
	}
	
	public GameEngine(ArrayList e, ArrayList i, Background b)
	{
		enemies = e;
		items = i;
		background = b;
		ENDBOUNDS = background.width;
		BOUNDS = background.bounds;
		TOPBOUNDS = background.topbounds;
	}
	
	public int setUpHero(Hero hero)
	{
		currentHeroId++;
		switch(currentHeroId)
		{
			case 1:
				heroOne = hero;
				heroOne.X = currentHeroX;
				heroOne.Y = currentHeroY;
				break;
			case 2:
				heroTwo = hero;
				heroTwo.X = currentHeroX;
				heroTwo.Y = currentHeroY;
				break;
		}
		currentHeroX += 30;
		currentHeroY += 192;
		return currentHeroId;
	}
	
	private Hero getHero(int id)
	{
		switch(id)
		{
			case 1:
				return heroOne;
			case 2:
				return heroTwo;
				
		}
		return heroOne;
	}

	public Hero update(int id, boolean r,boolean l, boolean u, boolean d, boolean p)
	{
		switch(id)
		{
			case 1:
				heroOne = checkBounds(heroOne,r,l,u,d,p);
				return heroOne;
			case 2:
				heroTwo = checkBounds(heroTwo,r,l,u,d,p);
				return heroTwo;
				
		}
		return null;
	}
	
	
	private Enemy updateEnemy(int id, int index, boolean r,boolean l, boolean u, boolean d, boolean p)
	{
		Enemy tempEnemy = (Enemy) enemies.get(index);
		
		tempEnemy = checkEnemyBounds(id, tempEnemy,r,l,u,d,p);
		return tempEnemy;
	}
	
	public void updateEnemies(int id)
  	{
  		
  		Hero hero = null;
  		int heroX = 0;
  		
  		switch(id)
  		{
  			case 1: hero = heroOne;
  					heroX = heroOne.X+33;
  					break;
  			case 2: hero = heroTwo;
  					//heroX = 
  					break;
  		}
  		
  		
  		
  		boolean r = false;
  		boolean l = false;
  		boolean u = false;
  		boolean d = false;
  		boolean p = false;
  		
  		for(int i = 0; i < enemies.size(); i++)
  		{
  			Enemy tempEnemy = (Enemy) enemies.get(i);
  			
  			if(tempEnemy.isLiving)
  			{
	  			if(tempEnemy.X - hero.X < 800)
	  			{
	  				
	  				tempEnemy.moveLeft();
	  				
	  				if(hero.Y - tempEnemy.Y > 10)
	  				{
	  					l = true;
	  					d = true;
	  				}
	  				else if (hero.Y - tempEnemy.Y < -10)
	  				{
	  					l = true;
	  					u = true;
	  				}
	  				else if(Math.abs(hero.Y - tempEnemy.Y)  <= 10)
	  				{
	  					l = true;
	  				}
	  				if(tempEnemy.X - heroX < 10)
	  				{
	  					tempEnemy.punch();
	  					p = true;
	  				}
	  				else if(hero.realX > tempEnemy.realX)
	  				{
	  					l = true;
	  					
	  				}
	  			}
	  			else 
	  			{
	  				tempEnemy.stand();
	  			}
	  			
	  			tempEnemy = updateEnemy(id, i,r,l,u,d,p);
	  			enemies.set(i, tempEnemy);
	  			r = false;
	  			l = false;
	  			u = false;
	  			d = false;
	  			p = false;
	  			
  			}
  		}
  	}
	
	
	private Enemy checkEnemyBounds(int id, Enemy enemy, boolean r,boolean l, boolean u, boolean d, boolean p)
	{
		Hero hero = getHero(id);
		
		enemy.updateBounds();
		hero.updateBounds();
		
		if(p)
		{
						
			if(Math.abs(enemy.Y - hero.Y) <= 10)
			{
				int tempEY = enemy.Y;
				enemy.Y = hero.Y;		
				if(hero.bounds.contains(enemy.punch))
				{
					hero.attacked(enemy.damage);
				}
				
				enemy.Y = tempEY;
			}
		}
			
		
		else if(r && u)
		{
			if(enemy.Y - 10 > BOUNDS && enemy.realX + 10 < ENDBOUNDS)
			{
				enemy.moveRight();
				enemy.X = enemy.X+enemy.dMoveSpeed;
				enemy.realX += enemy.dMoveSpeed;
				enemy.Y = enemy.Y-enemy.dMoveSpeed;
				enemy.realY -= enemy.dMoveSpeed;
			}
		}
		else if(r && d)
		{
			if(enemy.Y + 10 < TOPBOUNDS && enemy.realX + 10 < ENDBOUNDS)
			{
				enemy.moveRight();
				enemy.X = enemy.X+enemy.dMoveSpeed;
				enemy.realX += enemy.dMoveSpeed;
				enemy.Y = enemy.Y+enemy.dMoveSpeed;
				enemy.realY += enemy.dMoveSpeed;
			}
		}
		else if(l && d)
		{
			if(enemy.Y + 10 < TOPBOUNDS && enemy.realX - 10 > LEFTBOUNDS)
			{
				enemy.moveLeft();
				enemy.X = enemy.X-enemy.dMoveSpeed;
				enemy.realX -= enemy.dMoveSpeed;
				enemy.Y = enemy.Y+enemy.dMoveSpeed;
				enemy.realY += enemy.dMoveSpeed;
			}
		}
		else if(l && u)
		{
			if(enemy.Y - 10 > BOUNDS && enemy.realX - 10 > LEFTBOUNDS)
			{
				enemy.moveLeft();
				enemy.X = enemy.X-enemy.dMoveSpeed;
				enemy.realX -= enemy.dMoveSpeed;
				enemy.Y = enemy.Y-enemy.dMoveSpeed;
				enemy.realY -= enemy.dMoveSpeed;
			}
		}
		else if(l)
		{
			if(enemy.realX - 20 > LEFTBOUNDS)
			{
				enemy.moveLeft();
				enemy.X = enemy.X - enemy.moveSpeed;
				enemy.realX -= enemy.moveSpeed;
			}
		}
		else if(r)
		{
			if(enemy.realX + 20 < ENDBOUNDS)	
			{
				enemy.moveRight();
				enemy.X = enemy.X + enemy.moveSpeed;
				enemy.realX += enemy.moveSpeed;
			}
		}
		else if (d)
		{
			if(enemy.Y + 20 < TOPBOUNDS)
			{
				enemy.moveRight();
				enemy.Y = enemy.Y + enemy.moveSpeed;
				enemy.realY += enemy.moveSpeed;
			}
		}
		else if (u)
		{
			if(enemy.Y - 20 > BOUNDS)
			{
				enemy.moveRight();
				enemy.Y = enemy.Y - enemy.moveSpeed;
				enemy.realY -= enemy.moveSpeed;
			}
		}
		else
		{
			enemy.stand();
		}
		
		return enemy;
				
	}

	
	private Hero checkBounds(Hero hero, boolean r,boolean l, boolean u, boolean d, boolean p)
	{
		for(int i = 0; i < items.size(); i++)
		{
			//ew
			
			HealthItem tempItem = (HealthItem) items.get(i);
			
			if(Math.abs(tempItem.X - hero.X) <= 10 && Math.abs(tempItem.Y - hero.Y) <= 60)
			{
				int h = tempItem.use();
				hero.health += h;
				if(hero.health > 100)
					hero.health = 100;
			}
		}
		
		if(p)
		{
			hero.punch();
			for(int i =  0; i < enemies.size(); i++)
			{
				Enemy tempEnemy = (Enemy) enemies.get(i);
				
				if(Math.abs(tempEnemy.Y - hero.Y) <= 10)
				{
					boolean x = false;
					int tempEY = tempEnemy.Y;
					tempEnemy.Y = hero.Y;
					tempEnemy.bounds.setLocation(tempEnemy.X, tempEnemy.Y);
					hero.updateBounds();
					
					if(tempEnemy.bounds.contains(hero.punch))
					{
						x = tempEnemy.attacked(hero.damage);
					}
					if(!x)
					{
						tempEnemy.Y = tempEY;
						enemies.set(i, tempEnemy);
					}
				}
			}
			
		}
		else if(r && u)
		{
			if(hero.Y - 10 > BOUNDS && hero.realX + 10 < ENDBOUNDS)
			{
				hero.moveRight();
				hero.X = hero.X+hero.dMoveSpeed;
				hero.realX += hero.dMoveSpeed;
				hero.Y = hero.Y-hero.dMoveSpeed;
				hero.realY -= hero.dMoveSpeed;
			}
		}
		else if(r && d)
		{
			if(hero.Y + 10 < TOPBOUNDS && hero.realX + 10 < ENDBOUNDS)
			{
				hero.moveRight();
				hero.X = hero.X+hero.dMoveSpeed;
				hero.realX += hero.dMoveSpeed;
				hero.Y = hero.Y+hero.dMoveSpeed;
				hero.realY += hero.dMoveSpeed;
			}
		}
		else if(l && d)
		{
			if(hero.Y + 10 < TOPBOUNDS && hero.realX - 10 > LEFTBOUNDS)
			{
				hero.moveLeft();
				hero.X = hero.X-hero.dMoveSpeed;
				hero.realX -= hero.dMoveSpeed;
				hero.Y = hero.Y+hero.dMoveSpeed;
				hero.realY += hero.dMoveSpeed;
			}
		}
		else if(l && u)
		{
			if(hero.Y - 10 > BOUNDS && hero.realX - 10 > LEFTBOUNDS)
			{
				hero.moveLeft();
				hero.X = hero.X-hero.dMoveSpeed;
				hero.realX -= hero.dMoveSpeed;
				hero.Y = hero.Y-hero.dMoveSpeed;
				hero.realY -= hero.dMoveSpeed;
			}
		}
		else if(l)
		{
			if(hero.realX - 20 > LEFTBOUNDS)
			{
				hero.moveLeft();
				hero.X = hero.X-hero.moveSpeed;
				hero.realX -= hero.moveSpeed;
			}
		}
		else if(r)
		{
			if(hero.realX + 20 < ENDBOUNDS)	
			{
				hero.moveRight();
				hero.X = hero.X + hero.moveSpeed;
				hero.realX += hero.moveSpeed;
			}
		}
		else if (d)
		{
			if(hero.Y + 20 < TOPBOUNDS)
			{
				hero.moveLeft();
				hero.Y = hero.Y + hero.moveSpeed;
				hero.realY += hero.moveSpeed;
			}
		}
		else if (u)
		{
			if(hero.Y - 20 > BOUNDS)
			{
				hero.moveLeft();
				hero.Y = hero.Y - hero.moveSpeed;
				hero.realY -= hero.moveSpeed;
			}
		}
		else
		{
			hero.stand();
		}
		return hero;
				
	}
	
	public ArrayList getEnemies()
	{
		if(didEnemiesMove())
		{
			
		}
		else
		{
	//		for(int i = 0; i < enemies.size(); i++)
	//		{
	//			Enemy tempEnemy = (Enemy) enemies.get(i);
	//			tempEnemy.X -= 20;
	//		}
		}
		return enemies;
	}
	
	private boolean didEnemiesMove()
	{
		return false;
	}
}