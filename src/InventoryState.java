import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class InventoryState extends GameState {
    
    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Equipment myTestItem;
    
    public InventoryState(GameScreen myScreen, GameStateManager passedGSM) throws IOException
    {
        thisScreen = myScreen;
        myGSM = passedGSM;
        myTestItem = new Equipment("/item/amulet/celtic_red.png", 5);
    }
    
    public void draw()
    {
        thisScreen.gbi.drawImage(myTestItem.image, 50, 50, null);
    }
    
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
            if (key == KeyEvent.VK_I) {
      myGSM.setState(0); // Back to adventure screen
    }
                   if (key == KeyEvent.VK_ESCAPE) {
      myGSM.setState(2); // Goes to Main Menu
    
    }  
    
            
    }
    
    public void mousePressed(MouseEvent e)
    {
       
    }
            }

