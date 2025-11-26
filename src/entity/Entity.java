package entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public String name;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public Rectangle solidArea =  new Rectangle(10,14,25,20);
    public int solidAreaDefaultX,  solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    public boolean sliding = false;
    public int slideSpeed = 4;
    public int lastDirection = DOWN;
    public boolean isMoving = false;

    public int speedX = 0;              // Horizontal velocity component (used by ForceTile)
    public int speedY = 0;              // Vertical velocity component (used by ForceTile)
    public boolean isOnForceTile = false; // Flag indicating if the entity is currently standing on a ForceTile
    public int currentForceTileID = -1;

    public int maxLife;
    public int life;

    public int type; // 0 - player, 1 - npc, 2 - monster

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction(){ }

    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        // Move only if no collision
        if (!collisionOn) {
            switch(direction) {
                case "up":    worldY -= speed; break;
                case "down":  worldY += speed; break;
                case "left":  worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        // Prevent going out of world bounds
        if (worldX < 0) worldX = 0;
        if (worldY < 0) worldY = 0;
        if (worldX > gp.worldWidth - gp.tileSize) worldX = gp.worldWidth - gp.tileSize;
        if (worldY > gp.worldHeight - gp.tileSize) worldY = gp.worldHeight - gp.tileSize;

        // Handle contact with player
        if(this.type == 2 && contactPlayer){
            if(!gp.player.invincible){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        // Sprite animation
        spriteCounter++;
        if(spriteCounter > 12){
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }



    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

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
            // DEBUG: DISPLAY MONSTER COLLISION BOX
            g2.setColor(Color.RED);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
