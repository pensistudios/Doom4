/**********************************************
 *                  Doom 4                    *
 *class: Game                                 *
 *purpose: contains main method, creates game *
 *author: Patrick                             *
 *                           *
 **********************************************/

import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Game 
{
    public static void main(String[] args) 
    {
      // File watermark = new File("data\\menus\\watermark.bmp");
      
      // LevelFrame level =  new LevelFrame(watermark);
      
      // while(!level.isReady())
      // {
        
      // }
      
      // int i = level.returnAnswer();
      
      // level.hide();

      int i = 3;     
      if (args.length >= 1) {
        i = Integer.parseInt(args[0]);
      }

        GameFrame frame = new GameFrame(i); //1 mario 2 doom 3 city
        // frame.setVisible(true);
    }
}

class LevelFrame extends JFrame
{
  LevelSelector ls;
  
  public LevelFrame(File file)
  {
    super();
    
    try { ls = new LevelSelector(file); }
    catch (Exception e) {}  
    
    add(ls);
    
    setSize(new Dimension(500, 300));
    setVisible(true);
  }
  
  public boolean isReady()
  {
    return ls.ready;
  }
  
  public int returnAnswer()
  {
    return ls.returnAnswer();
  }
}

class LevelSelector extends JPanel implements ActionListener
{
  BufferedImage img;
  TexturePaint texture;
  PRadioButton doomButton;
  PRadioButton cityButton;
  PRadioButton marioButton;
  boolean ready = false;
  JButton ok;
  
  
  public LevelSelector(File file) throws IOException
  {
    super();
    setBorder(BorderFactory.createTitledBorder("Level"));
    marioButton = new PRadioButton("Mario");
    doomButton = new PRadioButton("Doom");
    cityButton = new PRadioButton("City");
    ok = new JButton("Ok");
    
    ok.addActionListener(this);
    
    
    ButtonGroup b = new ButtonGroup();
    b.add(marioButton);
    b.add(doomButton);
    b.add(cityButton);
    
    
    add(marioButton);
    add(doomButton);
    add(cityButton);
    add(ok);
    
    img = javax.imageio.ImageIO.read(file);
    Rectangle rect = new Rectangle(0,0, img.getWidth(null), img.getHeight(null));
    texture = new TexturePaint(img, rect);
    
    setOpaque(false);
    
    
    setPreferredSize(new Dimension(500, 300));
    setVisible(true);
  }
  
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;
    g2.setPaint(texture);
    g.fillRect(0,0,getWidth(),getHeight());
    super.paintComponent(g);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    ready = true;
  }
  
  public int returnAnswer()
  {
    if(marioButton.checked)
      return 1;
    if(doomButton.checked)
      return 2;
    if(cityButton.checked)
      return 3;
    
    return -1;
  }
}

class PRadioButton extends JRadioButton implements ActionListener
{
  public boolean checked = false;
  
  public PRadioButton(String title)
  {
    super(title);
    addActionListener(this);
    setOpaque(false);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    checked = (checked)?false:true;
  }
}
