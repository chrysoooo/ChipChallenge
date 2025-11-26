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

        if (entity.direction == null) {
            // Log a warning or simply return, as a null direction means
            // the entity isn't moving and collision check based on direction is unnecessary.
            return;
        }

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                if (entityTopRow >= 0) {
                    tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];

                    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                        entity.collisionOn = true;
                    }
                } else {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                if (entityBottomRow < gp.maxWorldRow) { // gp.maxWorldRow is 20, so valid is 0-19

                    // Use the original entityLeftCol and entityRightCol (which should be constrained
                    // from the start, but we'll assume they are okay for now and focus on the row)

                    // We need to ensure the column indices are also safe here, though the error
                    // points to the row calculation being the primary mover in this direction.
                    if (entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol &&
                            entityRightCol >= 0 && entityRightCol < gp.maxWorldCol) {

                        tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                        tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];

                        if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                            entity.collisionOn = true;
                        }
                    } else {
                        // If column indices are somehow out of bounds even when the row is okay
                        entity.collisionOn = true;
                    }

                } else {
                    // If futureBottomRow is >= maxWorldRow (i.e., off the bottom),
                    // force collision to stop movement
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                if (entityLeftCol >= 0) {
                    tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];

                    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                        entity.collisionOn = true;
                    }
                } else {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                if (entityRightCol < gp.maxWorldCol) {
                    tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];

                    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                        entity.collisionOn = true;
                    }
                } else {
                    // If futureRightCol is >= maxWorldCol (i.e., off the right of the map), treat as collision
                    entity.collisionOn = true;
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

        int tile1 = gp.tileM.mapTileNum[gp.currentMap][leftCol][topRow];
        int tile2 = gp.tileM.mapTileNum[gp.currentMap][rightCol][topRow];
        int tile3 = gp.tileM.mapTileNum[gp.currentMap][leftCol][bottomRow];
        int tile4 = gp.tileM.mapTileNum[gp.currentMap][rightCol][bottomRow];

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

        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] != null){
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get object's solid area position
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[][] target){
        int index = 999;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get object's solid area position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)){
                    if(target[gp.currentMap][i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;

        // get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // get object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if(entity.solidArea.intersects(gp.player.solidArea)){
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
