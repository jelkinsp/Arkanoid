package screen;

import base.BlockGenerator;
import base.GamePanel;
import base.ScoreHeader;
import sprites.LoadImage;
import sprites.Sprite;

import javax.swing.*;
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
    LoadImage loadImage;
    BufferedImage bufferedImage;
    BufferedImage[][] blockImageStage;
    BlockGenerator blockList;

    Image imageScaling;

    Sprite bar;
    Sprite ball;
    Sprite[][] blocks;

    Font fontTimer;
    private int cont;
    private boolean[] keysPressed;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        keysPressed = new boolean[2];
    }


    public void initWindow() {
//        this.gameJPanelContainer = new JPanel();
//        this.mainJPanel = new JPanel();
//        this.gameJPanelContainer.setBackground(Color.BLACK);
//        this.mainJPanel.setBackground(Color.BLACK);
//        this.gameJPanelContainer.setLayout(new GridLayout(1,1));
//        this.mainJPanel.setLayout(new GridLayout(1,1));
//        this.gamePanel.setLayout(new GridLayout(2,1));
//        this.gamePanel.add(this.mainJPanel);
//        this.gamePanel.add(this.gameJPanelContainer);

        this.loadImage = new LoadImage();
        this.blockList = new BlockGenerator(this.loadImage.getBlockSubBuffer());
        this.blocks = new Sprite[13][6];
        this.blockImageStage = this.blockList.getStage1();
        cont = 0;
        ball = new Sprite(20, 20, 300, gamePanel.getHeight() - 100, 0, 0, loadImage.getBallSubBuffer());

        bufferedImage = loadImage.getBackgroundBuffer();

        for (int i = 0; i < this.blockImageStage.length; i++) {
            for (int j = 0; j < this.blockImageStage[i].length; j++) {
                blocks[i][j] = new Sprite(
                        loadImage.getWidthBlock() * 3,
                        loadImage.getHeightBlock() * 3,
                        (loadImage.getWidthBlock() * 3) * i,
                        (loadImage.getHeightBlock() * 3) * j,
                        this.blockImageStage[i][j]);
            }
        }

        bar = new Sprite(LoadImage.WIDTH_BAR_SHIP_SPRITE, LoadImage.HEIGHT_BAR_SHIP_SPRITE, 300, gamePanel.getHeight() - 50, loadImage.getBarShipSubBuffer()[0]);
        fontTimer = new Font("Arial", Font.BOLD, 20);
        rescaleImage();
    }


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
//        paintTimer(g);

    }
//
//    private void paintTimer(Graphics g) {
//        Font f = g.getFont();
//        Color c = g.getColor();
//
//        g.setColor(COLOR_SCORE);
//        g.setFont(fontTimer);
//
//        g.setColor(c);
//        g.setFont(f);
//    }


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
        ball.moverSprite(gamePanel.getWidth(), gamePanel.getHeight());
    }

    private void checkCollision() {
        //Comprobar colisiones con el bar

        if (ball.colisionan(bar)) {
            double centerDistance = (ball.getPosX() + ball.getAncho() / 2d) - (bar.getPosX() + LoadImage.WIDTH_BAR_SHIP / 2d);
            double impactCof = centerDistance / (LoadImage.WIDTH_BAR_SHIP / 2d);
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
                    gamePanel.getScoreHeader().getScreenActual().addPoint();
//                    gamePanel.getScoreHeader().repaint();

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
            if(keysPressed[0]){
//                bar.setVelocidadX(bar.getVelocidadX()+5);
                bar.setPosX(bar.getPosX()-50);
            }
            if(keysPressed[1]){
                bar.setPosX(bar.getPosX()+50);
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
        bar.setPosX(e.getX() - LoadImage.WIDTH_BAR_SHIP / 2);
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