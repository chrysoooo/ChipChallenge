package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key_Blue extends SuperObject{
    public Key_Blue() {
        name = "Blue Key";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key_blue.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
