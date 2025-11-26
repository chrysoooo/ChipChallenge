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
    int hasKey_Yellow = 0;
    int hasFlippers = 0;
    int hasFireBoots = 0;
    int hasMicrochip = 0;

//    public boolean sliding = false;
//    public int slideSpeed = 4;

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
//        worldX = gp.tileSize * 9;
//        worldY = gp.tileSize * 7;
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

        int tileCol = (worldX + solidArea.x + solidArea.width / 2) / gp.tileSize;
        int tileRow = (worldY + solidArea.y + solidArea.height / 2) / gp.tileSize;

        // Bounds check (important for safety)
        if (tileCol < 0 || tileCol >= gp.maxWorldCol || tileRow < 0 || tileRow >= gp.maxWorldRow) {
            sliding = false;
            return;
        }

        int tileNum = gp.tileM.mapTileNum[tileCol][tileRow];
        // Assumes gp.tileM.tile[tileNum] gives the correct Tile object
        tile.Tile currentTile = gp.tileM.tile[tileNum];

        // 2. Check if the current tile is NOT ice
        if (!currentTile.isIce) {
            sliding = false;
            return;
        }

        // 3. We are on ice, so set sliding state
        sliding = true;

        // 4. Handle directional change for special ice tiles (if applicable)
        // NOTE: This logic assumes your Tile class has a property called 'iceTurn'
        // that is a String like "BOTTOM_RIGHT", etc., as you had in your commented code.
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

        // 5. Check for collision in the current sliding direction
        collisionOn = false;
        gp.cChecker.checkTile(this); // Check collision in the current tile/direction

        if(!collisionOn) {
            // No collision, so keep sliding
            moveInCurrentDirection();
        }
        else {
            // Collision detected, stop sliding
            sliding = false;
        }
    }

    public void update(){
        boolean keyIsPressed = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
        isMoving = keyIsPressed;

        if (keyIsPressed) {

            String newDirection = direction; // Keep current direction if multiple keys are pressed
            if(keyH.upPressed)      {
                newDirection = "up"; lastDirection = UP;
            } else if(keyH.downPressed)  {
                newDirection = "down"; lastDirection = DOWN;
            } else if(keyH.leftPressed)  {
                newDirection = "left"; lastDirection = LEFT;
            } else if(keyH.rightPressed) {
                newDirection = "right"; lastDirection = RIGHT;
            }

            direction = newDirection;

           // CHECK TILE COLLISION
           collisionOn = false;
           gp.cChecker.checkTile(this);

           // CHECK OBJECT COLLISION
           int objIndex = gp.cChecker.checkObject(this, true);
           pickUpObject(objIndex);

           // IF COLLISION FALSE, PLAYER CAN MOVE
           if(!collisionOn){
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
           } else {
               sliding = false;
           }

            int tileCol = (worldX + solidArea.x + solidArea.width / 2) / gp.tileSize;
            int tileRow = (worldY + solidArea.y + solidArea.height / 2) / gp.tileSize;
            if (tileCol >= 0 && tileCol < gp.maxWorldCol && tileRow >= 0 && tileRow < gp.maxWorldRow) {
                int tileNum = gp.tileM.mapTileNum[tileCol][tileRow];
                tile.Tile currentTile = gp.tileM.tile[tileNum];
                if (currentTile.isIce) {
                    sliding = true;
                } else {
                    sliding = false;
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

      //checkIceTile();
//
      //  int xspd = (keyH.rightPressed ? 1 : 0) - (keyH.leftPressed ? 1 : 0);
      //  int yspd = (keyH.downPressed ? 1 : 0) - (keyH.upPressed ? 1 : 0);
//
      //  xspd *= speed;
      //  yspd *= speed;
//
      //  updateFaceDirection(xspd, yspd);
      //  updateSpriteAnimation();
//
      //  collisionOn = false;
      //  gp.cChecker.checkTileWithOffset(this, xspd, 0);
      //  if (collisionOn) xspd = 0;
//
      //  collisionOn = false;
      //  gp.cChecker.checkTileWithOffset(this, 0, yspd);
      //  if (collisionOn) yspd = 0;
//
      //  worldX += xspd;
      //  worldY += yspd;
//
      //  if (!isMoving && sliding) checkIceTile();
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
                case "Yellow Key":
                    hasKey_Yellow++;
                    gp.obj[i] = null;
                    break;
                case "Yellow Door":
                    if(hasKey_Yellow > 0){
                        gp.obj[i] = null;
                        hasKey_Yellow--;
                    }
                    break;
                case "Flippers":
                    hasFlippers++;
                    gp.obj[i] = null;
                    for (int j = 0; j < gp.obj.length; j++) {
                        // Ensure the object exists and is a Water Tile
                        if (gp.obj[j] != null && gp.obj[j].name.equals("Water Tile")) {
                            gp.obj[j].collision = false;
                        }
                    }
                    break;
                case "Fire Boots":
                    hasFireBoots++;
                    gp.obj[i] = null;
                    for (int j = 0; j < gp.obj.length; j++) {
                        // Ensure the object exists and is a Fire Tile
                        if (gp.obj[j] != null && gp.obj[j].name.equals("Fire Tile")) {
                            gp.obj[j].collision = false;
                        }
                    }
                case "Microchip":
                    hasMicrochip++;
                    gp.obj[i] = null;
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
