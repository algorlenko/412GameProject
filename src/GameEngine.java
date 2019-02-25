
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
  public Unit myHero;
  Tile[][] myTiles;
  
  public int dungeonColumns;
  public int dungeonRows;
 
  
  public StatusScreen myStatus;
  
  public Image emptyImage;
 
  
  public GameEngine(GameScreen myScreen) throws IOException
  {
    dungeonColumns = 10;
    dungeonRows = 10;
    thisScreen = myScreen;
    myTiles = new Tile[dungeonColumns][dungeonRows];
    
    //emptyImage = generateImage
    for(int i = 0; i < dungeonColumns; i++)
    {
      for(int j = 0; j < dungeonRows; j++)
      {
        myTiles[i][j] = new Tile(i, j);
        //myTiles[i][j].syncTileWithScreen(); 
      }
      
    }
    myHero = new Unit(0, 0);
    Wall myWall = new Wall(2, 2);
    Wall myOtherWall = new Wall(3,3);
    myStatus = new StatusScreen();
    draw();
    //thisScreen.currentTiles[0][0].loadImage(myHero.unitImage, 1);
  }
  
  
  
  
  /*public class Tile
  {
    int x;
    int y;
    String[] imageName;
    MapObject[] myContents;
    
    public Tile(int myX, int myY)
    {
      imageName = new String[4];
      myContents = new MapObject[4];
      
      imageName[0] = "/dngn/floor/Crystal_floor0.png";
      imageName[1] = "/empty.png";
      x = myX;
      y = myY;
    }
 
    
    
    public void syncTileWithScreen()
    {
      for(int i = 0; i < 4; i++)
      {
        thisScreen.currentTiles[x][y].loadImage(imageName[i], i);
      }
      
      
    }
    
    
  } */
  
  
  public class MapObject
  {
        String unitImage;
    int x;
    int y;
    Image image;
   public void loadIntoTile(int myX, int myY)
    {
      
      myTiles[myX][myY].imageName[1] = unitImage;
      myTiles[myX][myY].myContents[1] = this;
      myTiles[myX][myY].image[1] = image;
  //    myTiles[myX][myY].syncTileWithScreen();
    }
    
    
  }
  
  
  
  public class Unit extends MapObject
  {

    public Unit(int myX, int myY) throws IOException
    {
      unitImage = "/elf_m.png";
      image = generateImage(unitImage);
      x = myX;
      y = myY;
      loadIntoTile(x, y);
    }
    
    
   /* public void loadIntoTile(int myX, int myY)
    {
      
      myTiles[myX][myY].imageName[1] = unitImage;
      myTiles[myX][myY].myContents[1] = this;
      myTiles[myX][myY].syncTileWithScreen();
    }
    */
    public void move(int dx, int dy)
    {   
      int futureX = x + dx;
      int futureY = y + dy;
      int pastX = x;
      int pastY = y;
      
      
      if(! (futureX < 0 || futureX > dungeonColumns - 1 || futureY < 0 || futureY > dungeonRows - 1) )
      {
        if(!(myTiles[futureX][futureY].myContents[1] instanceof Wall))
        {
          x = futureX;
          y = futureY;
          myTiles[pastX][pastY].myContents[1] = null;
          myTiles[pastX][pastY].imageName[1] = "/empty.png";
         // myTiles[pastX][pastY].syncTileWithScreen();
          loadIntoTile(x, y);
          myStatus.message = "you have moved into coordinate" + x + " " + y + "and your current error is: " + myTiles[5][5].myError; 
        }
      }
    }    
  }
  
  
  public class Wall extends MapObject
  {
    //int x;
    //int y;
    //String unitImage;
    public Wall(int myX, int myY) throws IOException
    {
      unitImage = "/dngn/wall/crystal_wall00.png"; 
      image = generateImage(unitImage);
      x = myX;
      y = myY;
      loadIntoTile(x, y);
    }
    
  /*  public void loadIntoTile(int myX, int myY)
    {
      
      myTiles[myX][myY].imageName[1] = unitImage;
      myTiles[myX][myY].myContents[1] = this;
      myTiles[myX][myY].syncTileWithScreen();
    }*/
    
    
    
  }
  
  
 @Override
  public void keyPressed(KeyEvent e) {   
    int key = e.getKeyCode();
    //  thisScreen.currentTiles[myHero.x][myHero.y].loadImage("src/resources/empty.png", 1);
    if (key == KeyEvent.VK_SPACE) {
      
    }   
    if (key == KeyEvent.VK_LEFT) {
      myHero.move(-1, 0);
    }    
    if (key == KeyEvent.VK_RIGHT) {
      myHero.move(1, 0);
    }   
    if (key == KeyEvent.VK_UP) {
      myHero.move(0, -1);
    }    
    if (key == KeyEvent.VK_DOWN) {
      myHero.move(0, 1);
    }   
    // thisScreen.currentTiles[myHero.x][myHero.y].loadImage(myHero.unitImage, 1);
    //thisScreen.currentTiles[myHero.x][myHero.y].hasChanged = true;
    draw();
  }
  
  
  public void mousePressed(MouseEvent e)
  {
      
      Point selectedTile = calculateTile(e.getX(), e.getY());
    if(selectedTile.x < dungeonColumns && selectedTile.x >= 0 && selectedTile.y < dungeonRows && selectedTile.y >= 0)
    {
    myHero.move(selectedTile.x - myHero.x, selectedTile.y - myHero.y);
    }
    draw();
  }
  
    public Point calculateTile(int x, int y)
  {
    Point myReturn = new Point();
    myReturn.x = x * dungeonColumns / thisScreen.getSize().width;
    myReturn.y = y * dungeonRows / ((thisScreen.getSize().height / 5) * 4);
    return myReturn;  
  }
  
  
  
  public static void keyReleased(KeyEvent e) {
    
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
      //hasChanged = true;
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
   //Graphics2D myGraphic = thisScreen.gbi;
 //   Dimension myBufferedDimension = g.getSize();
   int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
   int myWidth = thisScreen.myBufferedDimension.width;
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
          if(myTiles[i][j].imageName[layer] != "/empty.png")
        {
       // g2d.drawImage(currentTiles[i][j].image[layer],((this.getSize().width / dungeonColumns) * i), (this.getSize().height / dungeonRows) * j  ,this.getSize().width / dungeonColumns ,this.getSize().height / dungeonRows, null);
         myGraphic.drawImage(myTiles[i][j].image[layer],((myWidth / dungeonColumns) * i), (myHeight / dungeonRows) * j  ,myWidth / dungeonColumns , myHeight / dungeonRows, null);
    //    currentTiles[i][j].hasChanged = false;
        }
          
          
        }
       // }
      //}    
    }
  }
 // g2d.drawString(Integer.toString(myBufferedDimension.height),  myWidth/2, myHeight + (myBufferedDimension.height - myHeight) / 2 );
  }
  
 
 
    private void drawStatus(Graphics2D myGraphic)
  {   
 //  Dimension myBufferedDimension = g.getSize();
   int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
   int myWidth = thisScreen.myBufferedDimension.width;
 //  int myHeight = (340 / 5) * 4;
  // int myWidth = 640;
      
      
      
 myGraphic.drawString(myStatus.message,  0, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2 );
 //  g.drawString(myStatus.message,  0, myHeight + (340 - myHeight) / 2 );
  }
          
          
}