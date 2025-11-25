package Main;

import obj.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Key_Red();
        gp.obj[0].worldX = 13 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new Key_Red();
        gp.obj[1].worldX = 7 * gp.tileSize;
        gp.obj[1].worldY = 4 * gp.tileSize;

        gp.obj[2] = new Door_Red();
        gp.obj[2].worldX = 6 * gp.tileSize;
        gp.obj[2].worldY = 6 * gp.tileSize;

        gp.obj[3] = new Door_Red();
        gp.obj[3].worldX = 5 * gp.tileSize;
        gp.obj[3].worldY = 4 * gp.tileSize;

        gp.obj[4] = new Microchip();
        gp.obj[4].worldX = 14 * gp.tileSize;
        gp.obj[4].worldY = 7 * gp.tileSize;

        gp.obj[5] = new Flippers();
        gp.obj[5].worldX = 16 * gp.tileSize;
        gp.obj[5].worldY = 10 * gp.tileSize;

        gp.obj[6] = new Door_Blue();
        gp.obj[6].worldX = 14 * gp.tileSize;
        gp.obj[6].worldY = 9 * gp.tileSize;

        gp.obj[7] = new Key_Blue();
        gp.obj[7].worldX = 11 * gp.tileSize;
        gp.obj[7].worldY = 11 * gp.tileSize;
    }
}
