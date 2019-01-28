package screen;

import base.BlockGenerator;
import base.GamePanel;
import sprites.LoadMedia;
import sprites.Sprite;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * @author Jose Luis Luengo Ramos
 */
public class GameScreen implements IScreen, KeyEventDispatcher {
    private static final Color COLOR_SCORE = Color.WHITE;


    GamePanel gamePanel;
    LoadMedia loadMedia;
    BufferedImage bufferedImage;
    BufferedImage[][] blockImageStage;
    BlockGenerator blockList;

    Image imageScaling;

    Sprite bar;
    Sprite ball;
    Sprite[][] blocks;

    static Font mainFont;
    private int cont;
    private boolean[] keysPressed;
    private int totalblock;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        keysPressed = new boolean[2];
        this.loadMedia = gamePanel.getLoadMedia();
    }


    public void initWindow() {
        this.totalblock=0;
        this.blockList = new BlockGenerator(this.loadMedia.getBlockSubBuffer());
        this.blocks = new Sprite[12][6];
        this.blockImageStage = this.blockList.getStage1();
        cont = 0;
        ball = new Sprite(20, 20, 300, gamePanel.getHeight() - 100, 0, 0, loadMedia.getBallSubBuffer());

        bufferedImage = loadMedia.getBackgroundBuffer()[0];

        for (int i = 0; i < this.blockImageStage.length; i++) {
            for (int j = 0; j < this.blockImageStage[i].length; j++) {
                blocks[i][j] = new Sprite(
                        loadMedia.getWidthBlock() * 3,
                        loadMedia.getHeightBlock() * 3,
                        ((loadMedia.getWidthBlock() * 3) * i)+23,
                        ((loadMedia.getHeightBlock() * 3) * j)+96,
                        this.blockImageStage[i][j]);
            }
        }

        bar = new Sprite(LoadMedia.WIDTH_BAR_SHIP_SPRITE, LoadMedia.HEIGHT_BAR_SHIP_SPRITE, 300, gamePanel.getHeight() - 70, loadMedia.getBarShipSubBuffer()[0]);
//        mainFont = new Font("Arial", Font.BOLD, 20);
        rescaleImage();
    }
//    public static void loadFonts() {
//        try{
//            mainFont = Font.createFont(Font.TRUETYPE_FONT,
//                    GameScreen.class.getResourceAsStream("/fonts/fontPixelArkanoid.ttf"));
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }


    public void paintWindow(Graphics g) {
//        g.drawRect(0,0,gamePanel.getWidth(),100);
        fillBackground(g);
        ball.pintarSpriteEnMundo(g);
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                blocks[i][j].pintarSpriteEnMundo(g);
            }
        }
        bar.pintarSpriteEnMundo(g);
    }

    /**
     * Método que se utiliza para rellenar el fondo del JPanel.
     *
     * @param g Es el gráficos sobre el que vamos a pintar el fondo.
     */
    private void fillBackground(Graphics g) {
        g.drawImage(imageScaling, 0, 0, null);
    }


    /**
     * Método para mover todos los Sprites del juego.
     */
    private void moveSprites() {
        ball.moverSprite(gamePanel.getWidth()-20, gamePanel.getHeight()-20);
        bar.moverSprite();
    }

    private void checkCollision() {
        //Comprobar colisiones con el bar

        if (ball.colisionan(bar)) {
            double centerDistance = (ball.getPosX() + ball.getAncho() / 2d) - (bar.getPosX() + LoadMedia.WIDTH_BAR_SHIP / 2d);
            double impactCof = centerDistance / (LoadMedia.WIDTH_BAR_SHIP / 2d);
            double vT = ball.getTotalSpeed();
            double maxAngle = Math.toRadians(60);
            double angle = Math.PI / 2 - maxAngle * Math.abs(impactCof);
            double newVX = vT * Math.cos(angle) * ((centerDistance > 0) ? 1 : -1);
            double newVY = vT * Math.sin(angle);
//            System.out.println(impactCof+"\t"+Math.toDegrees(angle));
            ball.setVelocidadX(newVX);
            ball.setVelocidadY(-newVY);
        }
    }

    private void checkCollisionBlock() {

        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < this.blocks[i].length; j++) {

                if (ball.colisionan(blocks[i][j])) {
                    totalblock++;
                    gamePanel.getScoreHeader().getScreenActual().addPoint();
                    Sprite bloque = blocks[i][j];
                    if (Math.abs(ball.getPosX() - bloque.getPosX()) < ((ball.getAncho() + bloque.getAncho()) / 2)) {
                        ball.setVelocidadY(-ball.getVelocidadY());
                    }
                    if (Math.abs(ball.getPosY() - bloque.getPosY()) < ((ball.getAlto() + bloque.getAlto()) / 2)) {
                        ball.setVelocidadX(-ball.getVelocidadX());
                    }
                    blocks[i][j] = new Sprite(1, 1, -100, -100, null);
                }
            }
        }

    }

    public void executeFrame() {
        checkCollision();
        checkCollisionBlock();
        moveSprites();

    }

    public void keyPressed(KeyEvent e) {


    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        synchronized (GamePanel.class) {
            getKeyLogic(e);
            if(keysPressed[1]){
                bar.setVelocidadX(bar.getVelocidadX()+45);
            }
            if(keysPressed[0]){
                bar.setVelocidadX(bar.getVelocidadX()-45);
            }

            if(!keysPressed[0] && !keysPressed[1]){
                bar.setVelocidadX(0);
            }

        }
        return false;
    }

    private void getKeyLogic(KeyEvent e) {
        int keyCode;
        switch (e.getID()) {
            case KeyEvent.KEY_PRESSED:
                keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_A:
                        keysPressed[0]=true;
                        break;
                    case KeyEvent.VK_D:
                        keysPressed[1]=true;
                        break;
                }
                break;
            case KeyEvent.KEY_RELEASED:
                keyCode = e.getKeyCode();
                switch (keyCode){
                    case KeyEvent.VK_A:
                        keysPressed[0]=false;
                        break;
                    case KeyEvent.VK_D:
                        keysPressed[1]=false;
                        break;
                }


        }
    }

    public void moveMouse(MouseEvent e) {
        bar.setPosX(e.getX() - LoadMedia.WIDTH_BAR_SHIP / 2);
        bar.setPosY(gamePanel.getHeight() - 50);

    }


    public void clickMouse(MouseEvent e) {
        cont++;
        if (cont == 1) {
            ball.setTotalSpeed(8);
            ball.setVelocidadX(ball.getTotalSpeed() * Math.cos(Math.toRadians(60)));
            ball.setVelocidadY(ball.getTotalSpeed() * Math.sin(Math.toRadians(60)) * -1);
        }

    }


    public void resizeScreen(ComponentEvent e) {
        rescaleImage();

    }


    private void rescaleImage() {
        imageScaling = bufferedImage.getScaledInstance(gamePanel.getWidth(), gamePanel.getHeight(), Image.SCALE_SMOOTH);
    }



}