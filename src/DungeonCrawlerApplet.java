/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.swing.JApplet;

/**
 *
 * @author Sasha
 */
public class DungeonCrawlerApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     * @throws java.io.IOException
     */
    public void init(){
        // TODO start asynchronous download of heavy resources
        try
        {
      GameScreen myScreen = new GameScreen();
            setSize(640, 340);
      //StatusScreen myStatus = new StatusScreen();
        add(myScreen);
        setVisible(true);
      }
		catch(Exception e) {
			e.printStackTrace();
		}

    }

    // TODO overwrite start(), stop() and destroy() methods
}
