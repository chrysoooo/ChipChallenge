package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public int id;

    public BufferedImage image;
    public boolean collision = false;

    public boolean isIce = false;
    public String iceTurn = "NONE";

    public boolean isForce = false;
    public String forceDirection = "NONE";

    public Tile() {
    }

    public Tile(int id, boolean collision) {
        this.id = id;               // Assigns the ID
        this.collision = collision; // Assigns the collision status
    }
}
