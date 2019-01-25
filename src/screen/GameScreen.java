package screen;

import base.GamePanel;
import sprites.LoadImage;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Jose Luis Luengo Ramos
 */
public class GameScreen implements IScreen {
    private static final int SIZE_BALL = 15;
    private static final int WIDTH_BAR = 100;
    private static final int HEIGH_BAR = 12;
    private static final Color COLOR_SCORE = Color.WHITE;
    private static final Color COLOR_DISPARO = Color.GREEN;

    GamePanel gamePanel;

    LoadImage loadImage = new LoadImage();
    BufferedImage bufferedImage;
    Image imageScaling;

    Sprite bar;
    Sprite ball;

    Font fontTimer;
    private int cont;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void initWindow() {
        cont = 0;
        ball = new Sprite(20, 20, 300, gamePanel.getHeight()-100, 0, 0, loadImage.getBallSubBuffer());

            bufferedImage = loadImage.getBackgroundBuffer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        bar = new Sprite(100, 33, 300, gamePanel.getHeight()-50, loadImage.getBarShipSubBuffer()[0]);
        fontTimer = new Font("Arial", Font.BOLD, 20);
        rescaleImage();
    }


    public void paintWindow(Graphics g) {
        fillBackground(g);
        ball.pintarSpriteEnMundo(g);
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



                if(ball.getVelocidadX() > 0) {
                    ball.setVelocidadX(-1*Math.abs(ball.getVelocidadX()));

                } else {
                    ball.setVelocidadX(Math.abs(ball.getVelocidadX()));

                }
                if(ball.getVelocidadY() > 0) {
                    ball.setVelocidadY(-1*Math.abs(ball.getVelocidadY()));
                } else {
                    ball.setVelocidadY(Math.abs(ball.getVelocidadY()));
                }
        }
    }


    public void executeFrame() {
        checkCollision();
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
            ball.setVelocidadX(4);
            ball.setVelocidadY(4);
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