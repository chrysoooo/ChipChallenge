package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Water extends SuperObject{

    public Water() {
        name = "Water Tile";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/water.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
