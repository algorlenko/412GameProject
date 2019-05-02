
import java.io.IOException;

public class Hero extends Unit {

    final int SLOTS = 6;
    Equipment[] equippedItems;
    Inventory myInventory;
    int inventorySpace;
    long goldCoins;
    int baseAttackPower;

    public Spell selectedSpell;
    int baseIntelligence; // added by Alex
    int intelligence;
    int maxArmor;
    int baseMaxHP; 
    int armor; //Hunter     

    public Hero(int myX, int myY, Tile myTiles[][], String myImage, int myMaxHP) throws IOException {
        super(myX, myY, myTiles, myImage, myMaxHP);
        baseMaxHP = myMaxHP;
        goldCoins = 0;
        baseAttackPower = 20;
        attackPower = baseAttackPower;
        baseIntelligence = 50; // added by Alex
        intelligence = baseIntelligence;
        inventorySpace = 36;
        myInventory = new Inventory(36);
        
        maxMana = 100; 
        baseMaxMana = maxMana; 
        mana = maxMana; 
        
        equippedItems = new Equipment[SLOTS];
        for (int i = 0; i < SLOTS; i++) {
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
            if (myTiles[futureX][futureY].myContents[myLayer] == null || myTiles[futureX][futureY].myContents[myLayer] instanceof FriendlyCreature) { // Gorlenko modified this
                x = futureX;
                y = futureY;
                if(myTiles[futureX][futureY].myContents[myLayer] instanceof FriendlyCreature)
                {
                    myTiles[futureX][futureY].myContents[myLayer].x = pastX;
                           myTiles[futureX][futureY].myContents[myLayer].y = pastY;
                           myTiles[futureX][futureY].myContents[myLayer].loadIntoTile(pastX, pastY, myTiles);
                    // If the hero moves into a friendly monster he needs to be able to swap with him
                }
                else
                {
                myTiles[pastX][pastY].myContents[myLayer] = null;
                myTiles[pastX][pastY].imageName[myLayer] = "/empty.png";
                }
                loadIntoTile(x, y, myTiles);

                //myStatus.message = "you have moved into coordinate" + x + " " + y + "and your current error is: ";
                if (myTiles[futureX][futureY].myContents[2] instanceof LootBag) // this entire if statement could be converted into a more comprehensive pickUpItem function
                {
                    long howMuchGold;
                    String tempMessage;
                    LootBag myGrabbedLoot;
                    myGrabbedLoot = ((LootBag) myTiles[futureX][futureY].myContents[2]);
                    howMuchGold = myGrabbedLoot.goldCoins;
                    if (myGrabbedLoot.droppedItems == null) {
                        tempMessage = "You have picked up " + howMuchGold + " gold coins.";
                    } else if (myGrabbedLoot.droppedItems.size() == 1) {
                        tempMessage = "You have picked up an item, and " + howMuchGold + " gold coins.";
                    } else {
                        tempMessage = "You have picked up some items, and " + howMuchGold + " gold coins.";
                    }
                    if (pickUpItems(myGrabbedLoot)) {
                        myTiles[x][y].clearAtLayer(2);
                    } else {
                        tempMessage = "Your Inventory is Full, but you grabbed the " + howMuchGold + " gold coins.";
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
    
    @Override
     public void takeDamage(int damageAmount, Tile myTiles[][]) throws IOException {
        
            hp -= damageAmount;
        
             
            
             
            
        
        
        if (hp <= 0) {
            deathFunction(myTiles);
        }
    }

    public void attack(Unit recipient, StatusScreen myStatus, Tile myTiles[][]) throws IOException {
        recipient.takeDamage(attackPower, myTiles);
    }

    public boolean pickUpItems(LootBag target) { // this is still kinda poorly written

        goldCoins += target.goldCoins;
        target.goldCoins = 0;
        if (target.droppedItems != null) {
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

    public void equip(Equipment addedEquipment, int slot) {
        equippedItems[slot] = addedEquipment;
        calculateStats();

    }

    public void unequip(Equipment removedEquip) {
        calculateStats();
    }

    public void calculateStats() {
        attackPower = baseAttackPower;
        intelligence = baseIntelligence;
        int oldMaxHP = maxHP; 
        maxHP = baseMaxHP;
        for (int i = 0; i < SLOTS; i++) {
            if (equippedItems[i] != null) {
                attackPower += equippedItems[i].powerLevel;
                intelligence += equippedItems[i].intelligenceLevel;
                maxHP += equippedItems[i].armourLevel;
                
            }
        }
       hp = ((hp * maxHP) / (oldMaxHP)) +1;
       if(hp > maxHP)
       {
           hp = maxHP;
       }
        // this will be a function that will make strength autmoaticallt update HP and damage, and will make equipping Items apply their relevant stat boosts
    }

    
    public void recover(){
        hp = maxHP; 
        mana = maxMana; 
    
    
    }
    
}
