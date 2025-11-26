package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    // Inventory/Item Counters
    int hasKey_Red = 0;
    int hasKey_Blue = 0;
    int hasKey_Yellow = 0;
    int hasFlippers = 0;
    int hasFireBoots = 0;
    public int hasMicrochip = 0;

    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 -  (gp.tileSize/2);

        // COLLISION BOX OF PLAYER
        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 14;
        solidAreaDefaultX = solidArea.x; // records default values
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 14;
        solidArea.height = 16;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        // DO NOT REMOVE THIS IS FOR LEVEL 1 TESTING
         worldX = gp.tileSize * 15;
         worldY = gp.tileSize * 11;

        // DO NOT REMOVE THIS IS FOR LEVEL 2
//        worldX = gp.tileSize * 9;
//        worldY = gp.tileSize * 9;
        speed = 4;
        direction = "down";
        // Reset force speeds
        speedX = 0;
        speedY = 0;
        isOnForceTile = false;
        currentForceTileID = -1;
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

    // Used for sliding motion only
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

        if (tileCol < 0 || tileCol >= gp.maxWorldCol || tileRow < 0 || tileRow >= gp.maxWorldRow) {
            sliding = false;
            return;
        }

        int tileNum = gp.tileM.mapTileNum[gp.currentMap][tileCol][tileRow];
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
        boolean keyIsPressed = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
        isMoving = keyIsPressed;

        // Determine the player's current tile coordinates
        int playerCol = (worldX + solidArea.x + solidArea.width / 2) / gp.tileSize;
        int playerRow = (worldY + solidArea.y + solidArea.height / 2) / gp.tileSize;

        // Initialize flags for the current frame
        isOnForceTile = false;

        // Bounds check before accessing the map array
        if (playerCol >= 0 && playerCol < gp.maxWorldCol && playerRow >= 0 && playerRow < gp.maxWorldRow) {
            int tileId = gp.tileM.mapTileNum[gp.currentMap][playerCol][playerRow];
            tile.Tile currentTile = gp.tileM.tile[tileId];

            // 1. CHECK FOR FORCE TILE INTERACTION (High Priority)
            if (currentTile.isForce) {
                isOnForceTile = true;
                currentForceTileID = tileId;
                sliding = false; // Force movement overrides sliding
            }
            // 2. CHECK FOR ICE TILE INTERACTION (Lower Priority than Force)
            else if (currentTile.isIce) {
                // If the player isn't already sliding, they can start sliding
                if (!sliding && keyIsPressed) {
                    sliding = true;
                }
            }
            // 3. REGULAR TILE (Reset both special movements)
            else {
                sliding = false;
                speedX = 0;
                speedY = 0;
            }
        }

        // --- MOVEMENT LOGIC ---

        if (isOnForceTile) {
            int pushSpeed = 4;

            // Set speed based on Force Tile ID
            switch(currentForceTileID) {
                case 3: // ffup
                    speedX = 0; speedY = -pushSpeed;
                    direction = "up";
                    break;
                case 4: // ffdown
                    speedX = 0; speedY = pushSpeed;
                    direction = "down";
                    break;
                case 5: // ffleft
                    speedX = -pushSpeed; speedY = 0;
                    direction = "left";
                    break;
                case 6: // ffright
                    speedX = pushSpeed; speedY = 0;
                    direction = "right";
                    break;
                default:
                    speedX = 0; speedY = 0;
            }
            // Move player based on force
            worldX += speedX;
            worldY += speedY;

            // Collision check is often skipped or handled differently for force tiles,
            // but if the force pushes you into a wall, a collision check would happen here.
            // collisionOn = false;
            // gp.cChecker.checkTile(this);
            // if (collisionOn) { worldX -= speedX; worldY -= speedY; }

        } else if (sliding) {
            // Apply ice slide movement
            checkIceTile();

        } else if (keyIsPressed) {
            // Apply normal keyboard movement

            // Set Direction
            if(keyH.upPressed)      {
                direction = "up"; lastDirection = UP;
            } else if(keyH.downPressed)  {
                direction = "down"; lastDirection = DOWN;
            } else if(keyH.leftPressed)  {
                direction = "left"; lastDirection = LEFT;
            } else if(keyH.rightPressed) {
                direction = "right"; lastDirection = RIGHT;
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // IF COLLISION FALSE, PLAYER CAN MOVE
            if(!collisionOn){
                switch (direction) {
                    case "up":    worldY -= speed; break;
                    case "down":  worldY += speed; break;
                    case "left":  worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
        } else {
            standCounter++;

            if(standCounter == 10){
                spriteNum = 1;
                standCounter = 0;
            }
        }

        // --- ANIMATION LOGIC ---
        // Animation runs for all movement types (Force, Ice, or Normal)
        if (keyIsPressed || isOnForceTile || sliding) {
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
    }


    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[gp.currentMap][i].name;

            switch(objectName){
                case "Red Key":
                    hasKey_Red++;
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "Red Door":
                    if(hasKey_Red > 0){
                        gp.obj[gp.currentMap][i] = null;
                        hasKey_Red--;
                    }
                    break;
                case "Blue Key":
                    hasKey_Blue++;
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "Blue Door":
                    if(hasKey_Blue > 0){
                        gp.obj[gp.currentMap][i] = null;
                        hasKey_Blue--;
                    }
                    break;
                case "Yellow Key":
                    hasKey_Yellow++;
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "Yellow Door":
                    if(hasKey_Yellow > 0){
                        gp.obj[gp.currentMap][i] = null;
                        hasKey_Yellow--;
                    }
                    break;
                case "Flippers":
                    hasFlippers++;
                    gp.obj[gp.currentMap][i] = null;
                    for (int j = 0; j < gp.obj.length; j++) {
                        // Ensure the object exists and is a Water Tile
                        if (gp.obj[gp.currentMap][j] != null && "Water Tile".equals(gp.obj[gp.currentMap][j].name)) {
                            gp.obj[gp.currentMap][j].collision = false;
                        }
                    }
                    break;
                case "Fire Boots":
                    hasFireBoots++;
                    gp.obj[gp.currentMap][i] = null;
                    for (int j = 0; j < gp.obj.length; j++) {
                        // Ensure the object exists and is a Fire Tile
                        if (gp.obj[gp.currentMap][j] != null && "Fire Tile".equals(gp.obj[gp.currentMap][j].name)) {
                            gp.obj[gp.currentMap][j].collision = false;
                        }
                    }
                    break;
                case "Microchip":
                    hasMicrochip++;
                    gp.obj[gp.currentMap][i] = null;
                    break;
            }
        }
    }

    public void interactNPC(int i){
        if(i != 999){

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

        // DEBUG: DISPLAY PLAYER COLLISION BOX
        g2.setColor(Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}