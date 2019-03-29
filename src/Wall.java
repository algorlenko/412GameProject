
import java.io.IOException;

public class Wall extends MapObject {

    public Wall(int myX, int myY, Tile myTiles[][]) throws IOException {
        myLayer = 3;
        unitImage = "/dngn/wall/crystal_wall00.png";
        image = generateImage(unitImage);
        x = myX;
        y = myY;
        loadIntoTile(x, y, myTiles);
    }

}
