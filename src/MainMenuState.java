
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainMenuState extends GameState {
    
 public GameStateManager myGSM;
 public GameScreen thisScreen;
    
 
 public MainMenuState(GameScreen myScreen, GameStateManager passedGSM) throws IOException
    {
        thisScreen = myScreen;
        myGSM = passedGSM;
    }
 
 public void draw()
    {
        thisScreen.gbi.drawString(("Presss Enter To return to the Game. Press Z if you would like to exit the game."), 100, 100);
    }
    
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER) {
      myGSM.setState(0); // Go to adventure screen
    }
        if (key == KeyEvent.VK_Z) {
      System.exit(0); // Go to adventure screen
    }    
            
    }
    
    public void mousePressed(MouseEvent e)
    {
       
    }
 
}
