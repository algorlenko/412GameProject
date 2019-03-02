
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

myGSM.draw();
    
    //drawTiles(gbi);
    //drawStatus(gbi);
    g2d.drawImage(buffImage, 0, 0, this.getSize().width, this.getSize().height, null);
    //buffImg = new BufferedImage(myBufferedDimension.width, myBufferedDimension.height, BufferedImage.TYPE_INT_RGB);
    
    gbi.clearRect(0, 0, myBufferedDimension.width, myBufferedDimension.height);
   // gbi = buffImage.createGraphics();
  } 
  
  @Override
  public void actionPerformed(ActionEvent e)
  { 
   // repaint();
  }
  
  
 
  
 

   


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
