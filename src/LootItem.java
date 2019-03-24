public class LootItem extends MapObject {

    public InventoryItem thisItem;
    
public LootItem(int myX, int myY, Tile myTiles[][], InventoryItem myItem)
{
      myLayer = 2;
      thisItem = myItem;
      unitImage = myItem.imageName;
      image = myItem.image;
      x = myX;
      y = myY;
      loadIntoTile(x, y, myTiles);
    
}

    
}
