
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sasha
 */
public class Tile {

    String myError;
    int x;
    int y;
    String[] imageName;
    Image[] image;
    MapObject[] myContents;
    boolean hasChanged;

    public Tile(int myX, int myY, String floorImageString) throws IOException {

        imageName = new String[4];
        image = new Image[4];
        myContents = new MapObject[4];

        imageName[0] = floorImageString;
        //imageName[0] = "/carpet_1.png"; for testing
        imageName[1] = "/empty.png";

        image[0] = generateImage(imageName[0]);
        image[1] = generateImage(imageName[0]);

        x = myX;
        y = myY;
    }

    public void clearAtLayer(int deletionLayer) {
        myContents[deletionLayer] = null;
        imageName[deletionLayer] = "/empty.png";
    }

    public Image generateImage(String myImageName) throws IOException {
        Image myResult = null;

        try {
            myResult = ImageIO.read(getClass().getResourceAsStream(myImageName)); //this is the new way
        } catch (Exception e) {
            e.printStackTrace();
            myError = e.getMessage();
        }
        //hasChanged = true;
        return myResult;
    }
}
