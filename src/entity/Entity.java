package entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public boolean onIce = false;
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public int face = DOWN;
//    public String iceTurn = "NONE";

    public Rectangle solidArea =  new Rectangle(0,0,32,32);
    public int solidAreaDefaultX,  solidAreaDefaultY;
    public boolean collisionOn = false;

    public boolean sliding = false;
    public int slideSpeed = 4;
    public int lastDirection = DOWN;
    public boolean isMoving = false;

    public int speedX = 0;              // Horizontal velocity component (used by ForceTile)
    public int speedY = 0;              // Vertical velocity component (used by ForceTile)
    public boolean isOnForceTile = false; // Flag indicating if the entity is currently standing on a ForceTile
    public int currentForceTileID = -1;

    public Entity(GamePanel gp) {
        this.gp = gp;
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
        }
    }
}
