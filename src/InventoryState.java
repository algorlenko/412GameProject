import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class InventoryState extends GameState {
    
    public GameStateManager myGSM;
    public GameScreen thisScreen;
    
    public InventoryState(GameScreen myScreen, GameStateManager passedGSM)
    {
        thisScreen = myScreen;
        myGSM = passedGSM;
        
    }
    
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
      myGSM.setState(0); // Back to adventure screen
    }   
    }
    
    public void mousePressed(MouseEvent e)
    {
        
    }
            }

