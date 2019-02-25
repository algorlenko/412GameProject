
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

    public abstract class GameState {

            protected GameStateManager gsm;

            //public abstract void init();
            //public abstract void update();
            //public abstract void draw(java.awt.Graphics2D g);
            public abstract void keyPressed(KeyEvent e);
            public abstract void mousePressed(MouseEvent e);
            //public abstract void keyReleased(int k);

    }
