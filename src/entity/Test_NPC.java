package entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Test_NPC extends Entity {
    public Test_NPC(GamePanel gp) {
        super(gp);

        name = "Test";
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

    public void setAction() {

        actionLockCounter++;
        if(actionLockCounter == 120){

            Random random = new Random();
            int i =  random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            else if(i > 25 && i <= 50){
                direction = "down";
            }
            else if(i > 50 && i <= 75){
                direction = "left";
            }
            else if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}