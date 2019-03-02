
import java.io.IOException;

 public class Unit extends MapObject
  {

    public Unit(int myX, int myY, Tile myTiles[][]) throws IOException
    {
      unitImage = "/elf_m.png";
      image = generateImage(unitImage);
      x = myX;
      y = myY;
      loadIntoTile(x, y, myTiles);
    }
    
    
    public void move(int dx, int dy, Tile myTiles[][], int dungeonColumns, int dungeonRows)
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
        
          loadIntoTile(x, y, myTiles);
        }
      }
    }    
  }