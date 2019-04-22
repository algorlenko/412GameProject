import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameStateManager {
    public ArrayList<GameState> gameStates;
    public GameScreen myScreen;
    private int currentState;
    public Hero myHero;
    //public Design.Hero hero;
    public static final int MAINMENUSTATE = 2;
    public static final int ADVENTURESTATE = 0; // these numbers will be flipped later
    public static final int INVENTORYSTATE = 1;
    public static final int PAUSEMENUSTATE = 3;
    public static final int DEATHSTATE = 4; 
    public static final int SHOPSTATE = 5;
    //   public static final int SPELLBOOKTATE = 3;
    //   public static final int NEWCHARACTERSTATE = 4;
    public GameStateManager(GameScreen passedScreen) throws IOException {
        // hero = new Design.Hero("/player/base/demonspawn_black_m.png");
        gameStates = new ArrayList<GameState>();
        myScreen = passedScreen;
        // currentState = ADVENTURESTATE;
        //currentState = ADVENTURESTATE;
        gameStates.add(new GameEngine(myScreen, this));
        myHero = gameStates.get(0).myHero;
        gameStates.add(new InventoryState(myScreen, this, myHero));
        gameStates.add(new MainMenuState(myScreen, this));
        gameStates.add(new PauseMenuState(myScreen, this));
        gameStates.add(new DeathState(myScreen,this));
        gameStates.add(new ShopState(myScreen, this, myHero));
        setState(ADVENTURESTATE);
        //gameStates.add(new AdventureState(this));
        //gameStates.add(new InventoryState(this));
        //gameStates.add(new SpellBookState(this));
        //gameStates.add(new NewCharacterState(this));

    }


    
    public void resetGame()
    {
        try
        {
        gameStates.set(0, new GameEngine(myScreen, this));
        myHero = gameStates.get(0).myHero;
        gameStates.set(1, new InventoryState(myScreen, this, myHero));
        setState(0);
        }
        catch(Exception exc)
                {
                    
                }
    }

    public void setState(int state) {

        currentState = state;

        //       gameStates.get(currentState).draw(); //there is no reason this should not work

        //gameStates.get(currentState).init();

//        myScreen.repaint();

    }




    public void update() {

        //  gameStates.get(currentState).update();

    }




    public void draw() {

        try
        {
        gameStates.get(currentState).draw();
        }
        catch (Exception exc) {
                        exc.printStackTrace();

                    }

    }




    public void keyPressed(KeyEvent e) {

        gameStates.get(currentState).keyPressed(e);

//        gameStates.get(currentState).draw();

//          myScreen.repaint();

    }




    public void keyReleased(KeyEvent e) {

        gameStates.get(currentState).keyReleased(e);

        //       gameStates.get(currentState).draw();

        //         myScreen.repaint();

    }




    public void mousePressed(MouseEvent e) {

        gameStates.get(currentState).mousePressed(e);

        //       gameStates.get(currentState).draw();

        //       myScreen.repaint();

    }




    public void mouseReleased(MouseEvent e) {

        gameStates.get(currentState).mousePressed(e);

        //     gameStates.get(currentState).draw();

        //     myScreen.repaint();

    }




    public void mouseClicked(MouseEvent e) {

        gameStates.get(currentState).mouseClicked(e);

        //   gameStates.get(currentState).draw();

        //    myScreen.repaint();

    }




    public void mouseMoved(MouseEvent e) {

        gameStates.get(currentState).mouseMoved(e);

        //  gameStates.get(currentState).draw();

        //  myScreen.repaint();

    }




}