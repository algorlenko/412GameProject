
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Equipment extends InventoryItem {

    int powerLevel;
    int armourLevel; 
    int intelligenceLevel;
    String equipType;

    public Equipment(String myImageName, int myPowerLevel, int myArmourLevel, int myIntellegenceLevel, String myDescription, String myItemName, String myType) throws IOException {
        super(myImageName, myDescription, myItemName);
        powerLevel = myPowerLevel;
        armourLevel =myArmourLevel;
        intelligenceLevel = myIntellegenceLevel; 
        equipType = myType;
        isSellable = true;
    }

}