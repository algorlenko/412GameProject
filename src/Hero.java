
import java.io.IOException;

public class Hero extends Unit {

    Equipment equippedItem;
    Inventory myInventory;
    int inventorySpace;


    public Hero(int myX, int myY, Tile myTiles[][], String myImage) throws IOException {
        super(myX, myY, myTiles, myImage);

        attackPower = 20;
        inventorySpace = 10;
        myInventory = new Inventory(10);
    }

    public boolean move(int dx, int dy, Tile myTiles[][], int dungeonColumns, int dungeonRows, StatusScreen myStatus) {
        int futureX = x + dx;
        int futureY = y + dy;
        int pastX = x;
        int pastY = y;

        if (!(futureX < 0 || futureX > dungeonColumns - 1 || futureY < 0 || futureY > dungeonRows - 1)) {
            //if(!(myTiles[futureX][futureY].myContents[1] instanceof Wall))
            if (myTiles[futureX][futureY].myContents[myLayer] == null) {
                x = futureX;
                y = futureY;
                myTiles[pastX][pastY].myContents[myLayer] = null;
                myTiles[pastX][pastY].imageName[myLayer] = "/empty.png";

                loadIntoTile(x, y, myTiles);

                myStatus.message = "you have moved into coordinate" + x + " " + y + "and your current error is: ";

                if (myTiles[futureX][futureY].myContents[2] instanceof LootBag) // this entire if statement could be converted into a more comprehensive pickUpItem function
                {
                    if (pickUpItems((LootBag) myTiles[futureX][futureY].myContents[2])) {
                        myTiles[x][y].clearAtLayer(2);
                        myStatus.message = "You have picked up an item";
                    }
                }

                return true;
            }
            myStatus.message = "You cannnot move there";
            return false;
        }
        myStatus.message = "You cannnot move there";
        return false;
    }

    public void attack(Unit recipient, StatusScreen myStatus, Tile myTiles[][]) throws IOException {
        recipient.takeDamage(attackPower, myTiles);
    }

    public boolean pickUpItems(LootBag target) { // this is poorly written and should be rewritten with arraylists

        for (int i = 0; i < 10; i++) {
            if (target.droppedItems[i] == null) {
                return true;
            }
            if (myInventory.hasSpace()) {
                myInventory.addItem(target.droppedItems[i]);
            } else {
                return false;
            }
        }
        return true;
    }

}
