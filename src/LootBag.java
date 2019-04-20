
import java.io.IOException;
import java.util.ArrayList;

public class LootBag extends MapObject {

    ArrayList <InventoryItem> droppedItems;

    public LootBag(int myX, int myY, Tile myTiles[][], ArrayList <InventoryItem> myItems) throws IOException {
        myLayer = 2;
        droppedItems = myItems;
        unitImage = "/chest_silver.png";
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y, myTiles);

    }

    public void addToBag(ArrayList <InventoryItem> addedItems) //this is poorly written and should be done using lists
    {
        droppedItems.addAll(addedItems);
        /*
        for (int i = 1; i < droppedItems.length; i++) {
            for (int j = 0; j < droppedItems.length; j++) {
                if (droppedItems[i] == null) {
                    if (addedItems[j] == null) {
                        return;
                    }
                    droppedItems[i] = addedItems[j];
                    i++;
                }
            }
        }*/

    }

}
