/**********************************************
 *                  Doom 4                    *
 *class: Item                                 *
 *purpose: base class for items               *
 *author: Patrick                             *
 *     										  *
 **********************************************/

import java.awt.Graphics;

public class Item extends GameEntity
{
	boolean isOnGround;
	
	public Item()
	{
		super();
		isOnGround = true;
	}
	
	public void pickUpItem()
	{
		isOnGround = false;
	}
	
	public void draw(Graphics g)
	{
		if(isOnGround)
		{
			g.drawImage(itsImage.image,X,Y,null);
		}
	}	
	
}
