
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameEngine{
  
  public GameScreen thisScreen;
  public Unit myHero;
  Tile[][] myTiles;
  
  public int dungeonColumns;
  public int dungeonRows;
  
  public GameEngine(GameScreen myScreen)
  {
    dungeonColumns = 10;
    dungeonRows = 10;
    thisScreen = myScreen;
    thisScreen.changeSize(dungeonColumns, dungeonRows);
    myTiles = new Tile[dungeonColumns][dungeonRows];
    thisScreen = myScreen;
    for(int i = 0; i < dungeonColumns; i++)
    {
      for(int j = 0; j < dungeonRows; j++)
      {
        myTiles[i][j] = new Tile(i, j); 
        myTiles[i][j].syncTileWithScreen(); 
      }
      
    }
    myHero = new Unit(0, 0);
    Wall myWall = new Wall(2, 2);
    Wall myOtherWall = new Wall(3,3);
    //thisScreen.currentTiles[0][0].loadImage(myHero.unitImage, 1);
  }
  
  
  
  
  public class Tile
  {
    int x;
    int y;
    String[] imageName;
    MapObject[] myContents;
    
    public Tile(int myX, int myY)
    {
      imageName = new String[4];
      myContents = new MapObject[4];
      
      imageName[0] = "Resources/dngn/floor/Crystal_floor0.png";
      imageName[1] = "Resources/empty.png";
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
    
    
  }
  
  
  public class MapObject
  {
        String unitImage;
    int x;
    int y;
    
   public void loadIntoTile(int myX, int myY)
    {
      
      myTiles[myX][myY].imageName[1] = unitImage;
      myTiles[myX][myY].myContents[1] = this;
      myTiles[myX][myY].syncTileWithScreen();
    }
    
    
  }
  
  
  
  public class Unit extends MapObject
  {

    public Unit(int myX, int myY)
    {
      unitImage = "Resources/player/base/elf_m.png"; 
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
          myTiles[pastX][pastY].imageName[1] = "Resources/empty.png";
          myTiles[pastX][pastY].syncTileWithScreen();
          loadIntoTile(x, y);
          thisScreen.myStatus.message = "you have moved into coordinate" + x + " " + y; 
        }
      }
    }    
  }
  
  
  public class Wall extends MapObject
  {
    //int x;
    //int y;
    //String unitImage;
    public Wall(int myX, int myY)
    {
      unitImage = "Resources/dngn/wall/crystal_wall00.png"; 
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
    thisScreen.repaint();
  }
  
  
  public void mousePressed(MouseEvent e, Point selectedTile)
  {
    if(selectedTile.x < dungeonColumns && selectedTile.x >= 0 && selectedTile.y < dungeonRows && selectedTile.y >= 0)
    {
    myHero.move(selectedTile.x - myHero.x, selectedTile.y - myHero.y);
    }
    thisScreen.repaint();
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
}