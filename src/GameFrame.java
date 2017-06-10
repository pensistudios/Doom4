/**********************************************
 *                  Doom 4                    *
 *class: GameFrame                            *
 *purpose: creates the game, draws the game   *
 *author: Patrick                             *
 *                           *
 **********************************************/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;


public class GameFrame extends JFrame implements Runnable 
{
  private Thread animator; 
  private volatile boolean running = false;
  
  private volatile boolean isOverQuitButton = false;
    
    public boolean rightKeyPresed = false;
    public boolean upKeyPresed = false;
    public boolean leftKeyPresed = false;
    public boolean downKeyPresed = false;
    public boolean punchKeyPresed = false;
    
    private Rectangle pauseArea;
    private volatile boolean isPaused = false;
  
  private int height, width;
  private int goFrameCounter = 0;
  private static final int NUM_BUFFERS = 2;
  private static final int GO_WAIT_FRAMES = 30;
  private long period;
  
  private GraphicsDevice gd;
    private Graphics gScr;
    private BufferStrategy bufferStrategy;
    
    private Rectangle singlePlayerArea;
    private Rectangle multiPlayerArea;
    private Rectangle quitArea;
    
    private volatile boolean clickedQuitArea;
    private volatile boolean clickedMultiArea;
    private volatile boolean clickedSingleArea;
    
    private Rectangle heroButtonArea;
    private Rectangle heroTwoButtonArea;
    
    private volatile boolean clickedheroArea;
    private volatile boolean clickedHeroTwoArea;
  
  boolean rightKeyPressed = false;
  boolean leftKeyPressed = false;
  boolean upKeyPressed = false;
  boolean downKeyPressed = false;
  boolean punchKeyPressed = false;
  boolean creditsKeyPressed = false;
  boolean quitKeyPressed = false;
  
  GameLoader gameLoader;
  GameEngine gameEngine;
  Hero hero;
  int heroId;
  
  ArrayList enemies;
  ArrayList items;
  Background background;
  
  GameImage heroMenu;
  GameImage menu;
  GameImage healthMenu;
  GameImage go;
  GameImage gameOver;
  
  GameSound menuSound;
  GameSound levelMusic;
  
  int level;
  
  
  public GameFrame(int l)
  {
    super("Doom 4: Super Double Chex Quest");
    
    level = l;
    
    heroMenu = new GameImage("data\\menus\\hselection.jpg");
    menu = new GameImage("data\\menus\\menu.jpg");
    healthMenu = new GameImage("data\\menus\\health.gif");
    go = new GameImage("data\\game\\go.gif");
    gameOver = new GameImage("data\\game\\gameover.bmp");
    
    GameImage load = new GameImage("data\\menus\\loading.JPG");
    gameLoader = new GameLoader(load);
    
    menuSound = new GameSound("data\\audio\\intro.mid");
    menuSound.play();
    
    initFullScreen();
    
    addKeyListener( new KeyAdapter( ) {
        public void keyPressed(KeyEvent e)
        { processKey(e); }
        public void keyReleased(KeyEvent e)
         { processKeyRelease(e); }
        });
        
        addMouseListener( new MouseAdapter() {
        public void mouseClicked(MouseEvent e)
        { testPress(e.getX(), e.getY()); }
      });
        
        singlePlayerArea = new Rectangle(767,277,161,20);
        multiPlayerArea = new Rectangle(520,419,152,20);
        quitArea = new Rectangle(861,618,56,20);
        heroButtonArea = new Rectangle(149,640,205,30);
        heroTwoButtonArea = new Rectangle(676,640,200,30);
        

    startThread();
  }
  
  public void run() 
  {
    running = true;
    
    init();
    
    int choice = gameMenu();
    
    switch(choice)
    {
      case 1: //Single Player
      {
      
        hero = heroChoiceMenu();
        
        menuSound.stop();
                  
        loadGame(level); 
        
        gameEngine = new GameEngine(gameLoader.enemies, gameLoader.items, gameLoader.background);
        heroId = gameEngine.setUpHero(hero);
        
        levelMusic.loop();
        
        System.out.println("===Game Started===");
        
        while(running) 
        {
            gameUpdate();     
             screenUpdate();
  
  
              try 
              {
                  Thread.sleep(100);  
              }
              catch(InterruptedException ex){}
              
              if(quitKeyPressed)
              {
                System.exit(0);
              }
        
            }
            
            levelMusic.stop();
          gameover();
          credits();
          break;
        }
        case 2: 
        {
          System.out.println("NOT IMPLEMENTED YET");
          //break;
        }
        case 3: 
        {
          System.exit(0);
        }
    
      }
    }
    
    private void init()
    {
    gScr = bufferStrategy.getDrawGraphics();  
    }  
  
  private int gameMenu() 
  {

    boolean gameMenuRunning = true;
    while(gameMenuRunning)
    {
      
      
      gScr.drawImage(menu.image,0,0,null);
      bufferStrategy.show();
      if(clickedSingleArea)
      {
        gameMenuRunning = false;
        return 1;
      }
      if(clickedMultiArea)
      {
        gameMenuRunning = false;
        return 2;
      }
      if(clickedQuitArea)
      {
        gameMenuRunning = false;
        return 3;
      }
    
    }
    return 0;
  }
  
  private Hero heroChoiceMenu()
  {
    
    
    boolean heroMenuRunning = true;
    while(heroMenuRunning)
    {  
      gScr.drawImage(heroMenu.image,0,0,null);
      bufferStrategy.show();
      if(clickedheroArea)
      {
        hero = new Hero(Hero.HeroType.chex);
        hero.initActor("data\\characters\\warrior");
        hero.setBounds();
        return hero;
      }
      else if(clickedHeroTwoArea)
      {
        hero = new Hero(Hero.HeroType.doom);
        hero.initActor("data\\characters\\doom");
        hero.setBounds();
        return hero;
      }
    
    }
    return new Hero();
  }
  
  private void gameover()
  {
    boolean run = true;
    System.out.println("You have lost the match");
    
    while(run)
    {
      gScr.drawImage(gameOver.image, 0,0 ,null);
      bufferStrategy.show();
      if(quitKeyPressed)
      {
        System.exit(0);
      }
      if(creditsKeyPressed)
      {
        run = false;
      }
    }
    
  }
  
  private void credits()
  {
    GameImage one = new GameImage();
    GameImage two = new GameImage();
    GameImage three = new GameImage();
    
    one.loadImage("data\\credits\\credits1.JPG");
    two.loadImage("data\\credits\\credits2.JPG");
    three.loadImage("data\\credits\\credits3.JPG");
    
    gScr.drawImage(one.image,0,0,null);
    bufferStrategy.show();
    
    try 
        { Thread.sleep(2000);  }
        catch(InterruptedException ex){}
        
        gScr.drawImage(two.image,0,0,null);
        bufferStrategy.show();
    
    try 
        { Thread.sleep(2000);  }
        catch(InterruptedException ex){}
        
        gScr.drawImage(three.image,0,0,null);
        
        bufferStrategy.show();
    
    try 
        { Thread.sleep(2000);  }
        catch(InterruptedException ex){}
    
    System.exit(0);
  }
  
  public void loadGame(int lev) 
  {
    try { gameLoader.initGame(lev, gScr, bufferStrategy); }
    catch (Exception e) {  e.printStackTrace(); System.out.println("NOT LOADED"); } 
    enemies = gameLoader.enemies;
    items = gameLoader.items;
    background = gameLoader.background;
    levelMusic = gameLoader.music;
  }
  
  private void testPress(int x, int y)
  {
    clickedSingleArea = singlePlayerArea.contains(x,y) ? true : false;
    clickedMultiArea = multiPlayerArea.contains(x,y) ? true : false;
    clickedQuitArea = quitArea.contains(x,y) ? true : false;
    clickedheroArea = heroButtonArea.contains(x,y) ? true : false;
    clickedHeroTwoArea = heroTwoButtonArea.contains(x,y) ? true : false;
  }
  
  private void processKey(KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    
    if(keyCode == KeyEvent.VK_RIGHT)
    {
      rightKeyPressed = true;
    }
    
    if(keyCode == KeyEvent.VK_LEFT)
    {
      leftKeyPressed = true;
    }
    
    if(keyCode == KeyEvent.VK_DOWN)
    {
      downKeyPressed = true;
    }
    
    if(keyCode == KeyEvent.VK_UP)
    {
      upKeyPressed = true;
    }
    if(keyCode == KeyEvent.VK_SPACE)
    {
      punchKeyPressed = true;
    }
    if(keyCode == KeyEvent.VK_ESCAPE)
    {
      isPaused = isPaused ? false : true;
    }
    if(keyCode == KeyEvent.VK_C)
    {
      creditsKeyPressed = true;
    }
    if(keyCode == KeyEvent.VK_Q)
    {
      quitKeyPressed = true;
    }
  }
  
  private void processKeyRelease(KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    
    if(keyCode == KeyEvent.VK_RIGHT)
    {
      rightKeyPressed = false;
    }
    
    if(keyCode == KeyEvent.VK_LEFT)
    {
      leftKeyPressed = false;
    }
    
    if(keyCode == KeyEvent.VK_DOWN)
    {
      downKeyPressed = false;
    }
    
    if(keyCode == KeyEvent.VK_UP)
    {
      upKeyPressed = false;
    }
    if(keyCode == KeyEvent.VK_SPACE)
    {
      punchKeyPressed = false;
    }
    if(keyCode == KeyEvent.VK_C)
    {
      creditsKeyPressed = false;
    }
  }
  
  private void startThread() 
    { 
      if (animator == null || !running) 
      {
        animator = new Thread(this);
      animator.start();
      }
    }    
  
  private void initFullScreen()
   {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      gd = ge.getDefaultScreenDevice();
 
      setUndecorated(true);    
      setIgnoreRepaint(true);  
      setResizable(false);
    
      if (!gd.isFullScreenSupported()) {
        System.out.println("Full-screen exclusive mode not supported");
        System.exit(0);
      }
      gd.setFullScreenWindow(this); 

    
      gd.setDisplayMode(new DisplayMode(1024, 768, 32,60));
    setSize(1024,768);
      

      width = getBounds().width;
      height = getBounds().height;
      setBufferStrategy();
    }
    
  private void gameUpdate() 
    { 
      if (!isPaused)
      {
          hero = gameEngine.update(heroId,rightKeyPressed,leftKeyPressed,upKeyPressed,downKeyPressed,punchKeyPressed);
          gameEngine.updateEnemies(heroId);
          enemies = gameEngine.getEnemies();
          if(hero.isStanding)
          {
            goFrameCounter++;
          }
       }
        if(hero.reallyDead)
        {
          running = false;
        }
    }
      
    private void setBufferStrategy()
    { 
      try 
      {
          EventQueue.invokeAndWait( new Runnable() {
          public void run() 
          { createBufferStrategy(NUM_BUFFERS);  }
          });
      }
      catch (Exception e) 
      {  
          System.out.println("Error while creating buffer strategy");  
          System.exit(0);
      }

    try 
    {  
      Thread.sleep(500);  
    }
    catch(InterruptedException ex){}

    bufferStrategy = getBufferStrategy();  
  }
 
    private void screenUpdate()
  {
    //  if(gameOver)
      {
        try 
        {
            gScr = bufferStrategy.getDrawGraphics();
            gameRender(gScr);
            //gScr.dispose();
            if (!bufferStrategy.contentsLost())
              bufferStrategy.show();
            else
              System.out.println("Contents Lost");
       
            Toolkit.getDefaultToolkit().sync();
        }
        catch (Exception e) 
        { 
          e.printStackTrace();  
            running = false; 
        } 
      }
  } 
    
    private void gameRender(Graphics gScr)
    {
      if(upKeyPressed || downKeyPressed)
      {
        background.moveSize = hero.dMoveSpeed;
      }
      else
      {
        background.moveSize = hero.moveSpeed;
      }
      
    if(hero.X > 500 && hero.realX > 500 && hero.isMovingRight && hero.realX < background.width - 500)
      {
          
        background.moveLeft();
        hero.X -= background.moveSize;
        for(int i = 0; i < enemies.size(); i++)
        {
          Enemy tempEnemy = (Enemy) enemies.get(i);
          tempEnemy.X -= background.moveSize;
        }
        for(int i = 0; i < items.size(); i++)
        {
          HealthItem temp = (HealthItem) items.get(i);
          temp.X -= background.moveSize;
        }
        
      }
      else if(hero.X < 500  && hero.isMovingLeft && hero.realX > 500) 
      {  
        background.moveRight();
        hero.X += background.moveSize;
        
        for(int i = 0; i < enemies.size(); i++)
        {
          Enemy tempEnemy = (Enemy) enemies.get(i);
          
          tempEnemy.X+=background.moveSize;
        }
        for(int i = 0; i < items.size(); i++)
        {
          HealthItem temp = (HealthItem) items.get(i);
          temp.X += background.moveSize;
        }
      }
      
      boolean dontGo = false;
      
      if(hero.realX > background.width - 500)
      {
        background.stayStill();
        dontGo = true;
      }
      if(hero.realX < 450)
      {
        background.stayStill();
      }
      if(!(hero.isMovingRight || hero.isMovingLeft))
        background.stayStill();
      
      background.update();
      
      if ((background.xImHead > 0) && (background.xImHead < background.pWidth)) 
      {
        hero.X += background.xImHead;
        
        for(int i = 0; i < enemies.size(); i++)
        {
          Enemy tempEnemy = (Enemy) enemies.get(i);
          //tempEnemy.X+=background.moveSize;
          tempEnemy.X+= background.xImHead;
        }
        for(int i = 0; i < items.size(); i++)
        {
          HealthItem temp = (HealthItem) items.get(i);
          //temp.X += background.moveSize;
          temp.X+= background.xImHead;
        }
        
        background.xImHead = 0;
        background.stayStill();
      }
      else if (background.xImHead < background.pWidth-background.width)
      {
        hero.X -= background.moveSize;
        for(int i = 0; i < enemies.size(); i++)
        {
          Enemy tempEnemy = (Enemy) enemies.get(i);
          tempEnemy.X-=background.moveSize;
        }
        for(int i = 0; i < items.size(); i++)
        {
          HealthItem temp = (HealthItem) items.get(i);
          temp.X-=background.moveSize;
        }
        background.xImHead += background.moveSize;
        background.stayStill();
      }
      background.display(gScr);
      
      /*DEBUG
      gScr.setColor(Color.green);
      gScr.drawRect(hero.bounds.x, hero.bounds.y, hero.bounds.width, hero.bounds.height);
      gScr.drawRect(hero.punch.x, hero.punch.y, hero.punch.width, hero.punch.height);
      gScr.fillRect(hero.X, hero.Y, 10,10);
      gScr.setColor(Color.yellow);*/
      
      for(int i = 0; i < enemies.size(); i++)
      {
        Enemy tempEnemy = (Enemy) enemies.get(i);
        tempEnemy.draw(gScr);
        
        /*gScr.fillRect(tempEnemy.X, tempEnemy.Y, 10,10);
        gScr.drawRect(tempEnemy.bounds.x, tempEnemy.bounds.y, tempEnemy.bounds.width, tempEnemy.bounds.height);
        gScr.drawRect(tempEnemy.punch.x, tempEnemy.punch.y, tempEnemy.punch.width, tempEnemy.punch.height);*/
      }
      for(int i = 0; i < items.size(); i++)
      {
        HealthItem temp = (HealthItem) items.get(i);
        temp.draw(gScr);
      }
      hero.draw(gScr);
      /*gScr.setColor(Color.yellow);
      gScr.drawLine(0, background.topbounds, background.width, background.topbounds);
      gScr.setColor(Color.red);
      gScr.drawLine(0, background.bounds, background.width, background.bounds);*/
      gScr.drawImage(healthMenu.image, 10 ,10,null);
      gScr.setColor(Color.white);
      Integer healthTemp = new Integer(hero.health);
      gScr.drawString(healthTemp.toString(), 78, 34);
      
      if(hero.isMovingRight || hero.isPunching)
      {
        goFrameCounter = 0;
      }
      
      if(goFrameCounter >= GO_WAIT_FRAMES  && !dontGo)
      {
        gScr.drawImage(go.image, 600, 50, null);
      }
      
    }
    
    
  }
