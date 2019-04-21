
import java.io.IOException;

public class Hero extends Unit {

    final int SLOTS = 6;
    Equipment[] equippedItems;
    Inventory myInventory;
    int inventorySpace;
    double goldCoins;

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
                    double howMuchGold;
                    String tempMessage;
                    LootBag myGrabbedLoot;
                    myGrabbedLoot = ((LootBag) myTiles[futureX][futureY].myContents[2]);
                    howMuchGold = myGrabbedLoot.goldCoins;
                    if(myGrabbedLoot.droppedItems == null)
                    {
                        tempMessage = "You have picked up " + (int) howMuchGold + " gold coins.";
                    }
                    else if (myGrabbedLoot.droppedItems.size() == 1)
                    {
                        tempMessage = "You have picked up an item, and " + (int) howMuchGold + " gold coins.";
                    }
                    else
                    {
                        tempMessage = "You have picked up some items, and " + (int) howMuchGold + " gold coins.";
                    }
                    if (pickUpItems(myGrabbedLoot)) {
                        myTiles[x][y].clearAtLayer(2);
                    }
                    else
                    {
                        tempMessage = "Your Inventory is Full, but you grabbed the " + (int) howMuchGold + " gold coins.";
                    }
                    myStatus.pushMessage(tempMessage);
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

    public boolean pickUpItems(LootBag target) { // this is still kinda poorly written

        goldCoins += target.goldCoins;
        target.goldCoins = 0;
        if(target.droppedItems != null)
        {
        for (int i = 0; i < target.droppedItems.size(); i++) {
            if (target.droppedItems.get(i) == null) {
                return true;
            }
            if (myInventory.hasSpace()) {
                myInventory.addItem(target.droppedItems.get(i));
                target.droppedItems.remove(i);
                i--;
            } else {
                return false;
            }
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
