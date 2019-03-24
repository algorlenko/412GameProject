
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Equipment extends InventoryItem{


    int powerLevel;
    public Equipment(String myImageName, int myPowerLevel, String myDescription, String myItemName) throws IOException
            {
              super(myImageName, myDescription, myItemName);
              powerLevel = myPowerLevel;
            }
    
}
