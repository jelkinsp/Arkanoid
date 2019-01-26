package screen;

import base.BlockGenerator;
import base.GamePanel;
import sprites.LoadImage;
import sprites.Sprite;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * @author Jose Luis Luengo Ramos
 */
public class GameScreen implements IScreen {
    private static final int SIZE_BALL = 15;
    private static final int WIDTH_BAR = 180;
    private static final int HEIGH_BAR = 21;
    private static final Color COLOR_SCORE = Color.WHITE;
    private static final Color COLOR_DISPARO = Color.GREEN;

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

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void initWindow() {
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

        bar = new Sprite(100, 33, 300, gamePanel.getHeight() - 50, loadImage.getBarShipSubBuffer()[0]);
        fontTimer = new Font("Arial", Font.BOLD, 20);
        rescaleImage();
    }


    public void paintWindow(Graphics g) {
        fillBackground(g);
        ball.pintarSpriteEnMundo(g);
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                blocks[i][j].pintarSpriteEnMundo(g);
            }
        }
        bar.pintarSpriteEnMundo(g);
        paintTimer(g);

    }

    private void paintTimer(Graphics g) {
        Font f = g.getFont();
        Color c = g.getColor();

        g.setColor(COLOR_SCORE);
        g.setFont(fontTimer);

        g.setColor(c);
        g.setFont(f);
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
        ball.moverSprite(gamePanel.getWidth(), gamePanel.getHeight());
    }

    private void checkCollision() {
        //Comprobar colisiones con el bar

        if (ball.colisionan(bar)) {
            if (ball.getVelocidadX() > 0) {
                ball.setVelocidadX(-1 * Math.abs(ball.getVelocidadX()));
            } else {
                ball.setVelocidadX(Math.abs(ball.getVelocidadX()));
            }
            if (ball.getVelocidadY() > 0) {
                ball.setVelocidadY(-1 * Math.abs(ball.getVelocidadY()));
            } else {
                ball.setVelocidadY(Math.abs(ball.getVelocidadY()));
            }
        }
    }

    private void checkCollisionBlock() {

        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < this.blocks[i].length; j++) {

                if (ball.colisionan(blocks[i][j])) {
                    Sprite bloque = blocks[i][j];
                    if (Math.abs(ball.getPosX() - bloque.getPosX()) < ((ball.getAncho() + bloque.getAncho()) / 2)) {
                        ball.setVelocidadY( - ball.getVelocidadY());
                    } else if (Math.abs(ball.getPosY() - bloque.getPosY()) < ((ball.getAlto() + bloque.getAlto()) / 2)) {
                        ball.setVelocidadX( - ball.getVelocidadX());
                    }
//                    if (ball.getVelocidadX() > 0) {
//                        ball.setVelocidadX(-1 * Math.abs(ball.getVelocidadX()));
//                    } else {
//                        ball.setVelocidadX(Math.abs(ball.getVelocidadX()));
//                    }
//                    if (ball.getVelocidadY() > 0) {
//                        ball.setVelocidadY(-1 * Math.abs(ball.getVelocidadY()));
//                    } else {
//                        ball.setVelocidadY(Math.abs(ball.getVelocidadY()));
//                    }
                    blocks[i][j] = new Sprite(1, 1, -100, -100, null);
                }
            }
        }

    }

    public void executeFrame() {
        checkCollision();
        checkCollisionBlock();
//        comprobarColisionesDisparo1();
        moveSprites();

    }


    public void moveMouse(MouseEvent e) {
        bar.setPosX(e.getX() - bar.getAncho() / 2);
        bar.setPosY(gamePanel.getHeight() - 50);

    }


    public void clickMouse(MouseEvent e) {
        cont++;
        if (cont == 1) {
            ball.setVelocidadX(3);
            ball.setVelocidadY(9);
        }
//        if (asteroides.size() > 4) {
//
//        } else {
//            if (disparo == null) {
//                disparo = new Sprite(ANCHO_DISPARO, ALTO_DISPARO,
//                        e.getX() - (ANCHO_DISPARO - 40) / 2,
//                        e.getY() - ALTO_DISPARO / 2,
//                        0,
//                        VELOCIDAD_DISPARO,
//                        null);
//                disparo.setColor(COLOR_DISPARO);
//            }
//            if (disparo2 == null) {
//                disparo2 = new Sprite(ANCHO_DISPARO, ALTO_DISPARO,
//                        e.getX() - (ANCHO_DISPARO + 40) / 2,
//                        e.getY() - ALTO_DISPARO / 2,
//                        0,
//                        VELOCIDAD_DISPARO,
//                        null);
//                disparo2.setColor(COLOR_DISPARO);
//            }

    }


    public void resizeScreen(ComponentEvent e) {
        rescaleImage();

    }


    private void rescaleImage() {
        //Pensar en cada caso particular
        imageScaling = bufferedImage.getScaledInstance(gamePanel.getWidth(), gamePanel.getHeight(), Image.SCALE_SMOOTH);
    }
}