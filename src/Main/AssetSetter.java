package Main;

import Monster.Spider;
import entity.Test_NPC;
import obj.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][0] = new Key_Red();
        gp.obj[mapNum][0].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][1] = new Key_Red();
        gp.obj[mapNum][1].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 4 * gp.tileSize;

        gp.obj[mapNum][2] = new Door_Red();
        gp.obj[mapNum][2].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 6 * gp.tileSize;

        gp.obj[mapNum][3] = new Door_Red();
        gp.obj[mapNum][3].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 4 * gp.tileSize;

        gp.obj[mapNum][4] = new Microchip();
        gp.obj[mapNum][4].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][5] = new Flippers();
        gp.obj[mapNum][5].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 10 * gp.tileSize;

        gp.obj[mapNum][6] = new Door_Blue();
        gp.obj[mapNum][6].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 9 * gp.tileSize;

        gp.obj[mapNum][7] = new Key_Blue();
        gp.obj[mapNum][7].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 11 * gp.tileSize;

        gp.obj[mapNum][8] = new FireBoots();
        gp.obj[mapNum][8].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][8].worldY = 14 * gp.tileSize;

        gp.obj[mapNum][9] = new Microchip();
        gp.obj[mapNum][9].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][9].worldY = 14 * gp.tileSize;

        gp.obj[mapNum][10] = new Water();
        gp.obj[mapNum][10].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][10].worldY = 13 * gp.tileSize;

        gp.obj[mapNum][11] = new Water();
        gp.obj[mapNum][11].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][11].worldY = 13 * gp.tileSize;

        gp.obj[mapNum][12] = new Water();
        gp.obj[mapNum][12].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][12].worldY = 14 * gp.tileSize;

        gp.obj[mapNum][13] = new Water();
        gp.obj[mapNum][13].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][13].worldY = 15 * gp.tileSize;

        gp.obj[mapNum][14] = new Water();
        gp.obj[mapNum][14].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][14].worldY = 15 * gp.tileSize;

        gp.obj[mapNum][15] = new Water();
        gp.obj[mapNum][15].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][15].worldY = 15 * gp.tileSize;

        gp.obj[mapNum][16] = new Water();
        gp.obj[mapNum][16].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][16].worldY = 15 * gp.tileSize;

        gp.obj[mapNum][17] = new Water();
        gp.obj[mapNum][17].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][17].worldY = 14 * gp.tileSize;

        gp.obj[mapNum][18] = new Water();
        gp.obj[mapNum][18].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][18].worldY = 13 * gp.tileSize;

        gp.obj[mapNum][19] = new Water();
        gp.obj[mapNum][19].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][19].worldY = 13 * gp.tileSize;

        gp.obj[mapNum][20] = new Fire();
        gp.obj[mapNum][20].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][20].worldY = 8 * gp.tileSize;

        gp.obj[mapNum][21] = new Fire();
        gp.obj[mapNum][21].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][21].worldY = 8 * gp.tileSize;

        gp.obj[mapNum][22] = new Fire();
        gp.obj[mapNum][22].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][22].worldY = 8 * gp.tileSize;

        gp.obj[mapNum][23] = new Fire();
        gp.obj[mapNum][23].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][23].worldY = 8 * gp.tileSize;

        gp.obj[mapNum][24] = new Fire();
        gp.obj[mapNum][24].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][24].worldY = 9 * gp.tileSize;

        gp.obj[mapNum][25] = new Fire();
        gp.obj[mapNum][25].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][25].worldY = 9 * gp.tileSize;

        gp.obj[mapNum][26] = new Fire();
        gp.obj[mapNum][26].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][26].worldY = 9 * gp.tileSize;

        gp.obj[mapNum][27] = new Fire();
        gp.obj[mapNum][27].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][27].worldY = 9 * gp.tileSize;

        gp.obj[mapNum][28] = new Fire();
        gp.obj[mapNum][28].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][28].worldY = 11 * gp.tileSize;

        gp.obj[mapNum][29] = new Fire();
        gp.obj[mapNum][29].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][29].worldY = 11 * gp.tileSize;

//        mapNum = 1;
//        gp.obj[mapNum][29] = new Fire();
//        gp.obj[mapNum][29].worldX = 13 * gp.tileSize;
//        gp.obj[mapNum][29].worldY = 11 * gp.tileSize;
//
    }

    public void setNPC(){

        int mapNum = 0;

        gp.npc[mapNum][0] = new Test_NPC(gp);
        gp.npc[mapNum][0].worldX = 7 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 13 * gp.tileSize;
    }

    public void setMonster(){

        int mapNum = 1;

        gp.monster[mapNum][0] = new Spider(gp);
        gp.monster[mapNum][0].worldX = 24 * gp.tileSize;
        gp.monster[mapNum][0].worldY = 13 * gp.tileSize;
    }
}
