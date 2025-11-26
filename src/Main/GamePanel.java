package Main;

import java.awt.*;

import entity.Entity;
import entity.Player;
import obj.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 32; // 32x32 tile.
    final int scale = 1;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 20;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public final int maxMap = 10;
    public final int currentMap = 1;

    //FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread; // starts the game and keeps it running

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[][] = new SuperObject[maxMap][50]; // can only display up to -- objects at the same time
    public Entity npc[][] = new Entity[maxMap][5];
    public Entity monster[][] = new Entity[maxMap][5];

    public int mapTileNum[][];

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC(); // NOT NEEDED ONLY FOR LOGIC
        aSetter.setMonster();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            // 1: update information such as character position
            update();

            // 2: draw the screen with the updated information
            repaint(); //calls paintComponent method


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        player.update();

        for (int i = 0; i < npc[1].length; i++) {
            if (npc[currentMap][i] != null) {
                npc[currentMap][i].update();
            }
        }
        for (int i = 0; i < monster[1].length; i++) {
            if (monster[currentMap][i] != null) {
                monster[currentMap][i].update();
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        for(int i = 0; i < obj[1].length; i++){
            if(obj[currentMap][i] != null){
                obj[currentMap][i].draw(g2, this);
            }
        }

        for(int i = 0; i < npc[1].length; i++){
            if(npc[currentMap][i] != null){
                npc[currentMap][i].draw(g2);
            }
        }

        for(int i = 0; i < monster[1].length; i++){
            if(monster[currentMap][i] != null){
                monster[currentMap][i].draw(g2);
            }
        }

        player.draw(g2);

        ui.draw(g2);

        g2.dispose(); // disposes graphics context to save memory
    }
}
