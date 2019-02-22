
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sasha
 */
public class Tile {
    String myError;
    int x;
    int y;
    String[] imageName;
    Image[] image;
    GameEngine.MapObject[] myContents;
    boolean hasChanged;
    
    public Tile(int myX, int myY) throws IOException
    {
        
      imageName = new String[4];
      image = new Image[4];
      myContents = new GameEngine.MapObject[4];
      
      imageName[0] = "/dngn/floor/crystal_floor0.png";
      imageName[1] = "/empty.png";
      
      image[0] = generateImage(imageName[0]);
      image[1] = generateImage(imageName[0]);
      
      x = myX;
      y = myY;
    }
    
        public Image generateImage(String myImageName) throws IOException {
            Image myResult = null;
      
            try
            {
			myResult = ImageIO.read( getClass().getResourceAsStream(myImageName) ); //this is the new way
            }
		catch(Exception e) {
			e.printStackTrace();
                        myError = e.getMessage();
		}
      //hasChanged = true;
      return myResult;
        }
}
