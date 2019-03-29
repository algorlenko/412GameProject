
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
public class FunctionLibrary {

    public Image generateImage(String myImageName) throws IOException {
        Image myResult;

        myResult = ImageIO.read(getClass().getResourceAsStream(myImageName)); //this is the new way

        //hasChanged = true;
        return myResult;
    }
}
