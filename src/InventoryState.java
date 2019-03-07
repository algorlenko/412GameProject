
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class InventoryState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Equipment myTestItem;
    public int DeleteThisTestVariable;

    public InventoryState(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
        myTestItem = new Equipment("/item/amulet/celtic_red.png", 5, "This is an Amulet of Testing");
        DeleteThisTestVariable = 0;
    }

    public void draw() {
        thisScreen.gbi.drawImage(myTestItem.image, 50, 50, null);
        if (DeleteThisTestVariable == 1) {
            thisScreen.gbi.drawString(myTestItem.itemDescription, 50, 50);
            thisScreen.gbi.drawString( ("This item is Level " + myTestItem.PowerLevel), 50, 40);
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
        if (e.getX() < 75 && e.getX() > 50 && e.getY() < 75 && e.getY() > 50) {
            DeleteThisTestVariable = 1;
        } else {
            DeleteThisTestVariable = 0;
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }
}
