
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class DungeonCrawlerOther extends JFrame {

    public DungeonCrawlerOther() {       
        initUI();
    }
    
    private void initUI() {    
      GameScreen myScreen = new GameScreen();
      setSize(640, 340);
      //StatusScreen myStatus = new StatusScreen();
        add(myScreen);
        
      //  add(myStatus);
        //setSize(400, 300);
        
     

//RelativeLayout rl = new RelativeLayout(RelativeLayout.Y_AXIS);
//myStatus.setPreferredSize(new Dimension(25,25) );


// JPanel container = new JPanel(rl);




//myScreen.setPreferredSize(new Dimension(400, 400));

//myStatus.setPreferredSize(new Dimension(400, 400));

//container.add(myStatus, new Float(1));
//container.add(myScreen, new Float(5));


        
//add(container);
//          pack();

        
        setResizable(true);
        
      
        setTitle("Dungeon Crawler Other");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            DungeonCrawlerOther game = new DungeonCrawlerOther();
            game.setVisible(true);
        });
    }
}