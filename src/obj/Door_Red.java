package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door_Red extends SuperObject{

    public Door_Red() {
        name = "Red Door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_red.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
