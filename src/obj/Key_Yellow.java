package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key_Yellow extends SuperObject{
    public Key_Yellow() {
        name = "Yellow Key";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key_yellow.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
