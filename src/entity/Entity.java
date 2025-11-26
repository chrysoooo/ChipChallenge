package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

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

    public Rectangle solidArea;
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
}
