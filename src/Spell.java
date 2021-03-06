
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spell {

    final static int UNITLAYER = 3;
    public GameEngine myEngine;
    public String spellName;
    public Image spellImage;
    public int manaCost;
    public Spell(GameEngine passedEngine, String myName, String myImageName, int myManaCost) {
        myEngine = passedEngine;
        manaCost = myManaCost; 
        spellName = myName;
        try {
            spellImage = generateImage(myImageName);
        } catch (Exception e) {

        }
    }

    public void castSpell(Unit castingUnit, int targetedX, int targetedY) {
        if (spellName == "Teleport") {
            teleportAction(castingUnit, targetedX, targetedY, myEngine.myTiles, myEngine.dungeonColumns, myEngine.dungeonRows);
        }

        if (spellName == "Arcane Blast") {
            arcaneBlastAction(castingUnit, targetedX, targetedY, myEngine.myTiles, myEngine.dungeonColumns, myEngine.dungeonRows);
        }
        if (spellName == "Raise Skeleton") {
            summonSkeletonAction(castingUnit, targetedX, targetedY, myEngine.myTiles, myEngine.dungeonColumns, myEngine.dungeonRows);
        }
    }

    public void teleportAction(Unit castingUnit, int destinationX, int destinationY, Tile[][] myTiles, int dungeonColumns, int dungeonRows) {
        boolean teleportSuccess = castingUnit.moveTo(destinationX, destinationY, myTiles, dungeonColumns, dungeonRows);
        if (teleportSuccess && castingUnit == myEngine.myHero) {
            myEngine.myStatus.pushMessage("You have Teleported.");
            myEngine.thisScreen.resetCursor();
            myEngine.selectedSpell = null; // this could also be myHero.selectedSpell
            castingUnit.mana-= manaCost; 
            myEngine.successfulTurn();
        } else if (!teleportSuccess && castingUnit == myEngine.myHero) {
            myEngine.myStatus.pushMessage("You cannot Teleport there.");
        } else if (!teleportSuccess && castingUnit instanceof Monster) {
            // insert code here to make the enemy try teleporting again.
        }
    }

    public void arcaneBlastAction(Unit castingUnit, int destinationX, int destinationY, Tile[][] myTiles, int dungeonColumns, int dungeonRows) {
        boolean blastSuccess;
        if (myTiles[destinationX][destinationY].myContents[UNITLAYER] != null) {
            if (castingUnit == myEngine.myHero) {
                if (myTiles[destinationX][destinationY].myContents[UNITLAYER] instanceof Monster) {
                    Monster target = (Monster) myTiles[destinationX][destinationY].myContents[UNITLAYER];
                    try {
                        target.takeDamage(myEngine.myHero.intelligence, myTiles);
                    } catch (Exception exc) {

                    }
                    blastSuccess = true;
                    myEngine.myStatus.pushMessage("You have blasted the target for " + myEngine.myHero.intelligence + " damage.");
                    myEngine.thisScreen.resetCursor();
                    myEngine.selectedSpell = null; // this could also be myHero.selectedSpell
                    castingUnit.mana -= manaCost; 
                    myEngine.successfulTurn();
                    
                } else {
                    myEngine.myStatus.pushMessage("You can not blast there");
                }
            }
        }
    }

    public void summonSkeletonAction(Unit castingUnit, int destinationX, int destinationY, Tile[][] myTiles, int dungeonColumns, int dungeonRows) {
      //  boolean summonSuccess;
        if (destinationX < dungeonColumns && destinationX >= 0 && destinationY < dungeonRows && destinationY >= 0) {
            if (myTiles[destinationX][destinationY].myContents[UNITLAYER] == null) {
                try
                {
                myEngine.friendlyCreatures.add(new FriendlyCreature(destinationX, destinationY, "/mon/death_knight.png", myEngine.myHero.intelligence * 3, myEngine.myHero.intelligence, myEngine));
                myEngine.myStatus.pushMessage("You have Summoned an Undead Ally.");
            myEngine.thisScreen.resetCursor();
            myEngine.selectedSpell = null; // this could also be myHero.selectedSpell
            castingUnit.mana -= manaCost; 
            myEngine.successfulTurn();
                }
                catch(Exception exc)
                        {
                            
                        }
            }
            else
            {
                myEngine.myStatus.pushMessage("You can not Summon there.");
            }
        }
    }

    public Image generateImage(String myImageName) throws IOException {
        Image myResult = null;

        try {
            myResult = ImageIO.read(getClass().getResourceAsStream(myImageName)); //this is the new way
        } catch (Exception e) {
            e.printStackTrace();
            // myError = e.getMessage(); We may need this if the I/O goes wrong
        }
        //hasChanged = true;
        return myResult;
    }
}
