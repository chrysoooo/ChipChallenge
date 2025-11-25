package Main;

import obj.Key_Red;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Key_Red();
        gp.obj[0].worldX = 14 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new Key_Red();
        gp.obj[1].worldX = 7 * gp.tileSize;
        gp.obj[1].worldY = 4 * gp.tileSize;
    }
}
