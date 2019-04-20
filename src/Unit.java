
import java.io.IOException;

public class Unit extends MapObject {

    int maxHP;
    int hp;
    boolean isAlive;
    int attackPower;

    public Unit(int myX, int myY, Tile myTiles[][], String myImage, int myMaxHP) throws IOException {
        myLayer = 3; //if you change this unitLayer you need to change it in the GameEngine/AdventureState class as well.
        unitImage = myImage;
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y, myTiles);
        maxHP = myMaxHP;
        hp = maxHP;
        isAlive = true;
    }

    public void takeDamage(int damageAmount, Tile myTiles[][]) throws IOException {
        hp -= damageAmount;
        if (hp <= 0) {
            deathFunction(myTiles);
        }
    }

    public void deathFunction(Tile myTiles[][]) throws IOException {
        myTiles[x][y].myContents[myLayer] = null;
        myTiles[x][y].imageName[myLayer] = "/empty.png";
        isAlive = false;
    }

    public void move(int dx, int dy, Tile myTiles[][], int dungeonColumns, int dungeonRows) {
        int futureX = x + dx;
        int futureY = y + dy;
        int pastX = x;
        int pastY = y;

        if (!(futureX < 0 || futureX > dungeonColumns - 1 || futureY < 0 || futureY > dungeonRows - 1)) {
            if (!(myTiles[futureX][futureY].myContents[myLayer] instanceof Wall)) {
                x = futureX;
                y = futureY;
                myTiles[pastX][pastY].myContents[myLayer] = null;
                myTiles[pastX][pastY].imageName[myLayer] = "/empty.png";

                loadIntoTile(x, y, myTiles);
            }
        }
    }
}
