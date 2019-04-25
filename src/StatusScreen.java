
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

public class StatusScreen {

    String message1;
    String message2;
    String message3;

    public StatusScreen() {
        message1 = " ";
        message2 = " ";
        message3 = " ";
    }

    public void pushMessage(String pushedMessage) {
        message3 = message2;
        message2 = message1;
        message1 = pushedMessage;
    }

    public void drawStatus(Graphics2D myGraphic, Image statusImage, int myWidth, int myHeight, GameScreen thisScreen, Hero myHero) {
        //   myGraphic.setFont(StatusFont); this only needs to be done once
        // myGraphic.setPaint(new Color(255,255,255));
        myGraphic.drawImage(statusImage, 0, myHeight, myWidth, myHeight / 4, null);
        myGraphic.drawString(message3, 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        myGraphic.drawString(message2, 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        myGraphic.drawString(message1, 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) * 3 / 4);

        myGraphic.drawString("Your Current Gold is: " + myHero.goldCoins, 1000, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        /*if (myMonsters.size() != 0) {
            myGraphic.drawString("Your current Hp is: " + myHero.hp + "\nThe Monster's Hp is: " + myMonsters.get(0).hp, 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        } else {
            myGraphic.drawString("Your current Hp is: " + myHero.hp + "\nAll monsters on this floor are dead.", 50, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        }*/
        // myGraphic.drawString("the Current Frame is: " + heroFrame, 200, 50);

        if (myHero.hp <= (myHero.maxHP / 3) * 2 && myHero.hp > (100 / 3)) { // NO HARDCODED NUMBERS >:( I have dehardcoded some but not all of it
            myGraphic.setColor(Color.YELLOW);
        }
        if (myHero.hp < (myHero.maxHP / 3) && myHero.hp >= 0) {
            myGraphic.setColor(Color.RED);
        }
        if (myHero.hp > (myHero.maxHP / 3) * 2) {
            myGraphic.setColor(Color.GREEN);
            // myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3+110, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2 -20, myHero.hp, 20);
        }
        myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2 - 20, myHero.hp, 20);
        //myGraphic.drawString("HP: " + myHero.hp, (thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        myGraphic.drawString("HP: " + myHero.hp + " / " + myHero.maxHP, (thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);//for displaying hp 
        myGraphic.setColor(Color.WHITE);
    }

    public void print() {

    }

}
