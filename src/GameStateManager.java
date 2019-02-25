import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class GameStateManager {

    private ArrayList<GameState> gameStates;
    public GameScreen myScreen;
    private int currentState;
    //public Design.Hero hero;
    public static final int MENUSTATE = 1;
    public static final int ADVENTURESTATE = 0; // these numbers will be flipped later
 //   public static final int INVENTORYSTATE = 2;
 //   public static final int SPELLBOOKTATE = 3;
 //   public static final int NEWCHARACTERSTATE = 4;

    public GameStateManager(GameScreen passedScreen) throws IOException {

       // hero = new Design.Hero("/player/base/demonspawn_black_m.png");
        gameStates = new ArrayList<GameState>();
        myScreen = passedScreen;
        currentState = ADVENTURESTATE;
        //currentState = ADVENTURESTATE;
        gameStates.add(new GameEngine(myScreen));
        setState(ADVENTURESTATE);
        //gameStates.add(new AdventureState(this));
        //gameStates.add(new InventoryState(this));
        //gameStates.add(new SpellBookState(this));
        //gameStates.add(new NewCharacterState(this));

    }

    public void setState(int state) {
        currentState = state;
        //gameStates.get(currentState).init();
        myScreen.repaint();
    }

    
    public void update() {
      //  gameStates.get(currentState).update();
    }

    public void draw(Graphics2D g) {
      //  gameStates.get(currentState).draw(g);
    }

    public void keyPressed(KeyEvent e) {
        gameStates.get(currentState).keyPressed(e);
          myScreen.repaint();
    }

    public void keyReleased(int k) {
       // gameStates.get(currentState).keyReleased(k);
    }

    public void mousePressed(MouseEvent e){
        gameStates.get(currentState).mousePressed(e);
        myScreen.repaint();
    }
    
}