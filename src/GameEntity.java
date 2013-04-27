/**********************************************
 *                  Doom 4                    *
 *class: GameEntity                           *
 *purpose: base class for everything that     *
 *		   exists in the game                 *
 *author: Patrick                             *
 *     										  *
 **********************************************/

import java.awt.Graphics;

public abstract class GameEntity
{
	GameImage itsImage;
	public int X;
	public int Y;
	public int realX;
	public int realY;
	
	public GameEntity()
	{
		itsImage = new GameImage();
	}
	
	public void initEntity(String path)
	{
		itsImage.loadImage(path);
	}
	
	public void drawEntity(Graphics g)
	{
		g.drawImage(itsImage.image,X,Y,null);
	}

}