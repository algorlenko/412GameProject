
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;

public class GameScreen extends JPanel implements ActionListener 
{
  
  Tile[][] currentTiles;
  int dungeonRows;
  int dungeonColumns;
  private Timer timer;
  private final int DELAY = 10;
  GameEngine engine;
 StatusScreen myStatus;
Dimension myBufferedDimension;
  
  
  public void changeSize(int columns, int rows)
  {
  dungeonColumns = columns;
   dungeonRows = rows;
  
       currentTiles = new Tile[dungeonColumns][dungeonRows];
    for(int i = 0; i < dungeonColumns; i++)
    {
      for(int j = 0; j < dungeonRows; j++)
      {
        currentTiles[i][j] = new Tile(); 
      }
    }
   
  }
  
  
  public GameScreen()
  {    
    //dungeonColumns = 4;
    //dungeonRows = 4;   
   /* currentTiles = new Tile[dungeonColumns][dungeonRows];
    for(int i = 0; i < dungeonColumns; i++)
    {
      for(int j = 0; j < dungeonRows; j++)
      {
        currentTiles[i][j] = new Tile(); 
      }
    }*/
    initScreen();
  }
  
  private void initScreen() 
  { 
    myBufferedDimension = new Dimension(640, 320);
    myStatus = new StatusScreen();
    addKeyListener(new TAdapter());
    addMouseListener(new OtherAdapter());
    engine = new GameEngine(this);
    setBackground(Color.BLACK);
    this.setFocusable(true);
    //this.setSize(1,1);
    //timer = new Timer(DELAY, this);
    //timer.start();
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g); 
    
    Graphics2D g2d = (Graphics2D) g;

        // Creates the buffered image.
        BufferedImage buffImg = new BufferedImage(myBufferedDimension.width, myBufferedDimension.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gbi = buffImg.createGraphics();

    
    drawTiles(gbi);
    drawStatus(gbi);
    g2d.drawImage(buffImg, 0, 0, this.getSize().width, this.getSize().height, null);
  } 
  
  @Override
  public void actionPerformed(ActionEvent e)
  { 
   // repaint();
  }
  
  private void drawTiles(Graphics2D g)
  {   
    
 //   Dimension myBufferedDimension = g.getSize();
   int myHeight = (myBufferedDimension.height / 5) * 4;
   int myWidth = myBufferedDimension.width;
    //int myHeight = (340 / 5) * 4;
    //int myWidth = 640;
    for(int i = 0; i < dungeonColumns; i++)
    {
      for(int j = 0; j < dungeonRows; j++)
      {
     //   if(currentTiles[i][j].hasChanged == true)
      //  {
        for (int layer = 0; layer < 4; layer++)
        {
          if(currentTiles[i][j].imageName[layer] != "Resources/empty.png")
        {
       // g2d.drawImage(currentTiles[i][j].image[layer],((this.getSize().width / dungeonColumns) * i), (this.getSize().height / dungeonRows) * j  ,this.getSize().width / dungeonColumns ,this.getSize().height / dungeonRows, null);
         g.drawImage(currentTiles[i][j].image[layer],((myWidth / dungeonColumns) * i), (myHeight / dungeonRows) * j  ,myWidth / dungeonColumns , myHeight / dungeonRows, null);
    //    currentTiles[i][j].hasChanged = false;
        }
          
          
        }
       // }
      //}    
    }
  }
 // g2d.drawString(Integer.toString(myBufferedDimension.height),  myWidth/2, myHeight + (myBufferedDimension.height - myHeight) / 2 );
  }
  
    private void drawStatus(Graphics2D g)
  {   
 //  Dimension myBufferedDimension = g.getSize();
   int myHeight = (myBufferedDimension.height / 5) * 4;
   int myWidth = myBufferedDimension.width;
 //  int myHeight = (340 / 5) * 4;
  // int myWidth = 640;
      
      
      
 g.drawString(myStatus.message,  0, myHeight + (myBufferedDimension.height - myHeight) / 2 );
 //  g.drawString(myStatus.message,  0, myHeight + (340 - myHeight) / 2 );
  }
  
    
  public class Tile
  {
    String[] imageName;
    Image[] image;
    boolean hasChanged;
    public Tile()
    {
      imageName = new String[4];
      image = new Image[4];
      loadImage("Resources/dngn/floor/Crystal_floor0.png", 0);
      loadImage("Resources/empty.png", 1);

    }

     
    
    public void loadImage(String myImageName, int priority) {
      if(myImageName != null)
      {
      imageName[priority] = myImageName;
      ImageIcon ii = new ImageIcon(myImageName);
      image[priority] = ii.getImage();
      hasChanged = true;
      }
    }  
  }




    
    public class StatusScreen
    {
      String message;
     public StatusScreen()
     {
       message = "default message";
       
       
     }
     public void print()
     {
       
       
     }
    }


  private class TAdapter extends KeyAdapter
  {
    
    @Override
    public void keyReleased(KeyEvent e) {
      engine.keyReleased(e);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
      engine.keyPressed(e);
    }
    
 //   @Override
 //   public void mousePressed(MouseEvent e)
   // {
    // engine.mousePressed(e); 
    //}
    
  }
  
  
  
  
  
    private class OtherAdapter extends MouseAdapter
  {
    
    
   @Override
    public void mousePressed(MouseEvent e)
    {
     engine.mousePressed(e, calculateTile(e.getX(), e.getY())); 
    }
    
  }
  
  
  
  
  
  
  
  public Point calculateTile(int x, int y)
  {
    Point myReturn = new Point();
    myReturn.x = x * dungeonColumns / this.getSize().width;
    myReturn.y = y * dungeonRows / ((this.getSize().height / 5) * 4);
    return myReturn;  
  }
  
  
}
