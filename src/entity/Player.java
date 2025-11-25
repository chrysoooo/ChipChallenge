package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey_Red = 0;
    int hasKey_Blue = 0;

    public boolean sliding = false;
    public int slideSpeed = 4;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 -  (gp.tileSize/2);

        // COLLISION BOX OF PLAYER
        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 9;
        solidAreaDefaultX = solidArea.x; // records default values
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 10;
        solidArea.height = 17;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 15;
        worldY = gp.tileSize * 11;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/chip-up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/chip-up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/chip-down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/chip-down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/chip-left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/chip-left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/chip-right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/chip-right_2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void moveInCurrentDirection() {
    switch(direction) {
        case "up":    worldY -= slideSpeed; break;
        case "down":  worldY += slideSpeed; break;
        case "left":  worldX -= slideSpeed; break;
        case "right": worldX += slideSpeed; break;
        }
    }

    public void checkIceTile() {

    int tileCol = (worldX + solidArea.x) / gp.tileSize;
    int tileRow = (worldY + solidArea.y) / gp.tileSize;

    int tileNum = gp.tileM.mapTileNum[tileCol][tileRow];
    tile.Tile currentTile = gp.tileM.tile[tileNum];

    if (!currentTile.isIce) {
        sliding = false;   
        return;
    }

    sliding = true;

    switch(currentTile.iceTurn) {

        case "BOTTOM_RIGHT":
            if(direction.equals("up"))       direction = "right";
            else if(direction.equals("left")) direction = "down";
            break;

        case "BOTTOM_LEFT":
            if(direction.equals("up"))        direction = "left";
            else if(direction.equals("right")) direction = "down";
            break;

        case "UPPER_RIGHT":
            if(direction.equals("down"))      direction = "right";
            else if(direction.equals("left")) direction = "up";
            break;

        case "UPPER_LEFT":
            if(direction.equals("down"))     direction = "left";
            else if(direction.equals("right")) direction = "up";
            break;
    }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        if(!collisionOn) {
            moveInCurrentDirection();
        }
        else {
            sliding = false;  
        }
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true ||
            keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else if(keyH.rightPressed){
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                switch (direction) {
                    case "up":
                        worldY -= speed; // y value increases as they go down
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed; // x values increases to the right
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            if (sliding) {
                checkIceTile();
            }
        }

        checkIceTile();
    }

    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Red Key":
                    hasKey_Red++;
                    gp.obj[i] = null;
                    break;
                case "Red Door":
                    if(hasKey_Red > 0){
                        gp.obj[i] = null;
                        hasKey_Red--;
                    }
                    break;
                case "Blue Key":
                    hasKey_Blue++;
                    gp.obj[i] = null;
                    break;
                case "Blue Door":
                    if(hasKey_Blue > 0){
                        gp.obj[i] = null;
                        hasKey_Blue--;
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
