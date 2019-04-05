
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.Math;
import javax.imageio.ImageIO;

public class InventoryState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Equipment myTestItem;
    public int DeleteThisTestVariable;

    
    public Image menuImage;
    public Inventory heroInventory;
    public int rows;
    public int columns;
    public int hoveredSlot;

    public InventoryState(GameScreen myScreen, GameStateManager passedGSM, Inventory herosInventory) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
        myTestItem = new Equipment("/item/amulet/celtic_red.png", 5, "This is an Amulet of Testing", "AmuletOfTesting");
        DeleteThisTestVariable = 0;

        heroInventory = herosInventory;
        rows = (int) Math.sqrt(heroInventory.storageSpace) + 1;
        columns = rows;
        hoveredSlot = -1;
        menuImage= generateImage("/InventorySheen.png");
        
    }

    public void draw() {
        //thisScreen.gbi.drawImage(myTestItem.image, 50, 50, null);
        //if (DeleteThisTestVariable == 1) {
        //   thisScreen.gbi.drawString(myTestItem.itemDescription, 50, 50);
        //  thisScreen.gbi.drawString(("This item is Level " + myTestItem.powerLevel), 50, 40);
        //}

        int myHeight = thisScreen.myBufferedDimension.height;
        int myWidth = thisScreen.myBufferedDimension.width / 2;
        thisScreen.gbi.drawImage(menuImage, 0, 0, myWidth * 2, myHeight, null);
        drawInventory(myWidth, myHeight);
        drawDescription(myWidth, myHeight);
    }

    public void drawInventory(int myWidth, int myHeight) {

        int itemNumber = 0;

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                itemNumber = (j * columns) + i;
                if (itemNumber < heroInventory.storageSpace) {
                    if (heroInventory.items[itemNumber] != null) {
                        thisScreen.gbi.drawImage(heroInventory.items[itemNumber].image, ((myWidth / columns) * i), (myHeight / rows) * j, myWidth / columns, myHeight / rows, null);
                    }

                }

            }
        }
    }

    public void drawDescription(int myWidth, int myHeight) {
        if (hoveredSlot != -1) {
            if (heroInventory.items[hoveredSlot] != null) {
                int y = hoveredSlot / rows;
                int x = hoveredSlot - y;
                thisScreen.gbi.drawString(heroInventory.items[hoveredSlot].itemDescription, ((myWidth / columns) * x), (myHeight / rows) * y + 50); //we need to replace this 50 with something non hardcoded ASAP
            }
        }

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_I) {
            myGSM.setState(0); // Back to adventure screen
        }
        if (key == KeyEvent.VK_ESCAPE) {
            myGSM.setState(2); // Goes to Main Menu

        }

    }

    public void keyReleased(KeyEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        hoveredSlot = calculateSlot(e.getX(), e.getY());
        if (hoveredSlot >= heroInventory.storageSpace) {
            hoveredSlot = -1;
        }
    }

    public int calculateSlot(int x, int y) {
        int myWidth = thisScreen.getSize().width / 2;
        int myHeight = thisScreen.getSize().height;
        if (x * columns / myWidth >= columns) {
            return -1;
        }
        if (y * rows / myHeight >= rows) {
            return -1;
        }
        return (x * columns / myWidth) + ((y * rows / myHeight) * columns);
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }
    
    
    
        public Image generateImage(String myImageName) throws IOException {
        Image myResult = null;

        try {
            myResult = ImageIO.read(getClass().getResourceAsStream(myImageName)); //this is the new way
        } catch (Exception e) {
            e.printStackTrace();
        }
        //hasChanged = true;
        return myResult;
    }
}
