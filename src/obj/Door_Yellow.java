package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door_Yellow extends SuperObject{

    public Door_Yellow() {
        name = "Yellow Door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_yellow.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
