
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FriendlyCreature extends Unit {

    public GameEngine myEngine;

    public FriendlyCreature(int myX, int myY, String imageName, int myMaxHP, int myBaseAttackPower, GameEngine publicEngine) throws IOException {

        super(myX, myY, publicEngine.myTiles, imageName, myMaxHP);

        // myDrops[0] = myLoot;
        attackPower = myBaseAttackPower;
        myEngine = publicEngine;

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
        target = scanInRadius(1, myTiles); // first we try to scan for enemy monsters to attack
        // Random rand = new Random();

        if (target != null) {
            Unit unitTarget = (Unit) target;
            attack(unitTarget, myTiles);
            myStatus.pushMessage("Your Ally has attacked the Enemy for " + attackPower + " damage.");
            return;
        } else {
            target = scanInRadius(4, myTiles); // scan for enemy monsters to move closer to.
            if (target != null) {
followTarget(target);
            }
            else // if the ally can't find any monsters to engange he will follow the hero.
            {
                target = myEngine.myHero;
                followTarget(target);
            }
        }
        // move(xMove, yMove, myTiles, myTiles.length, myTiles[0].length);

    }

    public void followTarget(MapObject target) // I could potentially add in diagonal movement and just generally more intelligent calculation
    {
                int xMove = 0;
        int yMove = 0;
        xMove = target.x - x;
yMove = target.y - y; 
if(Math.abs(xMove) > Math.abs(yMove))
{
    xMove = (int) Math.signum(xMove);;
    yMove = 0;
}
else
{
    yMove = (int) Math.signum(yMove);
    xMove = 0;
}
move(xMove, yMove, myEngine.myTiles, myEngine.dungeonColumns, myEngine.dungeonRows);
    }
    
    
    
    
    public void attack(Unit recipient, Tile myTiles[][]) throws IOException {
        recipient.takeDamage(attackPower, myTiles);
    }

    public MapObject scanInRadius(int myRadius, Tile myTiles[][]) { // this scans for a monster within the designated radius

        for (int i = -myRadius; i <= myRadius; i++) {
            for (int j = -myRadius; j <= myRadius; j++) {
                if (!(x + i < 0 || x + i > myTiles.length - 1 || y + j < 0 || y + j > myTiles[0].length - 1 || (i == 0 && j == 0))) {
                    if (myTiles[x + i][y + j].myContents[myLayer] instanceof Monster) {
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

        // }
    }

}
