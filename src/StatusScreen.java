
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author papa
 */
public class StatusScreen {

    String message;

    public StatusScreen() {
        message = "This is the Default Message";

    }

    public void drawStatus(Graphics2D myGraphic, Image statusImage, Font StatusFont, int myWidth, int myHeight, GameScreen thisScreen, Hero myHero)
    {
         myGraphic.setFont(StatusFont);
       // myGraphic.setPaint(new Color(255,255,255));
        myGraphic.drawImage(statusImage, 0, myHeight, myWidth, myHeight/4, null);
        myGraphic.drawString(message, 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        /*if (myMonsters.size() != 0) {
            myGraphic.drawString("Your current Hp is: " + myHero.hp + "\nThe Monster's Hp is: " + myMonsters.get(0).hp, 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        } else {
            myGraphic.drawString("Your current Hp is: " + myHero.hp + "\nAll monsters on this floor are dead.", 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        }*/
        // myGraphic.drawString("the Current Frame is: " + heroFrame, 200, 50);

        if (myHero.hp <= (100 / 3) * 2 && myHero.hp > (100 / 3)) { // NO HARDCODED NUMBERS >:(
            myGraphic.setColor(Color.YELLOW);
            myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3+110, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2-20, myHero.hp, 20);
            myGraphic.drawString("HP: " + myHero.hp, (thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);//for displaying hp 
        }
        if (myHero.hp < (100 / 3) && myHero.hp >= 0) {
            myGraphic.setColor(Color.RED);
            myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3+110, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2-20, myHero.hp, 20);
            myGraphic.drawString("HP: " + myHero.hp, (thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        }
        if (myHero.hp > (100 / 3) * 2) {
            myGraphic.setColor(Color.GREEN);
            myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3+110, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2 -20, myHero.hp, 20);
            myGraphic.drawString("HP: " + myHero.hp, (thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        }
myGraphic.setColor(Color.WHITE);
    }
    
    public void print() {

    }

}
