
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Monster extends Unit {

    public ArrayList<InventoryItem> myDrops;

    double myBounty;
    
    public Monster(int myX, int myY, Tile myTiles[][], String myImage, InventoryItem myLoot, int myMaxHP) throws IOException { // this is the simpler constructor
        super(myX, myY, myTiles, myImage, myMaxHP);
        myBounty = 100;
        myDrops = new ArrayList <InventoryItem>();
        if(myLoot != null)
        {
        myDrops.add(myLoot);
        }
        else
        {
            myDrops = null;
        }
       // myDrops[0] = myLoot;
        attackPower = 2;

    }

        public Monster(int myX, int myY, Tile myTiles[][], String myImage, InventoryItem myLoot, int myMaxHP, double myBounty) throws IOException { // this will be the more in-depth constructor
        super(myX, myY, myTiles, myImage, myMaxHP);
        myBounty = 100;
        myDrops = new ArrayList <InventoryItem>();
        if(myLoot != null)
        {
        myDrops.add(myLoot);
        }
        else
        {
            myDrops = null;
        }
       // myDrops[0] = myLoot;
        attackPower = 2;

    }
    
    
    public void move(int dx, int dy, Tile myTiles[][], int dungeonColumns, int dungeonRows) {
        int futureX = x + dx;
        int futureY = y + dy;
        int pastX = x;
        int pastY = y;

        if (!(futureX < 0 || futureX > dungeonColumns - 1 || futureY < 0 || futureY > dungeonRows - 1)) {
            if (myTiles[futureX][futureY].myContents[myLayer] == null) {
                x = futureX;
                y = futureY;
                myTiles[pastX][pastY].myContents[myLayer] = null;
                myTiles[pastX][pastY].imageName[myLayer] = "/empty.png";

                loadIntoTile(x, y, myTiles);
            }
        }
    }

    public void aiAction(Tile myTiles[][], StatusScreen myStatus) throws IOException {
        MapObject target = null;
        target = scanInRadius(1, myTiles);
        Random rand = new Random();
        int xMove=0;
        int yMove=0;
        if (target != null) {
            Unit unitTarget = (Unit) target;
            attack(unitTarget, myTiles);
            myStatus.pushMessage("The Hero has been attacked!");
        } else {
            int n=rand.nextInt(4);
            switch (n){
                case 0:
                    xMove=-1;
                    yMove=0;
                    break;
                case 1:
                    xMove=0;
                    yMove=1;
                    break;
                case 2:
                    xMove=1;
                    yMove=0;
                    break;
                case 3:
                    xMove=0;
                    yMove=-1;
                    break;
                    
            }
            move(xMove, yMove, myTiles, myTiles.length, myTiles[0].length);
        }
    }

    public void attack(Unit recipient, Tile myTiles[][]) throws IOException {
        recipient.takeDamage(attackPower, myTiles);
    }

    public MapObject scanInRadius(int myRadius, Tile myTiles[][]) {

        for (int i = -myRadius; i <= myRadius; i++) {
            for (int j = -myRadius; j <= myRadius; j++) {
                if (!(x + i < 0 || x + i > myTiles.length - 1 || y + j < 0 || y + j > myTiles[0].length - 1 || (i == 0 && j == 0))) {
                    if (myTiles[x + i][y + j].myContents[myLayer] instanceof Hero) {
                        return myTiles[x + i][y + j].myContents[myLayer];
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void deathFunction(Tile myTiles[][]) throws IOException {
        super.deathFunction(myTiles);
       // if (myDrops != null) {
            if (myTiles[x][y].myContents[2] == null) {
                new LootBag(x, y, myTiles, myDrops, myBounty);
            } else if (myTiles[x][y].myContents[2] instanceof LootBag) {
                LootBag currentBag = (LootBag) myTiles[x][y].myContents[2];
                currentBag.addToBag(myDrops, myBounty);
            }

       // }
    }

}
