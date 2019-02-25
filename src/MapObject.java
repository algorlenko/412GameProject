
import java.awt.Image;

  public class MapObject
  {
        String unitImage;
    int x;
    int y;
    Image image;
   public void loadIntoTile(int myX, int myY, Tile[][] myTiles)
    {
      
      myTiles[myX][myY].imageName[1] = unitImage;
    //  myTiles[myX][myY].myContents[1] = this;
      myTiles[myX][myY].image[1] = image;
  //    myTiles[myX][myY].syncTileWithScreen();
    }
    
    
  }
