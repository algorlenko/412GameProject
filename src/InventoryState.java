
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.Math;
import javax.imageio.ImageIO;

public class InventoryState extends GameState {

    public GameStateManager myGSM;
    public GameScreen thisScreen;
    public Equipment myTestItem;

    public Image menuImage;
    public Inventory heroInventory;
    public Hero myHero;
    public int rows;
    public int columns;
    public int hoveredSlot;
    public int selectedSlot;
    public final int HELMET = 0;
    public final int TALISMAN = 1;
    public final int ARMOR = 2;
    public final int WEAPON = 3;
    public final int OFFHAND = 4;

    public final int BOOTS = 5;

    public InventoryState(GameScreen myScreen, GameStateManager passedGSM, Hero theHero) throws IOException {
        thisScreen = myScreen;
        myGSM = passedGSM;
        myHero = theHero;
        heroInventory = myHero.myInventory;
        rows = (int) Math.sqrt(heroInventory.storageSpace);
        columns = rows;
        hoveredSlot = -1;
        menuImage = generateImage("/Menu/inventorysheen2b.png");
        selectedSlot = -1;
    }

    public void draw() {
        //thisScreen.gbi.drawImage(myTestItem.image, 50, 50, null);
        //if (DeleteThisTestVariable == 1) {
        //   thisScreen.gbi.drawString(myTestItem.itemDescription, 50, 50);
        //  thisScreen.gbi.drawString(("This item is Level " + myTestItem.powerLevel), 50, 40);
        //}

        int myHeight = thisScreen.myBufferedDimension.height;
        int myWidth = thisScreen.myBufferedDimension.width / 2;
        thisScreen.gbi.drawImage(menuImage, 0, 0, myWidth * 2, myHeight, null);
        drawInventory(myWidth, myHeight);
        drawEquipped(myWidth, myHeight);
        drawDescription(myWidth, myHeight);
    }

    public void drawInventory(int myWidth, int myHeight) {

        int itemNumber = 0;

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                itemNumber = (j * columns) + i;
                if (itemNumber < heroInventory.storageSpace) {
                    if (heroInventory.items[itemNumber] != null) {
                        thisScreen.gbi.drawImage(heroInventory.items[itemNumber].image, ((myWidth / columns) * i) + 20, (myHeight / rows) * j + 20, (int) (myWidth * 0.8) / columns, (int) (myHeight * 0.8) / rows, null);
                    }

                }

            }
        }
    }

    public void drawEquipped(int myWidth, int myHeight) {
        for (int i = 0; i < myHero.SLOTS; i++) {
            if (myHero.equippedItems[i] != null) {
                thisScreen.gbi.drawImage(myHero.equippedItems[i].image, ((myWidth / columns) * 10) + 20, ((myHeight / rows) * i) + 20, (int) (myWidth * 0.8) / columns, (int) (myHeight * 0.8) / rows, null);
            }
        }

    }

    public void drawDescription(int myWidth, int myHeight) {
        if (hoveredSlot != -1 && hoveredSlot < heroInventory.storageSpace) {
            if (heroInventory.items[hoveredSlot] != null) {
                int y = hoveredSlot / rows;
                int x = hoveredSlot - (y * rows);
                thisScreen.gbi.drawString(heroInventory.items[hoveredSlot].itemDescription, ((myWidth / columns) * x), (myHeight / rows) * y + 50); //we need to replace this 50 with something non hardcoded ASAP
            }
        } else if (hoveredSlot >= heroInventory.storageSpace) {
            int hoveredEquipSlot = hoveredSlot - heroInventory.storageSpace;
            if (myHero.equippedItems[hoveredEquipSlot] != null) {
                int y = hoveredEquipSlot;
                int x = 9;
                thisScreen.gbi.drawString(myHero.equippedItems[hoveredEquipSlot].itemDescription, ((myWidth / columns) * x), (myHeight / rows) * y + 50); //we need to replace this 50 with something non hardcoded ASAP
            }
        }
        //thisScreen.gbi.drawString("The Hero's Current Attack Power Is: " + myHero.attackPower, 1000, 100);
        thisScreen.gbi.drawString("Current Attack Power: " + myHero.attackPower, 1000, 50);
        thisScreen.gbi.drawString("Current Gold is: " + (int) myHero.goldCoins, 1000, 100);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_I) {
            lightlyResetInventory();
            myGSM.setState(0); // Back to adventure screen

        }
        if (key == KeyEvent.VK_ESCAPE) {
            lightlyResetInventory();
            myGSM.setState(2); // Goes to Main Menu

        }
        
        // Now the only way to access the shop is by clicking on the shopkeeper when you are next to him.
        /* 
        if (key == KeyEvent.VK_S) {
            lightlyResetInventory();
            myGSM.setState(5); // Goes to shop State

        }
*/
    }

    public void lightlyResetInventory() {
        selectedSlot = -1;
        thisScreen.resetCursor();
    }

    public void keyReleased(KeyEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        hoveredSlot = calculateSlot(e.getX(), e.getY());
        //if (hoveredSlot >= heroInventory.storageSpace) { delete this soon
        //  hoveredSlot = -1;
        //}
    }

    public int calculateSlot(int x, int y) {
        int myWidth = thisScreen.getSize().width / 2;
        int myHeight = thisScreen.getSize().height;

        if (x * columns / myWidth < columns && y * rows / myHeight < rows) {
            return (x * columns / myWidth) + ((y * rows / myHeight) * columns);
        } else if (x * columns / myWidth >= columns && y * rows / myHeight < rows) {
            return heroInventory.storageSpace + (y * rows / myHeight);
        } else {
            return -1;
        }
    }

    public void mouseClicked(MouseEvent e) {
        hoveredSlot = calculateSlot(e.getX(), e.getY());

        if (hoveredSlot > -1) {
            if (hoveredSlot < heroInventory.storageSpace) // this part is buggy
            {

                if (heroInventory.items[hoveredSlot] != null && selectedSlot == -1) {
                    selectedSlot = hoveredSlot;
                    thisScreen.changeCursor(heroInventory.items[selectedSlot].image);
                } else if (selectedSlot > -1) {
                    if (selectedSlot < heroInventory.storageSpace) {
                        swapItems(selectedSlot, hoveredSlot);
                    } else if (selectedSlot >= heroInventory.storageSpace) {
                        if (heroInventory.items[hoveredSlot] == null) {
                            swapItems(selectedSlot, hoveredSlot);
                        } else {
                            if (attemptEquippingItem(hoveredSlot, selectedSlot - heroInventory.storageSpace)) {
                                lightlyResetInventory();
                            }
                        }
                    }
                }
            } else if (hoveredSlot >= heroInventory.storageSpace) {
                if (selectedSlot != -1) {
                    if (attemptEquippingItem(selectedSlot, hoveredSlot - heroInventory.storageSpace)) {
                        lightlyResetInventory();
                    }
                } else if (myHero.equippedItems[hoveredSlot - heroInventory.storageSpace] != null) {
                    selectedSlot = hoveredSlot;
                    thisScreen.changeCursor(myHero.equippedItems[hoveredSlot - heroInventory.storageSpace].image);
                }
            }

        }
    }

    public void swapItems(int firstSelectedSlot, int secondSelectedSlot) {
        if (selectedSlot != -1) {
            if (firstSelectedSlot < heroInventory.storageSpace && secondSelectedSlot < heroInventory.storageSpace) {
                InventoryItem tempItem;
                tempItem = heroInventory.items[firstSelectedSlot];
                heroInventory.items[firstSelectedSlot] = heroInventory.items[secondSelectedSlot];
                heroInventory.items[secondSelectedSlot] = tempItem;
                lightlyResetInventory();
            } else if (firstSelectedSlot < heroInventory.storageSpace && secondSelectedSlot >= heroInventory.storageSpace) {
                Equipment tempEquip;
                tempEquip = (Equipment) heroInventory.items[firstSelectedSlot];
                heroInventory.items[firstSelectedSlot] = myHero.equippedItems[secondSelectedSlot - heroInventory.storageSpace];
                myHero.unequip(myHero.equippedItems[secondSelectedSlot - heroInventory.storageSpace]);
                myHero.equip(tempEquip, secondSelectedSlot - heroInventory.storageSpace);

                lightlyResetInventory();
            } else if (secondSelectedSlot < heroInventory.storageSpace && firstSelectedSlot >= heroInventory.storageSpace) {
                Equipment tempEquip;
                tempEquip = (Equipment) heroInventory.items[secondSelectedSlot];
                heroInventory.items[secondSelectedSlot] = myHero.equippedItems[firstSelectedSlot - heroInventory.storageSpace];
                myHero.unequip(myHero.equippedItems[firstSelectedSlot - heroInventory.storageSpace]);
                myHero.equip(tempEquip, firstSelectedSlot - heroInventory.storageSpace);
                lightlyResetInventory();
            }

        }
    }

    public boolean attemptEquippingItem(int attemptedSlot, int attemptedEquipSlot) {
        if (hoveredSlot >= heroInventory.storageSpace && selectedSlot >= heroInventory.storageSpace) {
            return false;
        }
        InventoryItem attemptedItem;
        if (attemptedSlot < heroInventory.storageSpace) {
            attemptedItem = heroInventory.items[attemptedSlot];
        } else {
            attemptedItem = myHero.equippedItems[attemptedSlot - heroInventory.storageSpace];
        }

        if (attemptedItem == null) {
            swapItems(selectedSlot, hoveredSlot);
            return true;
        }
        if (attemptedItem instanceof Equipment) {
            Equipment attemptedEquipment = (Equipment) attemptedItem;
            if (equipmentCanGoThere(attemptedEquipment, attemptedEquipSlot)) {
                swapItems(selectedSlot, hoveredSlot);
                return true;
            }

        }
        return false;
    }

    public boolean equipmentCanGoThere(Equipment aE, int aES) {
        return (aE.equipType == "Helmet" && aES == HELMET)
                || (aE.equipType == "Talisman" && aES == TALISMAN)
                || (aE.equipType == "Armor" && aES == ARMOR)
                || (aE.equipType == "Weapon" && aES == WEAPON)
                || (aE.equipType == "Offhand" && aES == OFFHAND)
                || (aE.equipType == "Boots" && aES == BOOTS);
    }

    public void mouseReleased(MouseEvent e) {

    }

    public Image generateImage(String myImageName) throws IOException {
        Image myResult = null;

        try {
            myResult = ImageIO.read(getClass().getResourceAsStream(myImageName)); //this is the new way
        } catch (Exception e) {
            e.printStackTrace();
        }
        //hasChanged = true;
        return myResult;
    }
}
