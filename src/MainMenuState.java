
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainMenuState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Image menuImage;
    public Image selector;
    public int selectorX;
    public int selectorY;
    public int menuGap;
    public int selectedItem;
    public int itemCount;

    public MainMenuState(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
        menuImage = generateImage("/Main_Menu_Placeholder2.png");
        selector = generateImage("/selector.png");
        selectorX = 990;
        selectorY = 382;
        menuGap = thisScreen.myBufferedDimension.height / 8;
        selectedItem = 0;
        itemCount = 3;
    }

    public void draw() {

        // thisScreen.gbi.drawString(("Presss Enter To return to the Game. Press Z if you would like to exit the game."), 100, 100);
        thisScreen.gbi.drawImage(menuImage, 0, 0, thisScreen.myBufferedDimension.width, thisScreen.myBufferedDimension.height, null);
        drawArrow();
    }

    public void drawArrow() {
        // selector below
        thisScreen.gbi.drawImage(selector, selectorX, selectorY + (menuGap * selectedItem), thisScreen.myBufferedDimension.width / 12, thisScreen.myBufferedDimension.height / 12, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            if (selectedItem == 0) {
                myGSM.setState(0); // Go to adventure screen (New Game)
            }
            if (selectedItem == 1) {
                myGSM.setState(1); // Inventory (will be Load Game)
            }
            if (selectedItem == 2) {
                System.exit(0); // Exit
            }
            // myGSM.setState(0);
        }

        //Shortcuts Below
        if (key == KeyEvent.VK_X) {
            System.exit(0); // Exit
        }

        if (key == KeyEvent.VK_L) {
            myGSM.setState(1); // Inventory
        }

        if (key == KeyEvent.VK_N) {
            myGSM.setState(0); // Go to adventure screen (New Game)
        }

        if (key == KeyEvent.VK_DOWN) {
            selectedItem++;
            selectedItem %= itemCount;
// selectorY += thisScreen.myBufferedDimension.height / 8;
        }
        if (key == KeyEvent.VK_UP) {
            selectedItem = (selectedItem + itemCount - 1) % 3;

// selectorY -= thisScreen.myBufferedDimension.height / 8;
        }

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

    public void keyReleased(KeyEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        System.out.println(e.getPoint());
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }
}
