
import java.io.IOException;

public class Wall extends MapObject {

    public Wall(int myX, int myY, Tile myTiles[][], String wallImageString) throws IOException {
        myLayer = 3;
        unitImage = wallImageString;
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y, myTiles);
    }

}
