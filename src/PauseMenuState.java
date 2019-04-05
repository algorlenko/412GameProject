
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PauseMenuState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Image menuImage;
    public Image selector;

    public PauseMenuState(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
        menuImage = generateImage("/Pause_Menu_II.png");
    }

    public void draw() {

        // thisScreen.gbi.drawString(("Presss Enter To return to the Game. Press Z if you would like to exit the game."), 100, 100);
        thisScreen.gbi.drawImage(menuImage, 0, 0, thisScreen.myBufferedDimension.width, thisScreen.myBufferedDimension.height, null);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_R || key == KeyEvent.VK_P) {
            myGSM.setState(0); // Go to adventure screen
        }
        if (key == KeyEvent.VK_S) {
            // Spellbook
        }
        if (key == KeyEvent.VK_I) {
            myGSM.setState(1); // Inventory
        }
        if (key == KeyEvent.VK_X) { // lololol it spells out six
            System.exit(0); // Exit
        }
        if (key == KeyEvent.VK_O) {
            myGSM.setState(2); // PLACE. HOLDER. OPTIONS
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

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }
}
