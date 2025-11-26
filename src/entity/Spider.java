package entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Spider extends Entity {
    public Spider(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 2;

        getSpiderImage();
    }

    public void getSpiderImage(){

        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/spider_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/spider_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/spider_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/spider_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/spider_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/spider_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/spider_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/spider_right2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}