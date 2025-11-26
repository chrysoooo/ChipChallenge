package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class FireBoots extends SuperObject{

    public FireBoots() {
        name = "Fire Boots";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/fireboots.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
