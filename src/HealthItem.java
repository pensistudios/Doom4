/**********************************************
 *                  Doom 4                    *
 *class: HealthItem                           *
 *purpose: class for items that give you hp   *
 *author: Patrick                             *
 *                           *
 **********************************************/

public class HealthItem extends Item
{
  private int health;
  
  public HealthItem(int h)
  {
    super();
    health = h;
  }
  
  public int use()
  {
    pickUpItem();
    return health;
  } 
}
