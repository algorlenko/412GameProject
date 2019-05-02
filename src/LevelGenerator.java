
import java.io.*;
import javax.sound.sampled.*;

public abstract class LevelGenerator {

    
    
    public static void makeNewLevel(GameEngine myEngine) throws IOException, UnsupportedAudioFileException, FileNotFoundException {
        
        AudioPlayer iPod;
        myEngine.friendlyCreatures.clear(); 
        myEngine.myHero.recover();
        // GOING TO SWITCH THESE FOR TESTING
        // SPEAKING OF SWITCH, SWITCH WAS THE MATRIX'S BEST CHARACTER
        switch (myEngine.currentFloor) {
        
            case 1:{
             // iPod = new AudioPlayer("/Musica/edit2.wav");
             // iPod.play(); IT WORKS :) but i can't make it stop...
                armeenMakesTheBestLevels(myEngine);
                myEngine.myStatus.pushMessage("You come down a set of stairs to a find even more monsters.");
                myEngine.myStatus.pushMessage("You need to find your way out of here.");
                myEngine.myStatus.pushMessage("Guess you’ll have to fight your way out.");
                break;                
            }
            case 2: // could use some more enemies? nah
                levelFour(myEngine);
                myEngine.myStatus.pushMessage("Are those blue dragons?! And skeleton soldiers?!");
                myEngine.myStatus.pushMessage("What is this place?");
                myEngine.myStatus.pushMessage("You need to get out here fast.");
                break;
            case 3:
                levelFive(myEngine);
                myEngine.myStatus.pushMessage("As you go deeper down the stairs");
                myEngine.myStatus.pushMessage("you notice a funny hazy feeling. ");
                myEngine.myStatus.pushMessage("How did you learn to fight like this?");
                break;
            case 4:
                levelSix(myEngine);
                myEngine.myStatus.pushMessage("Something isn’t right here, where");
                myEngine.myStatus.pushMessage("did this sand come from. And all");
                myEngine.myStatus.pushMessage("of these crazy monsters, it is so confusing.");
                break;
            case 5:
                


                levelEight(myEngine);
                myEngine.myStatus.pushMessage("You walk into a room that appears completely open.");
                myEngine.myStatus.pushMessage("You get really dizzy and feel the room spinning around you.");
                myEngine.myStatus.pushMessage("Does this place end? Is this hell?");
                break;
            case 6:
                

                levelSeven(myEngine);
                myEngine.myStatus.pushMessage("Is it you or are the walls closing in?");
                myEngine.myStatus.pushMessage("Those aren’t walls! Quick fight your way out.");
                myEngine.myStatus.pushMessage("");
                break;
            default:
                break;
                
                /* THE STORY SO FAR:
                
                -aMTBL: Beautiful, absolutely impeccable. 10/10 (except for one wall)
                -levelFour: Sparse, but promising. Enemies need to be moved around. 7/10
                -levelFive: Sparse, but promising. 7/10
                -levelSix: Doesn't exist yet, but whatevs. 1/10
                -makeLevelTwo: Horrible, an absolute abomination. -5/10
                -levelSeven: Fiendishly difficult unless you teleport, should probably be the last level.
               
                
                1. You wake up in an open garden. How did you get here? There is crazy person guarding the only way out of here.

2. You come down a set of stairs to a find even more monsters. You need to find your way out of here. 
Guess you’ll have to fight your way out.

3. Are those blue dragons?! And skeleton soldiers?! 
What is this place?
You need to get out here fast.

4. As you go deeper down the stairs you notice a funny hazy feeling. 
How did you learn to fight like this?

5. Something isn’t right here, where did this sand come from. 
And all of these crazy monsters, it is so confusing.

6. You walk into a room that appears completely open.
You get really dizzy and feel the room spinning around you.
Does this place end? Is this hell?

7. Is it you or are the walls closing in?
Those aren’t walls! Quick fight your way out.

8. The walls down here are attacking you too!
You start to hear a constant faint beeping.
                Recommended Order:
                -Arm
                -Four
                -Five (fix the stone walls)
                -Six (the desert level)
                -Eight
                -Seven
                
                */
        }
    }
    
    
    public static void armeenMakesTheBestLevels(GameEngine myEngine) throws IOException {
        
        // DEFAULT FLOOR
        for (int i = 0; i < myEngine.dungeonColumns; i++) {
            for (int j = 0; j < myEngine.dungeonRows; j++) {
                if(i % 2 == 0)
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_3.png");
                else
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/floor_extra_4.png");
                //myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        
        myEngine.myTiles[18][0] = new Tile(18, 0, "/Floor/uf_terrain/floor_set_grey_10d.png"); // STAIR
        
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
        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(3, 3, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100,100,5));
        myEngine.myMonsters.add(new Monster(5, 7, myEngine.myTiles, "/Enemigos/uf_heroes/beetle_fire_1.png", null,100,100,2));
        myEngine.myMonsters.add(new Monster(9, 2, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", new InventoryItem("/item/book/parchment.png", "You will die here ",
                "nothing"),100,100,4));
        myEngine.myMonsters.add(new Monster(11, 2, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100,100,5));
        myEngine.myMonsters.add(new Monster(10, 3, myEngine.myTiles, "/Enemigos/uf_heroes/skeleton_1.png", new InventoryItem("/item/book/book_of_the_dead.png", "You were born in a town "
                + "straight out of a fairytale.", "L1Key"), 100,100,4));
        myEngine.myMonsters.add(new Monster(8, 7, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100,100,4));
        myEngine.myMonsters.add(new Monster(11, 8, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100,100,4));
        myEngine.myMonsters.add(new Monster(15, 8, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100,100,4));

        myEngine.myWalls.clear();
        

        // microsoft edge walls
        for (int i = 0; i < myEngine.dungeonColumns; i++)
        {
            for(int j=0;j<myEngine.dungeonRows;j++){
                if( j==myEngine.dungeonRows-1 || i==0 || i==myEngine.dungeonColumns-1)
                    myEngine.myWalls.add(new Wall(i, j, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); 
            }
            
                
        }
        
        // top walls
        for (int i = 1; i < myEngine.dungeonColumns - 1; i++)
        {
            myEngine.myWalls.add(new Wall(i, 0, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); 
        }
        
        
        // Here's some real goddamn WALLS son 😤😤😤
        for (int i = 1; i < 7; i++)
        {
            if(i != 4)
                myEngine.myWalls.add(new Wall(i, 6, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); // Makes a row of wall at Y coordinate 6
        }
        for (int i = 0; i < 6; i++)
        {
            if(i != 4)
                myEngine.myWalls.add(new Wall(i, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a row of wall at Y coordinate 5
        }
        for (int i = 2; i <= myEngine.dungeonRows - 1; i++)
        {
            if(i != 1)
                myEngine.myWalls.add(new Wall(6, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a column of wall at X coordinate 6
        }
        
        for (int i = 7; i < 19; i++)
        {
            if (i != 9 && i != 10 && i != 17 && i != 18)
                myEngine.myWalls.add(new Wall(i, 4, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); // Makes a row at y4 from coordinate 7 to 19
        }
        for (int i = 7; i < 20; i++)
        {
            if (i != 9 && i != 10 && i != 17 && i != 18)
                myEngine.myWalls.add(new Wall(i, 3, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a row at y3 from coordinate 7 to 19
        }
            
        for (int i = 0; i < myEngine.dungeonRows; i++)
        {
            if (i != 6)
            {
                myEngine.myWalls.add(new Wall(13, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a column at x13 from top to bottom
            }
        }
        
        // hey these aren't walls!!
        myEngine.myWalls.add(new Wall(9, 1, myEngine.myTiles, "/Floor/uf_terrain/coffin.png"));
        myEngine.myWalls.add(new Wall(10, 1, myEngine.myTiles, "/Floor/uf_terrain/coffin_open.png"));
        myEngine.myWalls.add(new Wall(11, 1, myEngine.myTiles, "/Floor/uf_terrain/coffin.png")); 
        
                myEngine.myHero.x = 1;
                myEngine.myHero.y = 8;
        myEngine.myHero.loadIntoTile(1, 8, myEngine.myTiles);
        
        myEngine.localShopKeep = new ShopKeeper(1, 1, myEngine);
        myEngine.myDoor = new Door(18, 0, myEngine.myTiles);
        
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
    
    
    public static void /*bodyShieldHere*/levelFour(GameEngine myEngine) throws IOException { // THERE'S NO DOOR
        
        // default floor tiles
        for (int i = 0; i < myEngine.dungeonColumns; i++) { // columns
            for (int j = 0; j < myEngine.dungeonRows; j++) { // rows
                if(i % 2 == 0)
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_3.png");
                else
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_4.png");
                // myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        
        
        
        
        
        // Other rooms
        
        for (int i = 0; i < 6; i++){ // Top left corner
            for (int j = 0; j < 6; j++){
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_20.png"); // Pink is Placeholder
            }
        }
          
        for (int i = 0; i < 6; i++){ // Bottom left corner; key will be here
            for (int j = 6; j < myEngine.dungeonRows; j++){
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_20.png");
            }
        }
        
        for (int i = 14; i < myEngine.dungeonColumns; i++){ // Top right corner
            for (int j = 0; j < 6; j++){ // put a wall on row 6...or is it 5? SHIT
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_21.png");
            }
        }
        for (int i = 14; i < myEngine.dungeonColumns; i++){ // Bottom right corner; door will be here
            for (int j = 6; j < myEngine.dungeonRows; j++){
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_21.png");
            }
        }
        
        // microsoft edge walls
        
        for (int i = 0; i < myEngine.dungeonColumns; i++)
        {
            for(int j=0;j<myEngine.dungeonRows;j++){
                if( j==myEngine.dungeonRows-1 || i==0 || i==myEngine.dungeonColumns-1)
                    myEngine.myWalls.add(new Wall(i, j, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); 
            }
            
        }
        
        // top walls
        for (int i = 1; i < myEngine.dungeonColumns - 1; i++)
        {
            myEngine.myWalls.add(new Wall(i, 0, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); 
        }
        
        
        
        // Left Side Walls
        for (int i = 1; i < 6; i++)
        {
            if(i != 3)
                myEngine.myWalls.add(new Wall(i, 6, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); // Makes a row of wall at Y coordinate 5
        }
        for (int i = 1; i < 6; i++)
        {
            if(i != 3)
                myEngine.myWalls.add(new Wall(i, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a row of wall at Y coordinate 5
        }
        
        for (int i = 6; i <= myEngine.dungeonRows - 1; i++)
        {
            if(i != 1)
                myEngine.myWalls.add(new Wall(6, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a column of wall at X coordinate 6, starting at row 6
        }
        
        myEngine.myWalls.add(new Wall(6, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_9.png")); // check those corners!
        for (int i = 1; i <= 5; i++)
        {
            if(i != 1)
                myEngine.myWalls.add(new Wall(5, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a column of wall at X coordinate 5 until row 5
        }
        
        // Right Side Walls
        for (int i = 14; i <= myEngine.dungeonColumns - 2; i++)
        {
            if(i != 17)
                myEngine.myWalls.add(new Wall(i, 6, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); // Makes a row of wall at Y coordinate 6
        }
        for (int i = 15; i <= myEngine.dungeonColumns - 2; i++)
        {
            if(i != 17)
                myEngine.myWalls.add(new Wall(i, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a row of wall at Y coordinate 5
        }
        for (int i = 6; i <= myEngine.dungeonRows - 1; i++)
        {
            // if(i != 800)
                myEngine.myWalls.add(new Wall(13, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a column of wall at X coordinate 6, starting at row 14
        }
        myEngine.myWalls.add(new Wall(13, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_9.png")); // check those corners!
        for (int i = 1; i <= 5; i++)
        {
             if(i != 1)
                myEngine.myWalls.add(new Wall(14, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); // Makes a column of wall at X coordinate 15 until row 5
        }
        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(1, 7, myEngine.myTiles, "/Enemigos/uf_heroes/elf_1.png", new Equipment ("/item/armour/headgear/cap_jester.png", -100,0,0, 
                "I can give you an application for the circus if you want to keep acting a damn fool: damage - 100", "FoolHat", "Helmet"), 120,0,5)); // Enemies can have health above 100, pls fix
        myEngine.myMonsters.add(new Monster(3, 4, myEngine.myTiles, "/Enemigos/uf_heroes/demon_blue_1.png", new Equipment ("/item/armour/shields/shield1.png", 0,15,0, "Sheild: armor + 15", "Sheild", "Offhand"), 75,100,6));
        myEngine.myMonsters.add(new Monster(10, 5, myEngine.myTiles, "/Enemigos/uf_heroes/demon_blue_1.png", new Equipment ("/item/armour/leather_armour1.png", 1,15,0, "Leather Armor : armor + 15", "Larmor", "Armor"), 75,100,6));
        myEngine.myMonsters.add(new Monster(14, 7, myEngine.myTiles, "/Enemigos/uf_heroes/demon_blue_1.png", null, 100,100,4));
        myEngine.myMonsters.add(new Monster(8, 5, myEngine.myTiles, "/Enemigos/uf_heroes/demon_blue_1.png", new Equipment ("/item/armour/robe1.png", 0,0,5, "Cloak, intellegence + 5", "Cloak", "Armor"), 75,100,6));
        myEngine.myMonsters.add(new Monster(11, 1, myEngine.myTiles, "/Enemigos/uf_heroes/skeleton_1.png", new InventoryItem("/item/book/book_of_the_dead.png", "PRO TIP: You can't equp books "
                + "Everything you touch, everything that touches you...dies.", "L1Key"), 200,200,5));
        
        myEngine.myWalls.clear();

                        
                myEngine.myHero.x = 9;
                myEngine.myHero.y = 8;
        myEngine.myHero.loadIntoTile(9, 8, myEngine.myTiles);
        myEngine.myTiles[17][0] = new Tile(17, 0, "/Floor/uf_terrain/floor_set_grey_10d.png"); // DOES THIS MAKE SENSE??
        myEngine.myDoor = new Door(17, 0, myEngine.myTiles); // YES
        myEngine.localShopKeep = new ShopKeeper(18, 8, myEngine);
        myEngine.currentFloor++;
    }
    
    
    public static void levelFive(GameEngine myEngine) throws IOException {
        
        // default floor tiles
        for (int i = 0; i < myEngine.dungeonColumns; i++) { // columns
            for (int j = 0; j < myEngine.dungeonRows; j++) { // rows
                if(i % 2 == 0)
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_3.png");
                else
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_4.png");
                // myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        
        // Other Rooms (Top Left)
        for (int i = 9; i < myEngine.dungeonColumns; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_22.png");
            }
        }
        
        // Bottom Left
        for (int i = 9; i < myEngine.dungeonColumns; i++)
        {
            for(int j = 5; j < myEngine.dungeonRows; j++)
            {
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_18.png");
            }
        }
        
        
        
        
        /*// Carpeting baby // if you're reading this jonathan is a flake
        myEngine.myTiles[11][7] = new Tile(11, 7, "/Floor/uf_terrain/carpet_5.png"); // Should move the carpet up by one...eventually
        myEngine.myTiles[10][6] = new Tile(10, 6, "/Floor/uf_terrain/carpet.png");
        myEngine.myTiles[12][6] = new Tile(12, 6, "/Floor/uf_terrain/carpet_2.png");
        myEngine.myTiles[12][8] = new Tile(12, 8, "/Floor/uf_terrain/carpet_4.png");
        myEngine.myTiles[10][8] = new Tile(10, 8, "/Floor/uf_terrain/carpet_3.png");
        myEngine.myTiles[11][8] = new Tile(11, 8, "/Floor/uf_terrain/carpet_8.png");
        myEngine.myTiles[12][7] = new Tile(12, 7, "/Floor/uf_terrain/carpet_9.png");
        myEngine.myTiles[11][6] = new Tile(11, 6, "/Floor/uf_terrain/carpet_7.png");
        myEngine.myTiles[10][7] = new Tile(10, 7, "/Floor/uf_terrain/carpet_6.png");
        */
        
        // microsoft edge walls
        
        for (int i = 0; i < myEngine.dungeonColumns; i++)
        {
            for(int j=0;j<myEngine.dungeonRows;j++){
                if( j==myEngine.dungeonRows-1 || i==0 || i==myEngine.dungeonColumns-1)
                    myEngine.myWalls.add(new Wall(i, j, myEngine.myTiles, "/Floor/uf_terrain/wall_stone_6.png")); 
            }
            
        }
        // top walls
        for (int i = 1; i < myEngine.dungeonColumns - 1; i++)
        {
            myEngine.myWalls.add(new Wall(i, 0, myEngine.myTiles, "/Floor/uf_terrain/wall_stone_14.png")); 
        }
        
        // Middle Wall
        for (int i = 0; i < myEngine.dungeonRows; i++)
        {
            if(i % 2 == 0 && i != 6)
                myEngine.myWalls.add(new Wall(9, i, myEngine.myTiles, "/Floor/uf_terrain/wall_stone_8.png")); 
            else if (i != 6)
                myEngine.myWalls.add(new Wall(9, i, myEngine.myTiles, "/Floor/uf_terrain/wall_stone_7.png")); 
        }
        myEngine.myWalls.add(new Wall(9, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_stone_16.png")); 
        
        // ...other Middle Wall
        for (int i = 10; i < myEngine.dungeonColumns - 1; i++)
        {
            if((i % 2 == 1 || i % 3 == 0) && i != 15)
                myEngine.myWalls.add(new Wall(i, 4, myEngine.myTiles, "/Floor/uf_terrain/wall_stone_14.png")); 
            else if (i != 15)
                myEngine.myWalls.add(new Wall(i, 4, myEngine.myTiles, "/Floor/uf_terrain/wall_stone_15.png")); 
        }
        
        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(2, 3, myEngine.myTiles, "/Enemigos/uf_heroes/necromancer_1.png", new Equipment ("/item/armour/scale_mail1.png", 0,30,0, 
                "Scale Mail, Armor + 30", "scaleMail", "Armor"), 50,150,12));
        myEngine.myMonsters.add(new Monster(6, 4, myEngine.myTiles, "/Enemigos/uf_heroes/necromancer_1.png", null, 50,120,12));
        myEngine.myMonsters.add(new Monster(3, 7, myEngine.myTiles, "/Enemigos/uf_heroes/necromancer_1.png", new Equipment ("/item/armour/shields/large_shield1.png", 0,10,5, "Wizards Sheild : Armor +10, Intellegence +5", "LargeSheild", "Armor"), 50,0,12));
        myEngine.myMonsters.add(new Monster(5, 6, myEngine.myTiles, "/Enemigos/uf_heroes/necromancer_1.png", null, 100, 100,5));
        myEngine.myMonsters.add(new Monster(16, 7, myEngine.myTiles, "/Enemigos/uf_heroes/necromancer_1.png", null,50,100,12));
        myEngine.myMonsters.add(new Monster(8, 5, myEngine.myTiles, "/Enemigos/uf_heroes/necromancer_1.png", new Equipment ("/item/weapon/spear3.png", 14,0,0, "Spear : damage + 14", "spear", "Weapon"), 50,100,12));
        myEngine.myMonsters.add(new Monster(11, 1, myEngine.myTiles, "/Enemigos/uf_heroes/skeleton_1.png", new InventoryItem("/item/food/lemon.png", "😬 Warning:"
                + " Good luck 😬", "L1Key"), 200,100,5));
        
        myEngine.myWalls.clear();
        
                myEngine.myHero.x = 17;
                myEngine.myHero.y = 2;
        myEngine.myHero.loadIntoTile(17, 2, myEngine.myTiles);
        myEngine.myTiles[2][0] = new Tile(2, 0, "/Floor/uf_terrain/floor_set_grey_10d.png"); // STAIR
        myEngine.myDoor = new Door(2, 0, myEngine.myTiles);
        myEngine.localShopKeep = new ShopKeeper(1, 1, myEngine);
        myEngine.currentFloor++;
        
    }
    
    public static void levelSeis(GameEngine myEngine) throws IOException { // unused
        
        // default floor tiles
        for (int i = 0; i < myEngine.dungeonColumns; i++) { // columns
            for (int j = 0; j < myEngine.dungeonRows; j++) { // rows
                if(i % 2 == 0)
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_3.png");
                else
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_4.png");
                // myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        
        // Other Rooms
        
        
        // microsoft edge walls
        
        for (int i = 0; i < myEngine.dungeonColumns; i++)
        {
            for(int j=0;j<myEngine.dungeonRows;j++){
                if( j==myEngine.dungeonRows-1 || i==0 || i==myEngine.dungeonColumns-1)
                    myEngine.myWalls.add(new Wall(i, j, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6.png")); 
            }
            
        }
        // top walls
        for (int i = 1; i < myEngine.dungeonColumns - 1; i++)
        {
            myEngine.myWalls.add(new Wall(i, 0, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14.png")); 
        }
        
        
    }
    
    public static void levelSeven/*oh shit*/(GameEngine myEngine) throws IOException {
        myEngine.myMonsters.clear();
        // DEFAULT FLOOR
        for (int i = 0; i < myEngine.dungeonColumns; i++) {
            for (int j = 0; j < myEngine.dungeonRows; j++) {
                if(i % 2 == 0)
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_3rd.png");
                else
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_4rd.png");
                //myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        
        myEngine.myTiles[18][0] = new Tile(18, 0, "/Floor/uf_terrain/floor_set_grey_10d.png"); // STAIR
        
        
        myEngine.myTiles[3][2] = new Tile(3, 2, "/Floor_Custom/carpet_5.png");
        myEngine.myTiles[2][1] = new Tile(2, 1, "/Floor_Custom/carpet.png");
        myEngine.myTiles[4][1] = new Tile(4, 1, "/Floor_Custom/carpet_2.png");
        myEngine.myTiles[4][3] = new Tile(4, 3, "/Floor_Custom/carpet_4.png");
        myEngine.myTiles[2][3] = new Tile(2, 3, "/Floor_Custom/carpet_3.png");
        myEngine.myTiles[3][3] = new Tile(3, 3, "/Floor_Custom/carpet_8.png");
        myEngine.myTiles[4][2] = new Tile(4, 2, "/Floor_Custom/carpet_9.png");
        myEngine.myTiles[3][1] = new Tile(3, 1, "/Floor_Custom/carpet_7.png");
        myEngine.myTiles[2][2] = new Tile(2, 2, "/Floor_Custom/carpet_6.png");
        
        
        // MONSTER is the best anime of all time #JohanDidNothingWrong
//        myEngine.myMonsters.clear();
//        myEngine.myMonsters.add(new Monster(3, 3, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100));
//        myEngine.myMonsters.add(new Monster(5, 7, myEngine.myTiles, "/Enemigos/uf_heroes/beetle_fire_1.png", null,100));
//        myEngine.myMonsters.add(new Monster(9, 2, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", new InventoryItem("/item/book/parchment.png", "GO CRAZY GO STUPID ",
//                "nothing"),100));
//        myEngine.myMonsters.add(new Monster(11, 2, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100));
        myEngine.myMonsters.add(new Monster(10, 6, myEngine.myTiles, "/Enemigos/uf_heroes/skeleton_1.png", new InventoryItem("/item/book/book_of_the_dead.png", "If you teleported to the end, "
                + "The Real Monster is you", "L1Key"), 100));
//        myEngine.myMonsters.add(new Monster(8, 7, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100));
//        myEngine.myMonsters.add(new Monster(11, 8, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100));
//        myEngine.myMonsters.add(new Monster(15, 8, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", null,100));

        myEngine.myWalls.clear();
        

        // microsoft edge walls
        for (int i = 0; i < myEngine.dungeonColumns; i++)
        {
            for(int j=0;j<myEngine.dungeonRows;j++){
                if( j==myEngine.dungeonRows-1 || i==0 || i==myEngine.dungeonColumns-1)
                    myEngine.myWalls.add(new Wall(i, j, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6rd.png")); 
            }
            
                
        }
        
        // top walls
        for (int i = 1; i < myEngine.dungeonColumns - 1; i++)
        {
            myEngine.myWalls.add(new Wall(i, 0, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14rd.png")); 
        }
        
        
        // Here's some fake goddamn WALLS son 😤😤😤
        for (int i = 1; i < 7; i++)
        {
            if(i != 4)
                myEngine.myMonsters.add(new Monster(i, 6, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14rd.png", null, 100)); // Makes a row of wall at Y coordinate 6
        }
        for (int i = 0; i < 6; i++)
        {
            if(i != 4)
                myEngine.myMonsters.add(new Monster(i, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6rd.png", null, 100)); // Makes a row of wall at Y coordinate 5
        }
        for (int i = 2; i <= myEngine.dungeonRows - 1; i++)
        {
            if(i != 1)
                myEngine.myMonsters.add(new Monster(6, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6rd.png", null, 100)); // Makes a column of wall at X coordinate 6
        }
        
        for (int i = 7; i < 19; i++)
        {
            if (i != 9 && i != 10 && i != 17 && i != 18)
                myEngine.myMonsters.add(new Monster(i, 4, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14rd.png", null, 100)); // Makes a row at y4 from coordinate 7 to 19
        }
        for (int i = 7; i < 20; i++)
        {
            if (i != 9 && i != 10 && i != 17 && i != 18)
                myEngine.myMonsters.add(new Monster(i, 3, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6rd.png", null, 100)); // Makes a row at y3 from coordinate 7 to 19
        }
            
        for (int i = 0; i < myEngine.dungeonRows; i++)
        {
            if (i != 6)
            {
                myEngine.myMonsters.add(new Monster(13, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6rd.png", null, 100)); // Makes a column at x13 from top to bottom
            }
        }
        
        // hey these aren't walls!!
        myEngine.myWalls.add(new Wall(9, 1, myEngine.myTiles, "/Floor/uf_terrain/coffin.png"));
        myEngine.myWalls.add(new Wall(10, 1, myEngine.myTiles, "/Floor/uf_terrain/coffin_open.png"));
        myEngine.myWalls.add(new Wall(11, 1, myEngine.myTiles, "/Floor/uf_terrain/coffin.png")); 
        
                myEngine.myHero.x = 1;
                myEngine.myHero.y = 8;
        myEngine.myHero.loadIntoTile(1, 8, myEngine.myTiles);
        
        myEngine.localShopKeep = new ShopKeeper(1, 1, myEngine);
        myEngine.myDoor = new Door(18, 0, myEngine.myTiles);
        
        myEngine.currentFloor++;
        
        
       
    }
    
    public static void levelEight(GameEngine myEngine) throws IOException { // Will have four rooms, different textures (checkerboard!)
        
        
                myEngine.myWalls.clear();
        // default floor tiles
        for (int i = 0; i < myEngine.dungeonColumns; i++) { // columns
            for (int j = 0; j < myEngine.dungeonRows; j++) { // rows
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_16.png");
            }
        }
        
        // Other Rooms (Top Left)
        for (int i = 9; i < myEngine.dungeonColumns; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_16.png");
                
            }
        }
        
        // Bottom Left
        for (int i = 9; i < myEngine.dungeonColumns; i++)
        {
            for(int j = 5; j < myEngine.dungeonRows; j++)
            {
               myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra_16.png");
            }
        }
        
        
        
        
        /*// Carpeting baby // if you're reading this jonathan is a flake
        myEngine.myTiles[11][7] = new Tile(11, 7, "/Floor/uf_terrain/carpet_5.png"); // Should move the carpet up by one...eventually
        myEngine.myTiles[10][6] = new Tile(10, 6, "/Floor/uf_terrain/carpet.png");
        myEngine.myTiles[12][6] = new Tile(12, 6, "/Floor/uf_terrain/carpet_2.png");
        myEngine.myTiles[12][8] = new Tile(12, 8, "/Floor/uf_terrain/carpet_4.png");
        myEngine.myTiles[10][8] = new Tile(10, 8, "/Floor/uf_terrain/carpet_3.png");
        myEngine.myTiles[11][8] = new Tile(11, 8, "/Floor/uf_terrain/carpet_8.png");
        myEngine.myTiles[12][7] = new Tile(12, 7, "/Floor/uf_terrain/carpet_9.png");
        myEngine.myTiles[11][6] = new Tile(11, 6, "/Floor/uf_terrain/carpet_7.png");
        myEngine.myTiles[10][7] = new Tile(10, 7, "/Floor/uf_terrain/carpet_6.png");
        */
        
        // microsoft edge walls
        
        for (int i = 0; i < myEngine.dungeonColumns; i++)
        {
            for(int j=0;j<myEngine.dungeonRows;j++){
                if( j==myEngine.dungeonRows-1 || i==0 || i==myEngine.dungeonColumns-1)
                    //myEngine.myWalls.add(new Wall(i, j, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_6b.png"));
                    myEngine.myWalls.add(new Wall(i, j, myEngine.myTiles, "/empty.png")); 
            }
            
        }
        // top walls
        for (int i = 1; i < myEngine.dungeonColumns - 1; i++)
        {
            //myEngine.myWalls.add(new Wall(i, 0, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14b.png"));
            myEngine.myWalls.add(new Wall(i, 0, myEngine.myTiles, "/empty.png")); 
        }
        
        // Middle Wall
        for (int i = 0; i < myEngine.dungeonRows; i++)
        {
            if(i % 2 == 0 && i != 6)
               // myEngine.myWalls.add(new Wall(9, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_8b.png")); 
                myEngine.myWalls.add(new Wall(9, i, myEngine.myTiles, "empty.png")); 
            else if (i != 6)
                //myEngine.myWalls.add(new Wall(9, i, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_7b.png"));
            myEngine.myWalls.add(new Wall(9, i, myEngine.myTiles, "/empty.png"));
        }
        //myEngine.myWalls.add(new Wall(9, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_16b.png")); 
        myEngine.myWalls.add(new Wall(9, 5, myEngine.myTiles, "/empty.png")); 
        // ...other Middle Wall
        for (int i = 1; i < myEngine.dungeonColumns - 1; i++)
        {
            if((i % 2 == 1 || i % 3 == 0) && i != 15 && i != 9)
                //myEngine.myWalls.add(new Wall(i, 4, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_14b.png")); 
                myEngine.myWalls.add(new Wall(i, 4, myEngine.myTiles, "/empty.png")); // we are cmmenting the beautiful walls in exchange for a trippy level.
            else if (i != 15 && i != 9)
               // myEngine.myWalls.add(new Wall(i, 4, myEngine.myTiles, "/Floor/uf_terrain/wall_crypt_15b.png")); 
                myEngine.myWalls.add(new Wall(i, 4, myEngine.myTiles, "/empty.png")); 
        }
        
        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(2, 3, myEngine.myTiles, "/Enemigos/uf_heroes/golem_ice_1.png", new Equipment ("/item/armour/headgear/helmet_art1.png", 0,35,0, 
                "Helm of Choas, armor + 35", "choasHelm", "Helmet"), 150,100,6));
        myEngine.myMonsters.add(new Monster(6, 4, myEngine.myTiles, "/Enemigos/uf_heroes/beholder_deep_1.png", null, 100,100,8));
        myEngine.myMonsters.add(new Monster(3, 7, myEngine.myTiles, "/Enemigos/uf_heroes/golem_ice_1.png", null, 150,200,8));
        myEngine.myMonsters.add(new Monster(13, 8, myEngine.myTiles, "/Enemigos/uf_heroes/golem_ice_1.png", null, 150,200,8));
        myEngine.myMonsters.add(new Monster(10, 5, myEngine.myTiles, "/Enemigos/uf_heroes/elemental_air_1.png", null, 125,100,6));
        myEngine.myMonsters.add(new Monster(16, 7, myEngine.myTiles, "/Enemigos/uf_heroes/beholder_deep_1.png", null, 100,100,8));
        myEngine.myMonsters.add(new Monster(7, 6, myEngine.myTiles, "/Enemigos/uf_heroes/elemental_air_1.png", null, 125,100,6));
        myEngine.myMonsters.add(new Monster(11, 1, myEngine.myTiles, "/Enemigos/uf_heroes/cultist_1.png", new InventoryItem("/item/food/lemon.png", "😬 Warning:"
                + " Good luck 😬", "L1Key"), 50,100,12));
        

        
                myEngine.myHero.x = 17;
                myEngine.myHero.y = 2;
        myEngine.myHero.loadIntoTile(17, 2, myEngine.myTiles);
        myEngine.myTiles[2][0] = new Tile(2, 0, "/Floor/uf_terrain/floor_set_grey_10d.png"); // STAIR
        myEngine.myDoor = new Door(2, 0, myEngine.myTiles);
        myEngine.localShopKeep = new ShopKeeper(1, 1, myEngine);
        myEngine.currentFloor++;
        
    }
    
    public static void /*bodyShieldHere*/levelSix(GameEngine myEngine) throws IOException {
        
        // default floor tiles
        for (int i = 0; i < myEngine.dungeonColumns; i++) { // columns
            for (int j = 0; j < myEngine.dungeonRows; j++) { // rows
                    myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra.png"); // change
                // myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        
        
        
        
        
        // Other rooms
        
        for (int i = 0; i < 6; i++){ // Top left corner
            for (int j = 0; j < 6; j++){
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra.png");
            }
        }
          
        for (int i = 0; i < 6; i++){ // Bottom left corner; key will be here
            for (int j = 6; j < myEngine.dungeonRows; j++){
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra.png");
            }
        }
        
        for (int i = 14; i < myEngine.dungeonColumns; i++){ // Top right corner
            for (int j = 0; j < 6; j++){ // put a wall on row 6...or is it 5? SHIT
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra.png");
            }
        }
        for (int i = 14; i < myEngine.dungeonColumns; i++){ // Bottom right corner; door will be here
            for (int j = 6; j < myEngine.dungeonRows; j++){
                myEngine.myTiles[i][j] = new Tile(i, j, "/Floor/uf_terrain/floor_extra.png");
            }
        }
        
        // microsoft edge walls
        
        for (int i = 0; i < myEngine.dungeonColumns; i++)
        {
            for(int j=0;j<myEngine.dungeonRows;j++){
                if( j==myEngine.dungeonRows-1 || i==0 || i==myEngine.dungeonColumns-1)
                    myEngine.myWalls.add(new Wall(i, j, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_6.png")); 
            }
            
        }
        
        // top walls
        for (int i = 1; i < myEngine.dungeonColumns - 1; i++)
        {
            myEngine.myWalls.add(new Wall(i, 0, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_14.png")); 
        }
        
        
        
        // Left Side Walls
        for (int i = 1; i < 6; i++)
        {
            if(i != 3)
                myEngine.myWalls.add(new Wall(i, 6, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_14.png")); // Makes a row of wall at Y coordinate 5
        }
        for (int i = 1; i < 6; i++)
        {
            if(i != 3)
                myEngine.myWalls.add(new Wall(i, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_6.png")); // Makes a row of wall at Y coordinate 5
        }
        
        for (int i = 6; i <= myEngine.dungeonRows - 1; i++)
        {
            if(i != 1)
                myEngine.myWalls.add(new Wall(6, i, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_6.png")); // Makes a column of wall at X coordinate 6, starting at row 6
        }
        
        myEngine.myWalls.add(new Wall(6, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_9.png")); // check those corners!
        for (int i = 1; i <= 5; i++)
        {
            if(i != 1)
                myEngine.myWalls.add(new Wall(5, i, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_6.png")); // Makes a column of wall at X coordinate 5 until row 5
        }
        
        // Right Side Walls
        for (int i = 14; i <= myEngine.dungeonColumns - 2; i++)
        {
            if(i != 17)
                myEngine.myWalls.add(new Wall(i, 6, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_14.png")); // Makes a row of wall at Y coordinate 6
        }
        for (int i = 15; i <= myEngine.dungeonColumns - 2; i++)
        {
            if(i != 17)
                myEngine.myWalls.add(new Wall(i, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_6.png")); // Makes a row of wall at Y coordinate 5
        }
        for (int i = 6; i <= myEngine.dungeonRows - 1; i++)
        {
            // if(i != 800)
                myEngine.myWalls.add(new Wall(13, i, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_6.png")); // Makes a column of wall at X coordinate 6, starting at row 14
        }
        myEngine.myWalls.add(new Wall(13, 5, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_9.png")); // check those corners!
        for (int i = 1; i <= 5; i++)
        {
             if(i != 1)
                myEngine.myWalls.add(new Wall(14, i, myEngine.myTiles, "/Floor/uf_terrain/wall_ruins_6.png")); // Makes a column of wall at X coordinate 15 until row 5
        }
        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(1, 8, myEngine.myTiles, "/Enemigos/uf_heroes/ogre_1.png", null, 175,200,4));
        myEngine.myMonsters.add(new Monster(3, 4, myEngine.myTiles, "/Enemigos/uf_heroes/elemental_earth_1.png", new Equipment ("/item/weapon/flail1.png", 20,0,0, "Flail : damage + 20", "Flail1", "Boots"), 150,100,6));
        myEngine.myMonsters.add(new Monster(17, 4, myEngine.myTiles, "/Enemigos/uf_heroes/elemental_earth_1.png", new Equipment ("/item/armour/boots4_green.png", 20,0,0, "Boots of Speed : damage + 20", "BootsofSpeed", "Boots"), 150,200,6));
        myEngine.myMonsters.add(new Monster(11, 4, myEngine.myTiles, "/Enemigos/uf_heroes/elemental_earth_1.png", new Equipment ("/item/weapon/giant_spiked_club.png", 8,0,0, "Brute Club : damage + 8", "BruteClub", "Weapon"), 150,200,6));
        myEngine.myMonsters.add(new Monster(12, 8, myEngine.myTiles, "/Enemigos/uf_heroes/elemental_earth_1.png", new Equipment ("/item/weapon/bullwhip2.png", 12,0,0, "Whip : damage + 12", "Whip", "Weapon"), 150,100,6));
        myEngine.myMonsters.add(new Monster(14, 7, myEngine.myTiles, "/Enemigos/uf_heroes/golem_stone_1.png", null, 175,100,4));
        myEngine.myMonsters.add(new Monster(9, 1, myEngine.myTiles, "/Enemigos/uf_heroes/golem_stone_1.png", null, 175,100,4));
        myEngine.myMonsters.add(new Monster(6, 4, myEngine.myTiles, "/Enemigos/uf_heroes/golem_stone_1.png", null, 175,100,4));
        myEngine.myMonsters.add(new Monster(7, 6, myEngine.myTiles, "/Enemigos/uf_heroes/golem_stone_1.png", null, 175,100,4));
        myEngine.myMonsters.add(new Monster(5, 7, myEngine.myTiles, "/Enemigos/uf_heroes/pixie_b_1.png", new InventoryItem("/item/book/book_of_the_dead.png", "You're too stupid to understand. "
                + "All these doors lead to the same place.", "L1Key"), 25,0,1));
        
        myEngine.myWalls.clear();

                        
                myEngine.myHero.x = 9;
                myEngine.myHero.y = 8;
        myEngine.myHero.loadIntoTile(9, 8, myEngine.myTiles);
        myEngine.myTiles[9][0] = new Tile(9, 0, "/Floor/uf_terrain/floor_set_grey_10d.png"); // DOES THIS MAKE SENSE??
        myEngine.myDoor = new Door(9, 0, myEngine.myTiles); // YES
        myEngine.localShopKeep = new ShopKeeper(18, 8, myEngine);
        myEngine.currentFloor++;
    }
    
    
    
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


public static void makeLevelTwo(GameEngine myEngine) throws IOException {
        
        
        for (int i = 0; i < myEngine.dungeonColumns; i++) {
            for (int j = 0; j < myEngine.dungeonRows; j++) {
                myEngine.myTiles[i][j] = new Tile(i, j, "/dngn/floor/green_bones12.png");
                //myEngine.myTiles[i][j].syncTileWithScreen(); 
            }
        }
        myEngine.myMonsters.clear();
        myEngine.myMonsters.add(new Monster(4, 4, myEngine.myTiles, "/elf_m.png", new Equipment ("/item/armour/headgear/cap_jester.png", 20, 0,0,"Fool's Hat: damage + 20", "FoolHat", "Helmet"), 100));
        myEngine.myMonsters.add(new Monster(4, 3, myEngine.myTiles, "/orange_demon.png", new Equipment ("/item/armour/boots4_green.png", 20, 0,0,"Boots of Speed : damage + 20", "BootsofSpeed", "Boots"), 100));
        myEngine.myMonsters.add(new Monster(5, 5, myEngine.myTiles, "/orange_demon.png", new InventoryItem("/key_gold.png", "The key to the next level.", "L1Key"), 100));
        myEngine.myWalls.clear();
        myEngine.myWalls.add(new Wall(10, 2, myEngine.myTiles, "/dngn/wall/brick_brown0.png"));
        myEngine.myWalls.add(new Wall(10, 3, myEngine.myTiles, "/dngn/wall/brick_brown0.png"));
                        myEngine.myTiles[myEngine.myHero.x][myEngine.myHero.y].myContents[3] = null;
                myEngine.myTiles[myEngine.myHero.x][myEngine.myHero.y].imageName[3] = "/empty.png";
                myEngine.myHero.x = 9;
                myEngine.myHero.y = 9;
        myEngine.myHero.loadIntoTile(9, 9, myEngine.myTiles);
        myEngine.myDoor = new Door(8, 8, myEngine.myTiles);
        myEngine.currentFloor++;
    }
*/