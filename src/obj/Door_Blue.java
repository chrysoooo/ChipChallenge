package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door_Blue extends SuperObject{

    public Door_Blue() {
        name = "Blue Door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_blue.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
