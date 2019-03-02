
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class InventoryItem {

    String imageName;
    Image image;
    String itemName; 
    public Image generateImage(String myImageName) throws IOException {
            Image myResult = null;
      
            try
            {
			myResult = ImageIO.read( getClass().getResourceAsStream(myImageName) ); //this is the new way
            }
		catch(Exception e) {
			e.printStackTrace();
                       // myError = e.getMessage(); We may need this if the I/O goes wrong
		}
      //hasChanged = true;
      return myResult;
        }
    
    
}
