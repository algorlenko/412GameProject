
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapObject {

    String unitImage;
    int x;
    int y;
    int myLayer;
    Image image;

    public void loadIntoTile(int myX, int myY, Tile[][] myTiles) {

        myTiles[myX][myY].imageName[myLayer] = unitImage;
        myTiles[myX][myY].myContents[myLayer] = this;
        myTiles[myX][myY].image[myLayer] = image;
        //    myTiles[myX][myY].syncTileWithScreen();
    }

    public Image generateImage(String myImageName) throws IOException {
        Image myResult = null;

        try {
            myResult = ImageIO.read(getClass().getResourceAsStream(myImageName)); //this is the new way
        } catch (Exception e) {
            e.printStackTrace();
            // myError = e.getMessage(); We may need this if the I/O goes wrong
        }
        //hasChanged = true;
        return myResult;
    }

}
