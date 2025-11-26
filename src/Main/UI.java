package Main;

import obj.Microchip;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_20;
    BufferedImage microchipImage;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_20 = new Font("arial", Font.BOLD, 20);
        Microchip microchip = new Microchip();
        microchipImage = microchip.image;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_20);
        g2.setColor(Color.red);
        g2.drawImage(microchipImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+ gp.player.hasMicrochip, 50, 30);
    }
}
