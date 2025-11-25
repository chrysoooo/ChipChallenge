package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Fire extends SuperObject{

    public Fire() {
        name = "Fire Tile";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/fire1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
