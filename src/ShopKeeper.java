
import java.awt.Image;
import java.io.IOException;

public class ShopKeeper extends MapObject implements Useable {

//    boolean isOpen;
GameEngine myEngine;

    public ShopKeeper(int myX, int myY, GameEngine passedEngine) throws IOException {
     //   isOpen = false;
        myLayer = 3;
myEngine = passedEngine;

        unitImage = "/Enemigos/merchant_a_ShopKeeper.png";
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y, myEngine.myTiles);
    }

    public int tryUse(GameEngine myEngine) {
return 3;
    }



}
