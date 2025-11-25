package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key_Red extends SuperObject{
    public Key_Red() {
        name = "Red Key";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key_red.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
