
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public abstract class GameState {

    protected GameStateManager gsm;

    //public abstract void init();
    //public abstract void update();
    public Hero myHero;

    public abstract void draw() throws FontFormatException, IOException;

    public abstract void keyPressed(KeyEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void keyReleased(KeyEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseClicked(MouseEvent e);

}
