/**********************************************
 *                  Doom 4                    *
 *class: ResourceManager                      *
 *purpose: maintains only one resource for    *
 *         multiple entities                  *
 *author: Patrick                             *
 *                           *
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
    name = name.toLowerCase();
    // File dirPath = new File(directoryPath);
    // String[] files = dirPath.list();
    String[] files = {
      "1.GIF",
      "2.GIF",
      "3.GIF",
      "4.GIF",
      "1.GIF",
      "punch.GIF",
      "death.GIF",
      "dead.GIF"
    };
    // for(int i = 0; i < files.length; i++)
    for(String path : files)
    {
      String fullPath = directoryPath + "/" + path;
      GameImage gi = new GameImage(fullPath);
      if (gi.success()) {
        String id = path.substring(0, path.length() - 4);
        images.put(name + id, gi);
      }
    }
    images.put(name+"s", images.get(name+"1"));
  }
  
  public GameImage getImage(String i)
  {
    i = i.toLowerCase();
    return (GameImage) images.get(i);
  }  
}
