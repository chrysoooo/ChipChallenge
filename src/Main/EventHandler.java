package Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 8;
            eventRect[map][col][row].y = 8;
            eventRect[map][col][row].width = 32;
            eventRect[map][col][row].height = 32;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;

                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }


    }

    public void checkEvent(){
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent == true){
            if((hit(0, 3, 4, "any") == true) && gp.player.hasMicrochip == 2) {
                teleport(1, 9, 9);
            }
            if((hit(1,18,15,"any") == true) && gp.player.hasMicrochip == 2){
                System.exit(0);
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection){
        if(map != gp.currentMap) return false;

        Rectangle playerArea = new Rectangle(
                gp.player.worldX + gp.player.solidArea.x,
                gp.player.worldY + gp.player.solidArea.y,
                gp.player.solidArea.width,
                gp.player.solidArea.height
        );

        Rectangle eventArea = new Rectangle(
                col * gp.tileSize + eventRect[map][col][row].x,
                row * gp.tileSize + eventRect[map][col][row].y,
                eventRect[map][col][row].width,
                eventRect[map][col][row].height
        );

        if(playerArea.intersects(eventArea) && !eventRect[map][col][row].eventDone){
            if(gp.player.direction.equals(reqDirection) || reqDirection.equals("any")){
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
                return true;
            }
        }
        return false;
    }

    public void teleport(int map, int col, int row){

        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;

        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
    }
}
