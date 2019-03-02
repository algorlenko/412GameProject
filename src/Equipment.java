
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Equipment extends InventoryItem{


    int PowerLevel;
    public Equipment(String myimageName, int myPowerLevel) throws IOException
            {
                imageName = myimageName;
                image = generateImage(imageName);
                PowerLevel = myPowerLevel;
            }
    
}
