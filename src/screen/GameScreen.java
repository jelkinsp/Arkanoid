package screen;

import base.BlockGenerator;
import base.GamePanel;
import sprites.LoadMedia;
import sprites.Sprite;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Clase con la pantalla principal
 *
 * @author Jose Luis Luengo Ramos
 */
public class GameScreen implements IScreen, KeyEventDispatcher {
    private final int DIFFICULT = 55;
    private final int DIFFICULT2 = 30;

    private GamePanel gamePanel;
    private LoadMedia loadMedia;
    private BufferedImage bufferedImage;
    private BufferedImage[][] blockImageStage;
    private BlockGenerator blockList;

    private Image imageScaling;
    private boolean ground;
    private Sprite bar;
    private Sprite ball;
    private Sprite[][] blocks;

    private Clip soundClip;
    private int cont;
    private boolean[] keysPressed;
    private int totalblock;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        keysPressed = new boolean[2];
        this.loadMedia = gamePanel.getLoadMedia();
    }

    /**
     * Metodo para iniciar y cargar los sonidos
     *
     * @param soundFile fichero con la el fichero cargado
     */
    public void initSound(File soundFile) {
        try {
            soundClip = AudioSystem.getClip();
            soundClip.open(AudioSystem.getAudioInputStream(soundFile));
            FloatControl volumen = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumen.setValue(-8.0f);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initWindow() {
        initSound(loadMedia.getSoundFileStage());
        soundClip.start();
        this.totalblock = 0;
        this.blockList = new BlockGenerator(this.loadMedia.getBlockSubBuffer());
        this.blocks = new Sprite[12][6];
        this.blockImageStage = this.blockList.getStage1();
        cont = 0;
        ball = new Sprite(
                20,
                20,
                300,
                gamePanel.getHeight() - 100,
                0,
                0,
                loadMedia.getBallSubBuffer());
        bufferedImage = loadMedia.getBackgroundBuffer()[0];
        for (int i = 0; i < this.blockImageStage.length; i++) {
            for (int j = 0; j < this.blockImageStage[i].length; j++) {
                blocks[i][j] = new Sprite(
                        loadMedia.getWidthBlock() * 3,
                        loadMedia.getHeightBlock() * 3,
                        ((loadMedia.getWidthBlock() * 3) * i) + 23,
                        ((loadMedia.getHeightBlock() * 3) * j) + 96,
                        this.blockImageStage[i][j]);
            }
        }
        bar = new Sprite(
                LoadMedia.WIDTH_BAR_SHIP_SPRITE,
                LoadMedia.HEIGHT_BAR_SHIP_SPRITE,
                300, gamePanel.getHeight() - 70
                , loadMedia.getBarShipSubBuffer()[0]);
        rescaleImage();
    }


    @Override
    public void paintWindow(Graphics g) {
        fillBackground(g);
        ball.paintSpriteInWord(g);
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                blocks[i][j].paintSpriteInWord(g);
            }
        }
        bar.paintSpriteInWord(g);
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
        this.ground = ball.moveSprite(gamePanel.getWidth() - 20, gamePanel.getHeight() - 20);
        bar.moveSprite();

    }

    /**
     * Comprueba la colision entre la bola y la barra.
     * Calcula mediante radianes el angulo optiomo a devolder la bola.
     *
     */
    private void checkCollision() {
        if (ball.collision(bar)) {
            initSound(loadMedia.getSoundFileBar());
            soundClip.start();
            double centerDistance = (ball.getPosX() + ball.getWidth() / 2d) - (bar.getPosX() + LoadMedia.WIDTH_BAR_SHIP / 2d);
            double impactCof = centerDistance / (LoadMedia.WIDTH_BAR_SHIP / 2d);
            double vT = ball.getTotalSpeed();
            double maxAngle = Math.toRadians(60);
            double angle = Math.PI / 2 - maxAngle * Math.abs(impactCof);
            double newVX = vT * Math.cos(angle) * ((centerDistance > 0) ? 1 : -1);
            double newVY = vT * Math.sin(angle);
//            System.out.println(impactCof+"\t"+Math.toDegrees(angle));
            ball.setSpeedX(newVX);
            ball.setSpeedY(-newVY);
        }
    }

    /**
     * Comprueba las colisiones entre la bola y los bloques.
     * Cuenta la cantidad de bloques eliminados para realizar distintas tareas.
     * Incrementa la velocidad de la bola segun la cantidad de bloques eliminados.
     *
     */
    private void checkCollisionBlock() {
        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < this.blocks[i].length; j++) {
                if (ball.collision(blocks[i][j])) {
                    initSound(loadMedia.getSoundFileBlock());
                    soundClip.start();
                    totalblock++;
                    if (totalblock > DIFFICULT2 && totalblock <= 40) {
                        ball.setTotalSpeed(ball.getTotalSpeed() + ((ball.getTotalSpeed() / 4) * 0.1));
                    } else if (totalblock > DIFFICULT && totalblock <= 60) {
                        ball.setTotalSpeed(ball.getTotalSpeed() + ((ball.getTotalSpeed() / 3) * 0.1));
                    }
                    gamePanel.getScoreHeader().getScreenActual().addPoint();
                    Sprite bloque = blocks[i][j];
                    if (Math.abs(ball.getPosX() - bloque.getPosX()) < ((ball.getWidth() + bloque.getWidth()) / 2)) {
                        ball.setSpeedY(-ball.getSpeedY());
                    }
                    if (Math.abs(ball.getPosY() - bloque.getPosY()) < ((ball.getHeight() + bloque.getHeight()) / 2)) {
                        ball.setSpeedX(-ball.getSpeedX());
                    }
                    blocks[i][j] = new Sprite(1, 1, -100, -100, null);
                }
            }
        }

    }

    @Override
    public void executeFrame() {
        checkWin();
        checkLose();
        checkCollision();
        checkCollisionBlock();
        moveSprites();
    }

    /**
     * Chequea si el jugador a perdido y carga la pantalla de GameOver
     *
     */
    private void checkLose() {
        if (this.ground) {
            soundClip.close();

            initSound(loadMedia.getSoundFileDestroidBar());
            soundClip.start();
            GameOverScreen gameOver = new GameOverScreen(gamePanel);
            gameOver.initWindow();
            gamePanel.setScreenActual(gameOver);
        }
    }

    /**
     * Chequea si el jugador a ganado y carga el siguente nivel
     *
     */
    private void checkWin() {
        if (totalblock > 71) {
            soundClip.close();
            initSound(loadMedia.getSoundFileStage());
            soundClip.start();
            GameScreen2 gameScreen = new GameScreen2(gamePanel);
            gameScreen.initWindow();
            gamePanel.setScreenActual(gameScreen);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        synchronized (GamePanel.class) {
            getKeyLogic(e);
            if (keysPressed[1]) {
                if (bar.getPosX() + LoadMedia.ORIG_WIDTH_BAR_SHIP <= gamePanel.getWidth() - 120) {
                    bar.setSpeedX(bar.getSpeedX() + 45);
                } else {
                    bar.setPosX(gamePanel.getWidth() - 120);
                    bar.setSpeedX(0);
//                    System.out.println("D-Paro: " + bar.getSpeedX());
                }
//                System.out.println("D-Aumento: " + bar.getSpeedX());
//                System.out.println("D-posicion: " + bar.getPosX());
            } else {

                bar.setSpeedX(0);
            }
            if (keysPressed[0]) {
                if (bar.getPosX() >= gamePanel.getX() + 35) {
                    bar.setSpeedX(bar.getSpeedX() - 45);
                } else {
//                    System.out.println("I-Paro: " + bar.getSpeedX());
                    bar.setPosX(gamePanel.getX() + 25);
                    bar.setSpeedX(0);
                }
//                System.out.println("I-Aumento: " + bar.getSpeedX());
//                System.out.println("I-posicion: " + bar.getPosX());
            }
            if (!keysPressed[0] && !keysPressed[1]) {
                bar.setSpeedX(0);
            }
        }
        return false;
    }

    /**
     * Comprueba que teclas se estan pulsando.
     *
     * @param e
     */

    private void getKeyLogic(KeyEvent e) {
        int keyCode;
        switch (e.getID()) {
            case KeyEvent.KEY_PRESSED:
                keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_A:
                        keysPressed[0] = true;
                        break;
                    case KeyEvent.VK_D:
                        keysPressed[1] = true;
                        break;
                }
                break;
            case KeyEvent.KEY_RELEASED:
                keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_A:
                        keysPressed[0] = false;
                        break;
                    case KeyEvent.VK_D:
                        keysPressed[1] = false;
                        break;
                }
        }
    }

    @Override
    public void clickMouse(MouseEvent e) {
        cont++;
        if (cont == 1) {
            ball.setTotalSpeed(4);
            ball.setSpeedX(ball.getTotalSpeed() * Math.cos(Math.toRadians(60)));
            ball.setSpeedY(ball.getTotalSpeed() * Math.sin(Math.toRadians(60)) * -1);
        }

    }


    @Override
    public void resizeScreen(ComponentEvent e) {
        rescaleImage();

    }


    private void rescaleImage() {
        imageScaling = bufferedImage.getScaledInstance(gamePanel.getWidth(), gamePanel.getHeight(), Image.SCALE_SMOOTH);
    }


}