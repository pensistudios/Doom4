/**********************************************
 *                  Doom 4                    *
 *class: GameLoader                           *
 *purpose: loads the game from the level files*
 *author: Patrick                             *
 *     										  *
 **********************************************/

import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;



public class GameLoader 
{
	private int level;
	private BufferedReader br;
	private BufferedReader rmbr;
	public ArrayList enemies;
	public ArrayList items;  
	public Background background;
	public GameSound  music;
	private GameImage load;
	private GameImage bar;
	private int nextPixel;
	private final int pixelInc = 16;
	ResourceManager rm; 
	
	public GameLoader(GameImage l)
	{
		nextPixel = 405;
		bar = new GameImage("data\\menus\\bar.bmp");
		background = new Background();
		enemies = new ArrayList();
		items = new ArrayList();
		load = l;
		rm = new ResourceManager();
	}
	
	public void initGame(int ten, Graphics g, BufferStrategy b) throws FileNotFoundException, IOException
	{
		g.clearRect(0,0,1024,768);
		b.show();
		g.clearRect(0,0,1024,768);
		
		g.drawImage(load.image,0,0,null);
		//for(int k = 405; k < 600; k += 16)
		//	g.drawImage(bar.image,k,408,null);
		b.show();
		g.drawImage(load.image,0,0,null);
		int wc = wordCount(ten);
		
		int loadInc = 13/(wc); //max bars divided by lines in file
		switch(ten)
		{
			case 1:
				br = new BufferedReader(new FileReader("data\\levels\\mario.lvl"));
				rmbr = new BufferedReader(new FileReader("data\\levels\\mario.lvl"));
				System.out.println("mario.lvl opened...");
				break;
				
			case 2:
				br = new BufferedReader(new FileReader("data\\levels\\doom.lvl"));
				rmbr = new BufferedReader(new FileReader("data\\levels\\doom.lvl"));
				System.out.println("doom.lvl opened....");
				break;
			
				
			case 3:
				br = new BufferedReader(new FileReader("data\\levels\\city.lvl"));
				rmbr = new BufferedReader(new FileReader("data\\levels\\city.lvl"));  
				System.out.println("city.lvl opened...");
				break;
				
		}
		
		String temp2;
		boolean flemoidLoaded = false;
		boolean cyclopsLoaded = false;
		boolean impLoaded = false;
		boolean cyberLoaded = false;
		boolean bowserLoaded = false;
		boolean spoonjLoaded = false;
		boolean chainLoaded = false;
		
		while((temp2 = rmbr.readLine()) != null)
		{
	
			String[] tempA = temp2.split(":");
			
			if(!temp2.equals(""))
			{
				if(tempA[0].equals("enemy"))
				{
					if(tempA[4].equals("0") && !flemoidLoaded)
					{
						flemoidLoaded = true;
						rm.addEnemyImages(tempA[1], "Flem");
					}
					if(tempA[4].equals("6") && !cyclopsLoaded)
					{
						cyclopsLoaded = true;
						rm.addEnemyImages(tempA[1], "Cycl");
					}
					if(tempA[4].equals("2") && !impLoaded)
					{
						impLoaded = true;
						rm.addEnemyImages(tempA[1], "Imp");
					}
					if(tempA[4].equals("8") && !cyberLoaded)
					{
						cyberLoaded = true;
						rm.addEnemyImages(tempA[1], "Cyber");
					}
					if(tempA[4].equals("9") && !spoonjLoaded)
					{
						spoonjLoaded = true;
						rm.addEnemyImages(tempA[1], "Spoo");
					}
					if(tempA[4].equals("10") && !bowserLoaded)
					{
						bowserLoaded = true;
						rm.addEnemyImages(tempA[1], "Bowser");
					}
					if(tempA[4].equals("11") && !chainLoaded)
					{
						chainLoaded = true;
						rm.addEnemyImages(tempA[1], "Chain");
					}
					
				}
			}
		}
		
		String temp;
		
		while((temp = br.readLine()) != null)
		{
			String[] tempA = temp.split(":");

			if(!temp.equals(""))
			{
				
				if(tempA[0].equals("background"))
				{
					background.loadImage(tempA[1]);
					background.width = Integer.parseInt(tempA[4]);
					background.bounds = Integer.parseInt(tempA[2]);
					background.topbounds = Integer.parseInt(tempA[3]);
					System.out.println("background loaded...");
				}
				if(tempA[0].equals("enemy"))
				{
					Enemy tempE = new Enemy();
					tempE.initEnemy(rm,Integer.parseInt(tempA[4]));
					tempE.X = Integer.parseInt(tempA[2]);
					tempE.Y = Integer.parseInt(tempA[3]);
					tempE.realX = tempE.X;
					tempE.realY = tempE.Y;
					enemies.add(tempE);
					System.out.println("Enemy \"" + tempA[4] +"\" loaded...");
					
				}
				if(tempA[0].equals("item"))
				{
					
					HealthItem tempHI = new HealthItem(Integer.parseInt(tempA[4]));
					tempHI.initEntity(tempA[1]);
					tempHI.X = Integer.parseInt(tempA[2]);
					tempHI.Y = Integer.parseInt(tempA[3]);
					tempHI.realX = tempHI.X;
					tempHI.realY = tempHI.Y;
					items.add(tempHI);
					System.out.println("Item loaded...");
				}
				if(tempA[0].equals("music"))
				{
					music = new GameSound(tempA[1]);
					System.out.println("Music loaded...");
				}
				
				for(int k = 0; k < loadInc; k++)
				{	
					g.drawImage(bar.image,nextPixel,408,null);
					b.show();
					g.drawImage(bar.image,nextPixel,408,null);
					b.show();
					nextPixel += 16;
				}
				b.show();
				
			}			
		}
		
		int finish = 13 - (loadInc * wc);
		finish = (finish % 2 != 0)? (finish--) : finish;
		
		for(int k = 0; k < finish; k++)
		{	
			g.drawImage(bar.image,nextPixel,408,null);
			b.show();
			g.drawImage(bar.image,nextPixel,408,null);
			b.show();
			nextPixel += 16;
		}
		b.show();
		
	}
	
	private int wordCount(int level) throws FileNotFoundException, IOException
 	{
		BufferedReader temp = null;
		int counter = 0;
		
		switch(level)
		{
			case 1:
				temp = new BufferedReader(new FileReader("data\\levels\\mario.lvl")); 
				break;
			case 2:
				temp = new BufferedReader(new FileReader("data\\levels\\doom.lvl"));
				break;
			case 3:
				temp = new BufferedReader(new FileReader("data\\levels\\city.lvl"));
				break;
		}
		
		
		while(temp.readLine() != null)
		{
			counter++;
		}
		
		return counter;
	}
	
	
	
		
}