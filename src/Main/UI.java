package Main;

import obj.Heart;
import obj.Microchip;
import obj.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_20;
    BufferedImage microchipImage;
    BufferedImage heart_full, heart_half, heart_blank;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_20 = new Font("arial", Font.BOLD, 20);
        Microchip microchip = new Microchip();
        microchipImage = microchip.image;

        SuperObject heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_20);
        g2.setColor(Color.red);
        g2.drawImage(microchipImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+ gp.player.hasMicrochip, 50, 30);

        drawPlayerLife(g2);
    }

    public void drawPlayerLife(Graphics2D g2) {
        int x = gp.tileSize * 17;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize * 17;
        y = gp.tileSize/2;
        i = 0;

        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
}
