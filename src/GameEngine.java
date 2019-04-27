
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.util.ArrayList; // import the ArrayList class

public class GameEngine extends GameState {

    public GameScreen thisScreen;

    public int currentFloor;
    //public Hero myHero; now all the states have a hero, who will be the same every time. This is how I am currently tackling having a shared inventory.
    Tile[][] myTiles;
    public Image statusImage;
    ArrayList<Monster> myMonsters;

ArrayList<FriendlyCreature> friendlyCreatures; // Gorlenko Added this 
 public FriendlyCreature selectedAlly;
    
    
    
    public Point hoveredTile;

    ArrayList<Wall> myWalls;

    public Monster selectedMonster;
    public int monsterIndex;

    Unit turnHolder;

    public Door myDoor;

    public final int UNITLAYER = 3; //if you change this unitlayer, you need to also change it in the Unit class.

    public int dungeonColumns;
    public int dungeonRows;

    public GameStateManager myGSM;

    public StatusScreen myStatus;

    public Image emptyImage;
    public Image[] FPStest; //delete everything associated with this hardcoded garbage very soon.
    public int heroFrame; // delete this
    public Spell selectedSpell;

    public GameEngine(GameScreen myScreen, GameStateManager passedGSM) throws IOException {

        currentFloor = 1;
        statusImage = generateImage("/StatusHUD.png");

        dungeonColumns = 20;
        dungeonRows = 10;
        thisScreen = myScreen;
        myTiles = new Tile[dungeonColumns][dungeonRows];
        hoveredTile = new Point(-1, -1);
        Font GeneralFont = new Font("TimesRoman", Font.ROMAN_BASELINE, 30);
        myScreen.gbi.setFont(GeneralFont);

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
        selectedSpell = null; // Alex added this

        myHero = new Hero(0, 0, myTiles, "dknight_1.png", 100);
        turnHolder = myHero;

        friendlyCreatures = new ArrayList<FriendlyCreature>(); // Gorlenko added this
        myMonsters = new ArrayList<Monster>(); // Create an ArrayList object
        myMonsters.add(new Monster(4, 4, myTiles, "/Enemigos/beetle_fire_giant_1.png", new Equipment("/item/weapon/mace3.png", 30, "Mace of Power : damage + 30", "MaceOfPower", "Weapon"), 100));
        myMonsters.add(new Monster(4, 3, myTiles, "/cultist_3.png", new InventoryItem("/key_gold.png", "The key to the next level.", "L1Key"), 100));
        myWalls = new ArrayList<Wall>();
        myWalls.add(new Wall(2, 2, myTiles, "/dngn/wall/crystal_wall00.png"));
        myWalls.add(new Wall(3, 3, myTiles, "/dngn/wall/crystal_wall00.png"));
        myStatus = new StatusScreen();
        myDoor = new Door(5, 5, myTiles);
    }

    public void makeNewLevel() throws IOException {
        LevelGenerator.makeNewLevel(this);
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

        if (key == KeyEvent.VK_S) {
            spellButtonClicked();
            return;
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
                myStatus.pushMessage("Your Target is out of Range.");
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
                myStatus.pushMessage("Your Target is out of Range.");
            }
        } else {
            myStatus.pushMessage("You cannot Attack this area.");
        }

    }

    public void successfulTurn() { // this Function modified by Gorlenko
        if (turnHolder == myHero) {
            if(friendlyCreatures.size() != 0)
            {
                turnHolder = friendlyCreatures.get(0);
                allyTurn();
                if(myMonsters.size() != 0)
                {
                    turnHolder = myMonsters.get(0);
                }
                else
                {
                    turnHolder = myHero;
                }
            }
            else if(myMonsters.size() != 0)
            {
            turnHolder = myMonsters.get(0);
            return;
            }
        }
        
        
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

    public void allyTurn() // This function was added by Gorlenko
    {
        if(friendlyCreatures.size() == 0)
        {
            return;
        }
        else
        {
            for (int i = 0; i < friendlyCreatures.size(); i++) {

            if (friendlyCreatures.get(i).isAlive == false) {
                friendlyCreatures.remove(i);
                if (i != friendlyCreatures.size()) {
                    i--;
                    continue;
                } else {
                    break;
                }

            }

            if (friendlyCreatures.size() == 0) {
                break;
            }

            turnHolder = friendlyCreatures.get(i);
            selectedAlly = (FriendlyCreature) turnHolder; //this may need improvments
            try
            {
            selectedAlly.aiAction(myTiles, myStatus);
            }
            catch(Exception exc)
            {
                
            }
            if (friendlyCreatures.get(i).isAlive == false) {
                friendlyCreatures.remove(i);
                if (i != friendlyCreatures.size()) {
                    i--;
                }
            }

        }

        }
        
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
        if (!myHero.isAlive) {
            myGSM.setState(4);
        }
    }

    public void mouseMoved(MouseEvent e) {
        hoveredTile = calculateTile(e.getX(), e.getY());

        if (hoveredTile.x < dungeonColumns && hoveredTile.x >= 0 && hoveredTile.y < dungeonRows && hoveredTile.y >= 0) {
            return;
        }
        hoveredTile.x = -1;
        hoveredTile.y = -1;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getY() >= thisScreen.getSize().height * 4 / 5) // Alex added this statement
        {
            spellButtonClicked();
            return;
        }
        Point selectedTile = calculateTile(e.getX(), e.getY());
        if (selectedTile.x < dungeonColumns && selectedTile.x >= 0 && selectedTile.y < dungeonRows && selectedTile.y >= 0) {
            //myHero.move(selectedTile.x - myHero.x, selectedTile.y - myHero.y, myTiles, dungeonColumns, dungeonRows, myStatus);

            if (myHero.isAlive) {
                if (turnHolder == myHero) {
                    if (selectedSpell == null) // this condition was added by Alex
                    {
                        attemptAttack(selectedTile);
                        attemptUsage(selectedTile);
                    } else // this else was added by Alex
                    {
                        selectedSpell.castSpell(myHero, selectedTile.x, selectedTile.y);
                        if (turnHolder instanceof Monster) {
                            try {
                                monsterTurn();
                            } catch (Exception exc) {

                            }
                        }
                    }
                }

            }
        }
    }

    public void spellButtonClicked() // Alex added this
    {
        myGSM.setState(6); // goes to SpellBook
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

    public void draw() throws FontFormatException, IOException {
        Graphics2D myGraphic = thisScreen.gbi;
        drawTiles(myGraphic);
        drawStatus(myGraphic);
        drawHovering(myGraphic);
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

    public void drawHovering(Graphics2D myGraphic) {
        if (hoveredTile.x != -1 && hoveredTile.y != -1) {
            int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
            int myWidth = thisScreen.myBufferedDimension.width;
            MapObject hoveredObject = myTiles[hoveredTile.x][hoveredTile.y].myContents[UNITLAYER];
            if (hoveredObject instanceof Unit) {
                Unit hoveredUnit = (Unit) hoveredObject;
                myGraphic.drawString(hoveredUnit.hp + " / " + hoveredUnit.maxHP, ((myWidth / dungeonColumns) * hoveredTile.x), (myHeight / dungeonRows) * hoveredTile.y);
            }
        }
    }

    private void drawStatus(Graphics2D myGraphic) throws FontFormatException, IOException {

        int myHeight = (thisScreen.myBufferedDimension.height / 5) * 4;
        int myWidth = thisScreen.myBufferedDimension.width;
        myStatus.drawStatus(myGraphic, statusImage, myWidth, myHeight, thisScreen, myHero);
    }

}
