package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Flippers extends SuperObject{
    public Flippers() {
        name = "Flippers";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/flippers.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
