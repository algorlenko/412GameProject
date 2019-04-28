
import java.io.IOException;

public abstract class LevelGenerator {

    public static void makeNewLevel(GameEngine myEngine) throws IOException { // Make this a switch statement! Ugh!

        // GOING TO SWITCH THESE FOR TESTING
        // SPEAKING OF SWITCH, SWITCH WAS THE MATRIX'S BEST CHARACTER
        myEngine.friendlyCreatures.clear(); // I think we can actually do all the list clearing here to cut down on lines.
        switch (myEngine.currentFloor) {

            case 1:
                makeLevelTwo(myEngine);
                break;
            case 2:
                armeenMakesTheBestLevels(myEngine);
                break;
            case 3:
                levelFour(myEngine); // but why is this level tw CUZ I SAID SO 🤐
                break;
            default:
                break;
        }
    }

    public static void makeLevelTwo(GameEngine myEngine) throws IOException {

        for (int i = 0; i < myEngine.dungeonColumns; i++) {
            for (int j = 0; j < myEngine.dungeonRows; j++) {
                myEngine.myTiles[i][j] = new Tile(i, j, "/dngn/floor/green_bones12.png");
                //myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
      //  myEngine.friendlyCreatures.clear(); // This line needs to be done every single time a new level is made.
      myEngine.localShopKeep = new ShopKeeper(7, 7, myEngine);
        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(4, 4, myEngine.myTiles, "/elf_m.png", new Equipment("/item/armour/headgear/cap_jester.png", 20, "Fool's Hat: damage + 20", "FoolHat", "Helmet"), 100));
        myEngine.myMonsters.add(new Monster(4, 3, myEngine.myTiles, "/orange_demon.png", new Equipment("/item/armour/boots4_green.png", 20, "Boots of Speed : damage + 20", "BootsofSpeed", "Boots"), 100));
        myEngine.myMonsters.add(new Monster(5, 5, myEngine.myTiles, "/orange_demon.png", new InventoryItem("/key_gold.png", "The key to the next level.", "L1Key"), 100));
        myEngine.myWalls.clear();
        myEngine.myWalls.add(new Wall(10, 2, myEngine.myTiles, "/dngn/wall/brick_brown0.png"));
        myEngine.myWalls.add(new Wall(10, 3, myEngine.myTiles, "/dngn/wall/brick_brown0.png"));
       // myEngine.myTiles[myEngine.myHero.x][myEngine.myHero.y].myContents[3] = null;
       // myEngine.myTiles[myEngine.myHero.x][myEngine.myHero.y].imageName[3] = "/empty.png";
        myEngine.myHero.x = 9;
        myEngine.myHero.y = 9;
        myEngine.myHero.loadIntoTile(9, 9, myEngine.myTiles);
        myEngine.myDoor = new Door(8, 8, myEngine.myTiles);
        myEngine.currentFloor++;
    }

    public static void armeenMakesTheBestLevels(GameEngine myEngine) throws IOException {

        // DEFAULT FLOOR
        for (int i = 0; i < myEngine.dungeonColumns; i++) {
            for (int j = 0; j < myEngine.dungeonRows; j++) {
                if (i % 2 == 0) {
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_3.png");
                } else {
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_4.png");
                }
                //myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }

        
        
        // Carpeting baby // Hey remember when Bjork laid an egg on the red carpet?
        myEngine.myTiles[3][2] = new Tile(3, 2, "/Floor/uf_terrain/carpet_5.png");
        myEngine.myTiles[2][1] = new Tile(2, 1, "/Floor/uf_terrain/carpet.png");
        myEngine.myTiles[4][1] = new Tile(4, 1, "/Floor/uf_terrain/carpet_2.png");
        myEngine.myTiles[4][3] = new Tile(4, 3, "/Floor/uf_terrain/carpet_4.png");
        myEngine.myTiles[2][3] = new Tile(2, 3, "/Floor/uf_terrain/carpet_3.png");
        myEngine.myTiles[3][3] = new Tile(3, 3, "/Floor/uf_terrain/carpet_8.png");
        myEngine.myTiles[4][2] = new Tile(4, 2, "/Floor/uf_terrain/carpet_9.png");
        myEngine.myTiles[3][1] = new Tile(3, 1, "/Floor/uf_terrain/carpet_7.png");
        myEngine.myTiles[2][2] = new Tile(2, 2, "/Floor/uf_terrain/carpet_6.png");

        /*
        myEngine.myTiles[3][2] = new Tile(3, 2, "/Floor_Custom/carpet_5.png");
        myEngine.myTiles[2][1] = new Tile(2, 1, "/Floor_Custom/carpet.png");
        myEngine.myTiles[4][1] = new Tile(4, 1, "/Floor_Custom/carpet_2.png");
        myEngine.myTiles[4][3] = new Tile(4, 3, "/Floor_Custom/carpet_4.png");
        myEngine.myTiles[2][3] = new Tile(2, 3, "/Floor_Custom/carpet_3.png");
        myEngine.myTiles[3][3] = new Tile(3, 3, "/Floor_Custom/carpet_8.png");
        myEngine.myTiles[4][2] = new Tile(4, 2, "/Floor_Custom/carpet_9.png");
        myEngine.myTiles[3][1] = new Tile(3, 1, "/Floor_Custom/carpet_7.png");
        myEngine.myTiles[2][2] = new Tile(2, 2, "/Floor_Custom/carpet_6.png");
         */
        // MONSTER is the best anime of all time #FreeKenzoTenma #HotCocoa
              myEngine.localShopKeep = new ShopKeeper(15, 9, myEngine);
        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(3, 3, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null, 100));
        myEngine.myMonsters.add(new Monster(0, 7, myEngine.myTiles, "/Enemigos/uf_heroes/beetle_fire_1.png", null, 100));
        myEngine.myMonsters.add(new Monster(10, 2, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null, 100));
        myEngine.myMonsters.add(new Monster(12, 2, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null, 100));
        myEngine.myMonsters.add(new Monster(11, 1, myEngine.myTiles, "/Enemigos/uf_heroes/skeleton_1.png", new InventoryItem("/item/book/book_of_the_dead.png", "You were born in a town "
                + "straight out of a fairytale.", "L1Key"), 100));
        myEngine.myMonsters.add(new Monster(8, 7, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null, 100));
        myEngine.myMonsters.add(new Monster(11, 9, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null, 100));
        myEngine.myMonsters.add(new Monster(15, 8, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null, 100));

        /*
        myEngine.myMonsters.add(new Monster(4, 4, myEngine.myTiles, "/elf_m.png", new Equipment ("/item/armour/headgear/cap_jester.png", 20, "Fool's Hat: damage + 20", "FoolHat", "Helmet"), 100));
        myEngine.myMonsters.add(new Monster(6, 3, myEngine.myTiles, "orange_demon.png", new Equipment ("/item/armour/boots4_green.png", 20, "Boots of Speed : damage + 20", "BootsofSpeed", "Boots"), 100));
        myEngine.myMonsters.add(new Monster(8, 3, myEngine.myTiles, "/cultist_3.png", new InventoryItem("/key_gold.png", "The key to the next level.", "L1Key"), 100));
         */
        myEngine.myWalls.clear();

        // Here's some real goddamn WALLS son 😤😤😤
        for (int i = 0; i < 7; i++) {
            if (i != 4) {
                myEngine.myWalls.add(new Wall(i, 6, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); // Makes a row of wall at Y coordinate 6
            }
        }
        for (int i = 0; i < 6; i++) {
            if (i != 4) {
                myEngine.myWalls.add(new Wall(i, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a row of wall at Y coordinate 5
            }
        }
        for (int i = 0; i <= myEngine.dungeonRows - 1; i++) {
            if (i != 1) {
                myEngine.myWalls.add(new Wall(6, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a column of wall at X coordinate 6
            }
        }

        for (int i = 7; i < 20; i++) {
            if (i != 9 && i != 10 && i != 17 && i != 18) {
                myEngine.myWalls.add(new Wall(i, 4, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); // Makes a row at y4 from coordinate 7 to 19
            }
        }
        for (int i = 7; i < 20; i++) {
            if (i != 9 && i != 10 && i != 17 && i != 18) {
                myEngine.myWalls.add(new Wall(i, 3, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a row at y3 from coordinate 7 to 19
            }
        }

        for (int i = 0; i < myEngine.dungeonRows; i++) {
            if (i != 6) {
                myEngine.myWalls.add(new Wall(13, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a column at x13 from top to bottom
            }
        }

        // hey these aren't walls!!
        myEngine.myWalls.add(new Wall(10, 0, myEngine.myTiles, "/Floor/uf_terrain/coffin.png"));
        myEngine.myWalls.add(new Wall(11, 0, myEngine.myTiles, "/Floor/uf_terrain/coffin_open.png"));
        myEngine.myWalls.add(new Wall(12, 0, myEngine.myTiles, "/Floor/uf_terrain/coffin.png"));
        myEngine.myTiles[myEngine.myHero.x][myEngine.myHero.y].myContents[3] = null;
        //      myEngine.myTiles[myEngine.myHero.x][myEngine.myHero.y].imageName[3] = "/empty.png"; WHAT ARE THOOOOOSE
        myEngine.myHero.x = 1;
        myEngine.myHero.y = 9;
        myEngine.myHero.loadIntoTile(1, 9, myEngine.myTiles);
        myEngine.myDoor = new Door(18, 1, myEngine.myTiles);

        myEngine.currentFloor++;

        /*
        
        This makes every other column a different tile.
        
        for (int i = 0; i < myEngine.dungeonColumns; i++) {
            for (int j = 0; j < myEngine.dungeonRows; j++) {
                if(i % 2 == 0)
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_3.png");
                else
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_4.png");
                //myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        
        for (int i = 0; i < 7; i++) {
                myEngine.myWalls.add(new Wall(4, j, myEngine.myTiles, "/Floor/uf_terrain/coffin.png"));
            }
                if(i % 2 == 0)
                    myEngine.myTiles[i][4] = new Tile(i, j, "/Floor/floor_extra_3.png");
                else
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_4.png");
                //myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        
        
         */
    }

    /*
    
    MY FIRST LEVEL IS DONE. BITCH.
    
     */
    public static void /*bodyShieldHere*/ levelFour(GameEngine myEngine) throws IOException {

        for (int i = 0; i < myEngine.dungeonColumns; i++) {
            for (int j = 0; j < myEngine.dungeonRows; j++) {
                if (j % 2 == 0) {
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_3.png");
                } else {
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_4.png");
                }
                //myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }

        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(4, 4, myEngine.myTiles, "/elf_m.png", new Equipment("/item/armour/headgear/cap_jester.png", 20, "Fool's Hat: damage + 20", "FoolHat", "Helmet"), 100));
        myEngine.myMonsters.add(new Monster(4, 3, myEngine.myTiles, "/orange_demon.png", new Equipment("/item/armour/boots4_green.png", 20, "Boots of Speed : damage + 20", "BootsofSpeed", "Boots"), 100));
        myEngine.myMonsters.add(new Monster(5, 5, myEngine.myTiles, "/orange_demon.png", new Equipment("/item/weapon/mace2.png", 3, "Mace of Steel : damage + 3", "MaceOfSteel", "Weapon"), 100));
        myEngine.myMonsters.add(new Monster(11, 1, myEngine.myTiles, "/Enemigos/uf_heroes/skeleton_1.png", new InventoryItem("/item/book/book_of_the_dead.png", "You're a ghost, a fucking tragedy. "
                + "Everything you touch, everything that touches you...dies.", "L1Key"), 100));

        myEngine.myWalls.clear();
        myEngine.myWalls.add(new Wall(10, 2, myEngine.myTiles, "/dngn/wall/brick_brown0.png"));
        myEngine.myWalls.add(new Wall(10, 3, myEngine.myTiles, "/dngn/wall/brick_brown0.png"));
        myEngine.myTiles[myEngine.myHero.x][myEngine.myHero.y].myContents[3] = null;
        myEngine.myTiles[myEngine.myHero.x][myEngine.myHero.y].imageName[3] = "/empty.png";
        myEngine.myHero.x = 9;
        myEngine.myHero.y = 9;
        myEngine.myHero.loadIntoTile(9, 9, myEngine.myTiles);
        myEngine.currentFloor++;
    }

    /*
    
    Other cheeky "key" quotes:
    -"I've shown you all the roads, all the doors. They all lead to the same place."
    -"Why'd you only beckon when you're high?"
    -"Ghosts, demons, apparitions...you stink of it, kid. Enough to make the most powerful men in the world look away. But not me."
    -"You're playing a game you can't win."
    -"Kindness is not the province of men."
    -"You were a god once, and yet you found it...beneath you."
    
    
    
     */
}

/* THIS IS THE OLD CODE THAT ALEX USED TO MAKE LEVEL TWO
        for (int i = 0; i < dungeonColumns; i++) {
            for (int j = 0; j < dungeonRows; j++) {
                myTiles[i][j] = new Tile(i, j, "/dngn/floor/green_bones12.png");
                //myTiles[i][j].syncTileWithScreen(); 
            }
        }
        myMonsters.clear();
        myMonsters.add(new Monster(4, 4, myTiles, "/elf_m.png", new Equipment ("/item/armour/headgear/cap_jester.png", 20, "Fool's Hat: damage + 20", "FoolHat", "Helmet"), 100));
        myMonsters.add(new Monster(4, 3, myTiles, "orange_demon.png", new Equipment ("/item/armour/boots4_green.png", 20, "Boots of Speed : damage + 20", "BootsofSpeed", "Boots"), 100));
        myMonsters.add(new Monster(5, 5, myTiles, "orange_demon.png", new Equipment ("/item/weapon/mace2.png", 3, "Mace of Steel : damage + 3", "MaceOfSteel", "Weapon"), 100));
        myWalls.clear();
        myWalls.add(new Wall(10, 2, myTiles, "/dngn/wall/brick_brown0.png"));
        myWalls.add(new Wall(10, 3, myTiles, "/dngn/wall/brick_brown0.png"));
                        myTiles[myHero.x][myHero.y].myContents[3] = null;
                myTiles[myHero.x][myHero.y].imageName[3] = "/empty.png";
                myHero.x = 9;
                myHero.y = 9;
        myHero.loadIntoTile(9, 9, myTiles);
 */
