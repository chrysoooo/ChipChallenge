package Main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

//        entity.onIce = false;
//        entity.iceTurn = "NONE";

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;

//                    if(gp.tileM.tile[tileNum1].isIce || gp.tileM.tile[tileNum2].isIce){
//                    entity.onIce = true;
//                        if(gp.tileM.tile[tileNum1].isIce && !gp.tileM.tile[tileNum1].iceTurn.equals("NONE"))
//                            entity.iceTurn = gp.tileM.tile[tileNum1].iceTurn;
//                        if(gp.tileM.tile[tileNum2].isIce && !gp.tileM.tile[tileNum2].iceTurn.equals("NONE"))
//                            entity.iceTurn = gp.tileM.tile[tileNum2].iceTurn;
//                        }
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;

//                    if(gp.tileM.tile[tileNum1].isIce || gp.tileM.tile[tileNum2].isIce){
//                    entity.onIce = true;
//                        if(gp.tileM.tile[tileNum1].isIce && !gp.tileM.tile[tileNum1].iceTurn.equals("NONE"))
//                            entity.iceTurn = gp.tileM.tile[tileNum1].iceTurn;
//                        if(gp.tileM.tile[tileNum2].isIce && !gp.tileM.tile[tileNum2].iceTurn.equals("NONE"))
//                            entity.iceTurn = gp.tileM.tile[tileNum2].iceTurn;
//                        }
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;

//                    if(gp.tileM.tile[tileNum1].isIce || gp.tileM.tile[tileNum2].isIce){
//                    entity.onIce = true;
//                        if(gp.tileM.tile[tileNum1].isIce && !gp.tileM.tile[tileNum1].iceTurn.equals("NONE"))
//                            entity.iceTurn = gp.tileM.tile[tileNum1].iceTurn;
//                        if(gp.tileM.tile[tileNum2].isIce && !gp.tileM.tile[tileNum2].iceTurn.equals("NONE"))
//                            entity.iceTurn = gp.tileM.tile[tileNum2].iceTurn;
//                        }
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;

//                    if(gp.tileM.tile[tileNum1].isIce || gp.tileM.tile[tileNum2].isIce){
//                    entity.onIce = true;
//                        if(gp.tileM.tile[tileNum1].isIce && !gp.tileM.tile[tileNum1].iceTurn.equals("NONE"))
//                            entity.iceTurn = gp.tileM.tile[tileNum1].iceTurn;
//                        if(gp.tileM.tile[tileNum2].isIce && !gp.tileM.tile[tileNum2].iceTurn.equals("NONE"))
//                            entity.iceTurn = gp.tileM.tile[tileNum2].iceTurn;
//                        }
                }
                break;
        }
    }

    public void checkTileWithOffset(Entity e, int xOff, int yOff) {

        int leftX   = e.worldX + e.solidArea.x + xOff;
        int rightX  = e.worldX + e.solidArea.x + e.solidArea.width + xOff;
        int topY    = e.worldY + e.solidArea.y + yOff;
        int bottomY = e.worldY + e.solidArea.y + e.solidArea.height + yOff;

        int leftCol = leftX / gp.tileSize;
        int rightCol = rightX / gp.tileSize;
        int topRow = topY / gp.tileSize;
        int bottomRow = bottomY / gp.tileSize;

        int tile1 = gp.tileM.mapTileNum[topRow][leftCol];
        int tile2 = gp.tileM.mapTileNum[topRow][rightCol];
        int tile3 = gp.tileM.mapTileNum[bottomRow][leftCol];
        int tile4 = gp.tileM.mapTileNum[bottomRow][rightCol];

        // any is solid?
        if (gp.tileM.tile[tile1].collision ||
                gp.tileM.tile[tile2].collision ||
                gp.tileM.tile[tile3].collision ||
                gp.tileM.tile[tile4].collision)
        {
            e.collisionOn = true;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
