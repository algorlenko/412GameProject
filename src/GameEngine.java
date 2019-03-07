
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameEngine extends GameState{
  
  public GameScreen thisScreen;
  public Hero myHero;
  Tile[][] myTiles;
  
  public int dungeonColumns;
  public int dungeonRows;
 
  public GameStateManager myGSM;
  
  
  public StatusScreen myStatus;
  
  public Image emptyImage;
 
  
  public GameEngine(GameScreen myScreen, GameStateManager passedGSM) throws IOException
  {
    dungeonColumns = 10;
    dungeonRows = 10;
    thisScreen = myScreen;
    myTiles = new Tile[dungeonColumns][dungeonRows];
    
    
   myGSM = passedGSM;
    //emptyImage = generateImage
    for(int i = 0; i < dungeonColumns; i++)
    {
      for(int j = 0; j < dungeonRows; j++)
      {
        myTiles[i][j] = new Tile(i, j);
        //myTiles[i][j].syncTileWithScreen(); 
      }
      
    }
    myHero = new Hero(0, 0, myTiles);
    Wall myWall = new Wall(2, 2, myTiles);
    Wall myOtherWall = new Wall(3,3, myTiles);
    myStatus = new StatusScreen();
  }
  
  
  
  

  
 
  
  
 
  


  
  
 @Override
  public void keyPressed(KeyEvent e) {   
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_I) {
      myGSM.setState(1); // Goes to Inventory Screen
    }   
    if (key == KeyEvent.VK_LEFT) {
      myHero.move(-1, 0, myTiles, dungeonColumns, dungeonRows, myStatus);
     
    }    
    if (key == KeyEvent.VK_RIGHT) {
      myHero.move(1, 0, myTiles, dungeonColumns, dungeonRows, myStatus);
    
    }   
    if (key == KeyEvent.VK_UP) {
      myHero.move(0, -1, myTiles, dungeonColumns, dungeonRows, myStatus);
    
    }    
    if (key == KeyEvent.VK_DOWN) {
      myHero.move(0, 1, myTiles, dungeonColumns, dungeonRows, myStatus);
    
    }   

       if (key == KeyEvent.VK_ESCAPE) {
      myGSM.setState(2); // Goes to Main Menu
    
    }  
    
    
  }
  
  
  public void mousePressed(MouseEvent e)
  {
      
      Point selectedTile = calculateTile(e.getX(), e.getY());
    if(selectedTile.x < dungeonColumns && selectedTile.x >= 0 && selectedTile.y < dungeonRows && selectedTile.y >= 0)
    {
    myHero.move(selectedTile.x - myHero.x, selectedTile.y - myHero.y, myTiles, dungeonColumns, dungeonRows, myStatus);
    }
  }
  
      public void mouseMoved(MouseEvent e)
    {
       
    }
  
      
            public void mouseClicked(MouseEvent e)
    {
       
    }
      
            public void mouseReleased(MouseEvent e)
    {
       
    }
  
    public Point calculateTile(int x, int y)
  {
    Point myReturn = new Point();
    myReturn.x = x * dungeonColumns / thisScreen.getSize().width;
    myReturn.y = y * dungeonRows / ((thisScreen.getSize().height / 5) * 4);
    return myReturn;  
  }
  
  
  
  public void keyReleased(KeyEvent e) {
    
    int key = e.getKeyCode();
    
    if (key == KeyEvent.VK_LEFT) {
      
    }
    
    if (key == KeyEvent.VK_RIGHT) {
      
    }
    
    if (key == KeyEvent.VK_UP) {
      
    }
    
    if (key == KeyEvent.VK_DOWN) {
      
    }   
  }
  
  
  
          public Image generateImage(String myImageName) throws IOException {
            Image myResult = null;
      try{
			myResult = ImageIO.read( getClass().getResourceAsStream(myImageName) ); //this is the new way
		}
		catch(Exception e) {
			e.printStackTrace();
		}

      return myResult;
        }
 
        public void draw()
        {
            Graphics2D myGraphic = thisScreen.gbi;
            drawTiles(myGraphic);
            drawStatus(myGraphic);
        }
          
          
 private void drawTiles(Graphics2D myGraphic)
  {   

   int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
   int myWidth = thisScreen.myBufferedDimension.width;

    for(int i = 0; i < dungeonColumns; i++)
    {
      for(int j = 0; j < dungeonRows; j++)
      {

        for (int layer = 0; layer < 4; layer++)
        {
          if(myTiles[i][j].imageName[layer] != "/empty.png")
        {

         myGraphic.drawImage(myTiles[i][j].image[layer],((myWidth / dungeonColumns) * i), (myHeight / dungeonRows) * j  ,myWidth / dungeonColumns , myHeight / dungeonRows, null);

        }
          
          
        }
  
    }
  }

  }
  
 
 
    private void drawStatus(Graphics2D myGraphic)
  {   

   int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
   int myWidth = thisScreen.myBufferedDimension.width;

      
      
      
 myGraphic.drawString(myStatus.message,  0, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2 );

  }
          
          
}