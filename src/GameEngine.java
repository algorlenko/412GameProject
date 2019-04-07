
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.ArrayList; // import the ArrayList class

public class GameEngine extends GameState {

    public GameScreen thisScreen;
    //public Hero myHero; now all the states have a hero, who will be the same every time. This is how I am currently tackling having a shared inventory.
    Tile[][] myTiles;

    ArrayList<Monster> myMonsters;

    ArrayList<Wall> myWalls;

    public Monster selectedMonster;
    public int monsterIndex;

    Unit turnHolder;

    public final int UNITLAYER = 3; //if you change this unitlayer, you need to also change it in the Unit class.

    public int dungeonColumns;
    public int dungeonRows;

    public GameStateManager myGSM;

    public StatusScreen myStatus;

    public Image emptyImage;

    public Image[] FPStest; //delete everything associated with this hardcoded garbage very soon.
    public int heroFrame; // delete this

    public GameEngine(GameScreen myScreen, GameStateManager passedGSM) throws IOException {
        dungeonColumns = 20;
        dungeonRows = 10;
        thisScreen = myScreen;
        myTiles = new Tile[dungeonColumns][dungeonRows];

        myGSM = passedGSM;
        //emptyImage = generateImage
        for (int i = 0; i < dungeonColumns; i++) {
            for (int j = 0; j < dungeonRows; j++) {
                myTiles[i][j] = new Tile(i, j, "/dngn/floor/crystal_floor0.png");
                //myTiles[i][j].syncTileWithScreen(); 
            }

        }

        FPStest = new Image[4];
        FPStest[0] = generateImage("dknight_1.png"); // delete all this
        FPStest[1] = generateImage("dknight_2.png");
        FPStest[2] = generateImage("dknight_3.png");
        FPStest[3] = generateImage("dknight_4.png");
        heroFrame = 0;

        myHero = new Hero(0, 0, myTiles, "dknight_1.png");
        turnHolder = myHero;
        myMonsters = new ArrayList<Monster>(); // Create an ArrayList object
        myMonsters.add(new Monster(4, 4, myTiles, "/Enemigos/beetle_fire_giant_1.png"));
        myMonsters.add(new Monster(4, 3, myTiles, "/cultist_3.png"));
        myWalls = new ArrayList<Wall>();
        myWalls.add(new Wall(2, 2, myTiles, "/dngn/wall/crystal_wall00.png"));
        myWalls.add(new Wall(3, 3, myTiles, "/dngn/wall/crystal_wall00.png"));
        myStatus = new StatusScreen();
        Door myDoor = new Door(5, 5, myTiles);
    }

    public void makeNewLevel() throws IOException {
        for (int i = 0; i < dungeonColumns; i++) {
            for (int j = 0; j < dungeonRows; j++) {
                myTiles[i][j] = new Tile(i, j, "/dngn/floor/green_bones12.png");
                //myTiles[i][j].syncTileWithScreen(); 
            }
        }
        myMonsters.clear();
        myMonsters.add(new Monster(4, 4, myTiles, "/elf_m.png"));
        myMonsters.add(new Monster(4, 3, myTiles, "/orange_demon.png"));
        myWalls.clear();
        myWalls.add(new Wall(10, 2, myTiles, "/dngn/wall/brick_brown0.png"));
        myWalls.add(new Wall(10, 3, myTiles, "/dngn/wall/brick_brown0.png"));
                        myTiles[myHero.x][myHero.y].myContents[3] = null;
                myTiles[myHero.x][myHero.y].imageName[3] = "/empty.png";
                myHero.x = 9;
                myHero.y = 9;
        myHero.loadIntoTile(9, 9, myTiles);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_I) {
            myGSM.setState(1); // Goes to Inventory Screen
        }

        if (key == KeyEvent.VK_ESCAPE) {
            myGSM.setState(2); // Goes to Main Menu

        }

        if (key == KeyEvent.VK_P) {
            myGSM.setState(3);
        }

        if (myHero.isAlive) {
            if (turnHolder == myHero) {
                if (key == KeyEvent.VK_LEFT) {
                    if (myHero.move(-1, 0, myTiles, dungeonColumns, dungeonRows, myStatus) == true) {
                        successfulTurn();
                    }

                }
                if (key == KeyEvent.VK_RIGHT) {
                    if (myHero.move(1, 0, myTiles, dungeonColumns, dungeonRows, myStatus) == true) {
                        successfulTurn();
                    }

                }
                if (key == KeyEvent.VK_UP) {
                    if (myHero.move(0, -1, myTiles, dungeonColumns, dungeonRows, myStatus) == true) {
                        successfulTurn();
                    }

                }
                if (key == KeyEvent.VK_DOWN) {
                    if (myHero.move(0, 1, myTiles, dungeonColumns, dungeonRows, myStatus) == true) {
                        successfulTurn();
                    }

                }

                if (turnHolder instanceof Monster) {
                    try // I fucking hate Java for this, I need to get rid of this garbage, but I can't currently, it is all because I need to generate a Treasure Chest Image in the Monster Class for the LootBag
                    {
                        if (turnHolder instanceof Monster) {
                            monsterTurn();
                        }
                    } catch (Exception exc) {
                        exc.printStackTrace();

                    }
                    turnHolder = myHero;
                }

            }
        }

    }

    public void mousePressed(MouseEvent e) {



    }

    public void attemptUsage(Point selectedTile) {
        if (myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER] instanceof Useable) {
            if (selectedTile.x <= myHero.x + 1 && selectedTile.x >= myHero.x - 1 && selectedTile.y <= myHero.y + 1 && selectedTile.y >= myHero.y - 1) {
                int useResult = ((Useable) myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER]).tryUse(myHero, myStatus, myTiles);
                if (useResult == 1) {
                    successfulTurn();
                    try {
                        monsterTurn();
                    } catch (Exception exc) {
                        exc.printStackTrace();

                    }

                } else if (useResult == 2) {
                    try {
                        makeNewLevel();
                    } catch (Exception exc) {
                        exc.printStackTrace();

                    }
                }
            } else {
                myStatus.message = "Your Target is out of Range.";
            }

        }
    }

    public void attemptAttack(Point selectedTile) {
        if (myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER] instanceof Monster) {
            if (selectedTile.x <= myHero.x + 1 && selectedTile.x >= myHero.x - 1 && selectedTile.y <= myHero.y + 1 && selectedTile.y >= myHero.y - 1) {
                try // I fucking hate Java for this, I need to get rid of this garbage, but I can't currently, it is all because I need to generate a Treasure Chest Image in the Monster Class for the LootBag
                {
                    myHero.attack((Unit) myTiles[selectedTile.x][selectedTile.y].myContents[UNITLAYER], myStatus, myTiles);
                    successfulTurn();
                    if (turnHolder instanceof Monster) {
                        monsterTurn();
                    }
                } catch (Exception exc) {
                    exc.printStackTrace();

                }
            } else {
                myStatus.message = "Your Target is out of Range.";
            }
        } else {
            myStatus.message = "You cannot Attack this area.";
        }

    }

    public void successfulTurn() {
        if (turnHolder == myHero && myMonsters.size() != 0) {
            turnHolder = myMonsters.get(0);
            return;
        }
        // if (turnHolder instanceof Monster) {
        //      if (monsterIndex == myMonsters.length - 1) {
        //         turnHolder = myHero;
        //      }
        //  }
    }

    public void monsterTurn() throws IOException {
        for (int i = 0; i < myMonsters.size(); i++) {

            if (myMonsters.get(i).isAlive == false) {
                myMonsters.remove(i);
                if (i != myMonsters.size()) {
                    i--;
                    continue;
                } else {
                    break;
                }

            }

            if (myMonsters.size() == 0) {
                break;
            }

            turnHolder = myMonsters.get(i);
            selectedMonster = (Monster) turnHolder; //this may need improvments
            selectedMonster.aiAction(myTiles, myStatus);

            if (myMonsters.get(i).isAlive == false) {
                myMonsters.remove(i);
                if (i != myMonsters.size()) {
                    i--;
                }
            }

        }
        turnHolder = myHero;
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        Point selectedTile = calculateTile(e.getX(), e.getY());
        if (selectedTile.x < dungeonColumns && selectedTile.x >= 0 && selectedTile.y < dungeonRows && selectedTile.y >= 0) {
            //myHero.move(selectedTile.x - myHero.x, selectedTile.y - myHero.y, myTiles, dungeonColumns, dungeonRows, myStatus);

            if (myHero.isAlive) {
                if (turnHolder == myHero) {
                    attemptAttack(selectedTile);
                    attemptUsage(selectedTile);
                }

            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public Point calculateTile(int x, int y) {
        Point myReturn = new Point();
        myReturn.x = x * dungeonColumns / thisScreen.getSize().width;
        myReturn.y = y * dungeonRows / ((thisScreen.getSize().height / 5) * 4);
        return myReturn;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

        }

        if (key == KeyEvent.VK_RIGHT) {

        }

        if (key == KeyEvent.VK_UP) {

        }

        if (key == KeyEvent.VK_DOWN) {

        }
    }

    public Image generateImage(String myImageName) throws IOException {
        Image myResult = null;
        try {
            myResult = ImageIO.read(getClass().getResourceAsStream(myImageName)); //this is the new way
        } catch (Exception e) {
            e.printStackTrace();
        }

        return myResult;
    }

    public void draw() {
        Graphics2D myGraphic = thisScreen.gbi;
        drawTiles(myGraphic);
        drawStatus(myGraphic);
    }

    private void drawTiles(Graphics2D myGraphic) {

        int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
        int myWidth = thisScreen.myBufferedDimension.width;

        for (int i = 0; i < dungeonColumns; i++) {
            for (int j = 0; j < dungeonRows; j++) {
                if (myTiles[i][j].myContents[UNITLAYER] == myHero) //delete this
                { // delete this
                    myTiles[i][j].image[UNITLAYER] = myHero.image;// delete this

                } //delete this
                for (int layer = 0; layer < 4; layer++) {
                    if (myTiles[i][j].imageName[layer] != "/empty.png") {
                        myGraphic.drawImage(myTiles[i][j].image[layer], ((myWidth / dungeonColumns) * i), (myHeight / dungeonRows) * j, myWidth / dungeonColumns, myHeight / dungeonRows, null);
                    }

                }

            }
        }
        heroFrame += 1; // delete this
        heroFrame %= 4;
        myHero.image = FPStest[heroFrame]; // delete this
    }

    private void drawStatus(Graphics2D myGraphic) {

        int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
        int myWidth = thisScreen.myBufferedDimension.width;

        myGraphic.drawString(myStatus.message, 0, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        if (myMonsters.size() != 0) {
            myGraphic.drawString("Your current Hp is: " + myHero.hp + "The Monster's Hp is: " + myMonsters.get(0).hp, 0, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        } else {
            myGraphic.drawString("Your current Hp is: " + myHero.hp + " All monsters on this floor are dead.", 0, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 4);
        }
        // myGraphic.drawString("the Current Frame is: " + heroFrame, 200, 50);

        if (myHero.hp <= (100 / 3) * 2 && myHero.hp > (100 / 3)) { // NO HARDCODED NUMBERS >:(
            myGraphic.setColor(Color.YELLOW);
            myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2, myHero.hp, 20);
            myGraphic.drawString("HP is : " + myHero.hp, (thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);//for displaying hp 
        }
        if (myHero.hp < (100 / 3) && myHero.hp >= 0) {
            myGraphic.setColor(Color.RED);
            myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2, myHero.hp, 20);
            myGraphic.drawString(" HP is : " + myHero.hp, (thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        }
        if (myHero.hp > (100 / 3) * 2) {
            myGraphic.setColor(Color.GREEN);
            myGraphic.fillRect((thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2, myHero.hp, 20);
            myGraphic.drawString(" HP is : " + myHero.hp, (thisScreen.myBufferedDimension.width / 4) * 3, myHeight + (thisScreen.myBufferedDimension.height - myHeight) / 2);
        }

    }

}
