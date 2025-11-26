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

        gp.obj[8] = new FireBoots();
        gp.obj[8].worldX = 6 * gp.tileSize;
        gp.obj[8].worldY = 14 * gp.tileSize;

        gp.obj[9] = new Microchip();
        gp.obj[9].worldX = 7 * gp.tileSize;
        gp.obj[9].worldY = 14 * gp.tileSize;

        gp.obj[10] = new Water();
        gp.obj[10].worldX = 7 * gp.tileSize;
        gp.obj[10].worldY = 13 * gp.tileSize;

        gp.obj[11] = new Water();
        gp.obj[11].worldX = 8 * gp.tileSize;
        gp.obj[11].worldY = 13 * gp.tileSize;

        gp.obj[12] = new Water();
        gp.obj[12].worldX = 8 * gp.tileSize;
        gp.obj[12].worldY = 14 * gp.tileSize;

        gp.obj[13] = new Water();
        gp.obj[13].worldX = 8 * gp.tileSize;
        gp.obj[13].worldY = 15 * gp.tileSize;

        gp.obj[14] = new Water();
        gp.obj[14].worldX = 7 * gp.tileSize;
        gp.obj[14].worldY = 15 * gp.tileSize;

        gp.obj[15] = new Water();
        gp.obj[15].worldX = 6 * gp.tileSize;
        gp.obj[15].worldY = 15 * gp.tileSize;

        gp.obj[16] = new Water();
        gp.obj[16].worldX = 5 * gp.tileSize;
        gp.obj[16].worldY = 15 * gp.tileSize;

        gp.obj[17] = new Water();
        gp.obj[17].worldX = 5 * gp.tileSize;
        gp.obj[17].worldY = 14 * gp.tileSize;

        gp.obj[18] = new Water();
        gp.obj[18].worldX = 5 * gp.tileSize;
        gp.obj[18].worldY = 13 * gp.tileSize;

        gp.obj[19] = new Water();
        gp.obj[19].worldX = 6 * gp.tileSize;
        gp.obj[19].worldY = 13 * gp.tileSize;

        gp.obj[20] = new Fire();
        gp.obj[20].worldX = 6 * gp.tileSize;
        gp.obj[20].worldY = 8 * gp.tileSize;

        gp.obj[21] = new Fire();
        gp.obj[21].worldX = 5 * gp.tileSize;
        gp.obj[21].worldY = 8 * gp.tileSize;

        gp.obj[22] = new Fire();
        gp.obj[22].worldX = 7 * gp.tileSize;
        gp.obj[22].worldY = 8 * gp.tileSize;

        gp.obj[23] = new Fire();
        gp.obj[23].worldX = 8 * gp.tileSize;
        gp.obj[23].worldY = 8 * gp.tileSize;

        gp.obj[24] = new Fire();
        gp.obj[24].worldX = 6 * gp.tileSize;
        gp.obj[24].worldY = 9 * gp.tileSize;

        gp.obj[25] = new Fire();
        gp.obj[25].worldX = 5 * gp.tileSize;
        gp.obj[25].worldY = 9 * gp.tileSize;

        gp.obj[26] = new Fire();
        gp.obj[26].worldX = 7 * gp.tileSize;
        gp.obj[26].worldY = 9 * gp.tileSize;

        gp.obj[27] = new Fire();
        gp.obj[27].worldX = 8 * gp.tileSize;
        gp.obj[27].worldY = 9 * gp.tileSize;

        gp.obj[28] = new Fire();
        gp.obj[28].worldX = 9 * gp.tileSize;
        gp.obj[28].worldY = 11 * gp.tileSize;

        gp.obj[29] = new Fire();
        gp.obj[29].worldX = 13 * gp.tileSize;
        gp.obj[29].worldY = 11 * gp.tileSize;
    }

    public void setNPC(){
        gp.npc[0] = new Test_NPC(gp);
        gp.npc[0].worldX = 7 * gp.tileSize;
        gp.npc[0].worldY = 13 * gp.tileSize;
    }

    public void setMonster(){
        gp.monster[0] = new Spider(gp);
        gp.monster[0].worldX = 24 * gp.tileSize;
        gp.monster[0].worldY = 13 * gp.tileSize;
    }
}
