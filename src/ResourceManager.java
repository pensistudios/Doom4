/**********************************************
 *                  Doom 4                    *
 *class: ResourceManager                      *
 *purpose: maintains only one resource for    *
 *         multiple entities                  *
 *author: Patrick                             *
 *     										  *
 **********************************************/

import java.util.HashMap;
import java.io.File;

public class ResourceManager
{
	private HashMap images;
	
	public ResourceManager()
	{
		images = new HashMap<String,GameImage>();
		
	}
	
	public void addEnemyImages(String directoryPath, String name)
	{
		File dirPath = new File(directoryPath);
		String[] files = dirPath.list();
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].equals("1.GIF"))
			{
				images.put(name + "1", new GameImage(directoryPath + "\\" + files[i]));
			}
			if(files[i].equals("2.GIF"))
			{
				images.put(name + "2", new GameImage(directoryPath + "\\" + files[i]));
			}
			if(files[i].equals("3.GIF"))
			{
				images.put(name + "3", new GameImage(directoryPath + "\\" + files[i]));
			}
			if(files[i].equals("4.GIF"))
			{
				images.put(name + "4", new GameImage(directoryPath + "\\" + files[i]));
			}
			if(files[i].equals("1.GIF"))
			{
				images.put(name + "S", new GameImage(directoryPath + "\\" + files[i]));
			}
			if(files[i].equals("punch.GIF"))
			{
				images.put(name + "P", new GameImage(directoryPath + "\\" + files[i]));
			}
			if(files[i].equals("death.GIF"))
			{
				images.put(name + "Death", new GameImage(directoryPath + "\\" + files[i]));
			}
			if(files[i].equals("dead.GIF"))
			{
				images.put(name + "Dead", new GameImage(directoryPath + "\\" + files[i]));
			}
		}
	}
	
	public GameImage getImage(String i)
	{
		return (GameImage) images.get(i);
	}	
}