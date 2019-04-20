
import java.io.IOException;

public class Hero extends Unit {

    final int SLOTS = 6;
    Equipment[] equippedItems;
    Inventory myInventory;
    int inventorySpace;
    int goldCoins;

    public Hero(int myX, int myY, Tile myTiles[][], String myImage, int myMaxHP) throws IOException {
        super(myX, myY, myTiles, myImage, myMaxHP);
        goldCoins = 0;
        attackPower = 20;
        inventorySpace = 36;
        myInventory = new Inventory(36);
        equippedItems = new Equipment[SLOTS];
        for(int i =0; i<SLOTS; i++)
        {
            equippedItems[i] = null;
            
        }
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

                //myStatus.message = "you have moved into coordinate" + x + " " + y + "and your current error is: ";

                if (myTiles[futureX][futureY].myContents[2] instanceof LootBag) // this entire if statement could be converted into a more comprehensive pickUpItem function
                {
                    if (pickUpItems((LootBag) myTiles[futureX][futureY].myContents[2])) {
                        myTiles[x][y].clearAtLayer(2);
                        myStatus.pushMessage("You have picked up an item");
                    }
                }

                return true;
            }
            myStatus.pushMessage("You cannnot move there");
            return false;
        }
        myStatus.pushMessage("You cannnot move there");
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

    public void equip(Equipment addedEquipment, int slot)
    {
                equippedItems[slot] = addedEquipment;
        if(addedEquipment != null)
        {
        attackPower += addedEquipment.powerLevel;
        }
    }
    
    public void unequip(Equipment removedEquip)
    {
        if(removedEquip != null)
        {
        attackPower -= removedEquip.powerLevel;
        }
    }
    
    public void calculateStats()
    {
        // this will be a function that will make strength autmoaticallt update HP and damage, and will make equipping Items apply their relevant stat boosts
    }
    
}
