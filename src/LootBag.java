
import java.io.IOException;

public class LootBag extends MapObject {

    InventoryItem droppedItems[];

    public LootBag(int myX, int myY, Tile myTiles[][], InventoryItem[] myItems) throws IOException {
        myLayer = 2;
        droppedItems = myItems;
        unitImage = "/chest_silver.png";
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y, myTiles);

    }

    public void addToBag(InventoryItem[] addedItems) //this is poorly written and should be done using lists
    {
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
        }

    }

}
