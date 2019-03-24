import java.io.IOException;

  public class Monster extends Unit 
  {
      
      public InventoryItem[] myDrops;
      
      
      public Monster(int myX, int myY, Tile myTiles[][], String myImage) throws IOException
      {
      super(myX, myY, myTiles, myImage);
      myDrops = new InventoryItem[10];
      myDrops[0] = new InventoryItem("/key_gold.png", "The key to the next level.", "L1Key");
      attackPower = 2;
      for(int i =1; i < 10; i++)
      {
          myDrops[i] = null;
      }
      
      }
      
      
          public void move(int dx, int dy, Tile myTiles[][], int dungeonColumns, int dungeonRows)
    {   
      int futureX = x + dx;
      int futureY = y + dy;
      int pastX = x;
      int pastY = y;
      
      
      if(! (futureX < 0 || futureX > dungeonColumns - 1 || futureY < 0 || futureY > dungeonRows - 1) )
      {
        if(myTiles[futureX][futureY].myContents[myLayer] == null)
        {
          x = futureX;
          y = futureY;
          myTiles[pastX][pastY].myContents[myLayer] = null;
          myTiles[pastX][pastY].imageName[myLayer] = "/empty.png";
        
          loadIntoTile(x, y, myTiles);
        }
      }
    } 
     
          public void aiAction(Tile myTiles[][], StatusScreen myStatus) throws IOException
          {
              MapObject target = null;
              target = scanInRadius(1, myTiles);
              
              if(target != null)
              {
                  Unit unitTarget = (Unit) target;
                  attack(unitTarget, myTiles);
                  myStatus.message = "The Hero has been attacked!";
              }
              else
              {
                  move(0, 1, myTiles, myTiles.length, myTiles[0].length);
              }
          }
          
          
          public void attack(Unit recipient, Tile myTiles[][]) throws IOException
          {
              recipient.takeDamage(attackPower, myTiles);
          }
          
          
        public MapObject scanInRadius(int myRadius, Tile myTiles[][])
        {
            
        for(int i = -myRadius; i <= myRadius; i++)
              {
                for(int j = -myRadius; j <= myRadius; j++)
                {
                   if(! (x + i < 0 || x + i > myTiles.length - 1 || y + j < 0 || y + j > myTiles[0].length - 1 || (i == 0 && j == 0) ) )
                   {
                       if(myTiles[x + i][y + j].myContents[myLayer] instanceof Hero)
                       {
                           return myTiles[x + i][y + j].myContents[myLayer];
                       }
                   }
                }
              }
            return null;
        }
        
        @Override
        public void deathFunction(Tile myTiles[][]) throws IOException
        {
            super.deathFunction(myTiles);
            if(myDrops != null)
            {
                if(myTiles[x][y].myContents[2] == null)
                {
                new LootBag(x, y, myTiles, myDrops);
                }
                else if (myTiles[x][y].myContents[2] instanceof LootBag)
                {
                    LootBag currentBag = (LootBag) myTiles[x][y].myContents[2];
                    currentBag.addToBag(myDrops);
                }
                
            }
        }
        
  }
 