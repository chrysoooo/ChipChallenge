package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Microchip extends SuperObject{
    public Microchip() {
        name = "Microchip";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/microchip.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
