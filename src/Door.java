
import java.awt.Image;
import java.io.IOException;

public class Door extends MapObject implements Useable {

    boolean isOpen;
    Image openImage;
    String openImageString;
    Image closedImage;
    String closedImageString;

    public Door(int myX, int myY, Tile myTiles[][]) throws IOException {
        isOpen = false;
        myLayer = 3;
        closedImageString = "/dngn/doors/runed_door.png";
        closedImage = generateImage(closedImageString);

        openImageString = "/dngn/doors/open_door.png";
        openImage = generateImage(openImageString);

        unitImage = closedImageString;
        image = closedImage;
        x = myX;
        y = myY;
        loadIntoTile(x, y, myTiles);
    }

    public int tryUse(GameEngine myEngine) {
        if (isOpen == false) {
            return tryOpen(myEngine.myHero, myEngine.myStatus, myEngine.myTiles);
        } else {
            return 2;
        }
    }

    public int tryOpen(Hero myHero, StatusScreen myStatus, Tile myTiles[][]) {
        if (isOpen == false) {
            if (myHero.myInventory.searchFor("L1Key") != -1) {
                isOpen = true;
                unitImage = openImageString;
                image = openImage;
                loadIntoTile(x, y, myTiles);
                int removedItem;
                removedItem = myHero.myInventory.searchFor("L1Key");
                myHero.myInventory.clearSlot(removedItem);
                myStatus.pushMessage("You have unlocked the door using your key.");
                return 1;
            }
            myStatus.pushMessage("You do not have the key.");
            return -1;
        }
        myStatus.pushMessage("The Door is already open.");
        return -1;
    }
}
