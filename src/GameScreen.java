
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
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
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
  GameStateManager myGSM;
  
  
      // Image Stuff
    public BufferedImage buffImage;
    public Graphics2D gbi;


  
  
  public GameScreen() throws IOException
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
  
  private void initScreen() throws IOException
  { 
    myBufferedDimension = new Dimension(640, 320);
    myStatus = new StatusScreen();
    addKeyListener(new TAdapter());
    addMouseListener(new OtherAdapter());
    //engine = new GameEngine(this);
    //currentTiles = engine.myTiles;
    setBackground(Color.BLACK);
    this.setFocusable(true);
    
    buffImage = new BufferedImage(myBufferedDimension.width, myBufferedDimension.height, BufferedImage.TYPE_INT_RGB);
    gbi = buffImage.createGraphics();
    
    
    myGSM = new GameStateManager(this);
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


    
    //drawTiles(gbi);
    //drawStatus(gbi);
    g2d.drawImage(buffImage, 0, 0, this.getSize().width, this.getSize().height, null);
    //buffImg = new BufferedImage(myBufferedDimension.width, myBufferedDimension.height, BufferedImage.TYPE_INT_RGB);
    buffImage = new BufferedImage(myBufferedDimension.width, myBufferedDimension.height, BufferedImage.TYPE_INT_RGB);
    gbi = buffImage.createGraphics();
  } 
  
  @Override
  public void actionPerformed(ActionEvent e)
  { 
   // repaint();
  }
  
  
 
  
    
  /*public class Tile
  {
    String[] imageName;
    Image[] image;
    boolean hasChanged;
    public Tile()
    {
      imageName = new String[4];
      image = new Image[4];
      loadImage("/dngn/floor/Crystal_floor0.png", 0);
      loadImage("/empty.png", 1);

    }

     
    
    public void loadImage(String myImageName, int priority) {
      if(myImageName != null)
      {
      imageName[priority] = myImageName;
      //ImageIcon ii = new ImageIcon(myImageName);  //this is my old semi trusty way of reading images
      //image[priority] = ii.getImage();   //this is my old semi trusty way of reading images
      
      
      
      		try {
			image[priority] = ImageIO.read(
				getClass().getResourceAsStream(myImageName) //this is the new way
			);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
      
      
      
      
      hasChanged = true;
      }
    }  
  }
*/



   


  private class TAdapter extends KeyAdapter
  {
    
    @Override
    public void keyReleased(KeyEvent e) {
      engine.keyReleased(e);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
      myGSM.keyPressed(e);
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
     myGSM.mousePressed(e); 
    }
    
  }
  
  
}
