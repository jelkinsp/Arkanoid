package sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Esta clase carga todas las imagenes y los tamaños de los sprites
 * Se pueden obtener mediante los GETTER.
 *
 * @author : Jose Luis Luengo Ramos
 */
public class LoadImage {

    //Bloques
    private final static int POSITION_X_BLOCK = 0;
    private final static int POSITION_Y_BLOCK = 0;
    private final static int WIDTH_BLOCK = 16;
    private final static int HEIGHT_BLOCK = 8;
    //Bola de energia
    private final static int POSITION_X_BALL = 0;
    private final static int POSITION_Y_BALL = 12;
    private final static int WIDTH_BALL_SPRITE = 9;
    private final static int HEIGHT_BALL_SPRITE = 9;
    //bola sin sombra.
    private final static int WIDTH_BALL = 5;
    private final static int HEIGHT_BALL = 5;
    //Nave
    private final static int POSITION_X_BAR_SHIP = 0;
    private final static int POSITION_Y_BAR_SHIP = 25;
    private final static int WIDTH_BAR_SHIP_SPRITE = 36;
    private final static int HEIGHT_BAR_SHIP_SPRITE = 12;
    //Nave sin sombra.
    private final static int WIDTH_BAR_SHIP = 32;
    private final static int HEIGHT_BAR_SHIP = 8;
    //Distacia entre las nave dentro de la imagen principal
    private final static int SIZE_BETWEEN_BAR_SHIP = 5;


    private BufferedImage spritesBuffer;
    private BufferedImage ballSubBuffer;
    private BufferedImage liveUpSubBuffer;
    private BufferedImage backgroundBuffer;
    private BufferedImage[] barShipSubBuffer;
    private BufferedImage[] barShipDestroidSubBuffer;
    private BufferedImage[] blockSubBuffer;


    public LoadImage() {
        initializeSpritesSubBuffer();
        initializeBallBuffer();
        initializeBackgroundBuffer();
        initializeShipBuffer();
        initializeShipDestroidBuffer();
        initializeBlockBuffer();
        initializeLiveUpBuffer();
    }


    /**
     * Inicializa el Sprite principal. Lee la imagen y la paso al spritesBuffer.
     * Obtenemos los bloques, la bola de energia, la nave con sus diferentes estados.
     */
    private void initializeSpritesSubBuffer() {
        try {
            this.spritesBuffer = ImageIO.read(new File("image/spritesArkanoid.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicializa la imagen de la bola
     *
     */
    private void initializeBallBuffer() {
        this.ballSubBuffer = this.spritesBuffer.getSubimage(
                POSITION_X_BALL,
                POSITION_Y_BALL,
                WIDTH_BALL_SPRITE,
                HEIGHT_BALL_SPRITE);
    }

    /**
     * Inicializa la imagen de la fondo
     *
     */
    private void initializeBackgroundBuffer() {
        try {
            this.backgroundBuffer = ImageIO.read(new File("image/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicializa la imagen de la nave
     *
     */
    private void initializeShipBuffer() {
        this.barShipSubBuffer = new BufferedImage[4];
        for (int i = 0; i < this.barShipSubBuffer.length; i++) {
            this.barShipSubBuffer[i] = this.spritesBuffer.getSubimage(
                    POSITION_X_BAR_SHIP + ((SIZE_BETWEEN_BAR_SHIP + WIDTH_BAR_SHIP_SPRITE) * i),
                    POSITION_Y_BAR_SHIP,
                    WIDTH_BAR_SHIP_SPRITE,
                    HEIGHT_BAR_SHIP_SPRITE
            );
        }
    }

    /**
     * Inicializa la imagen de la nave destruyendose
     *
     */
    private void initializeShipDestroidBuffer() {
        this.barShipDestroidSubBuffer = new BufferedImage[4];
        this.barShipDestroidSubBuffer[0] = this.spritesBuffer.getSubimage(0, 44, 36, 13);
        this.barShipDestroidSubBuffer[1] = this.spritesBuffer.getSubimage(41, 41, 30, 16);
        this.barShipDestroidSubBuffer[2] = this.spritesBuffer.getSubimage(82, 41, 32, 16);
        this.barShipDestroidSubBuffer[3] = this.spritesBuffer.getSubimage(123, 43, 30, 14);
    }

    /**
     * Inicializa la imagen del icono de la vida
     *
     */
    private void initializeLiveUpBuffer() {
        this.liveUpSubBuffer = this.spritesBuffer.getSubimage(12, 14, 16, 7);
    }

    /**
     * Obtiene los Sprites correspondientes a los bloques, se obtiene mediante
     * subimagenes del sprite principal con posicionamiento y tamaño.
     */
    private void initializeBlockBuffer() {
        this.blockSubBuffer = new BufferedImage[10];
        for (int i = 0; i < this.blockSubBuffer.length; i++) {
            this.blockSubBuffer[i] = this.spritesBuffer.getSubimage(
                    POSITION_X_BLOCK + (WIDTH_BLOCK * i),
                    POSITION_Y_BLOCK,
                    WIDTH_BLOCK,
                    HEIGHT_BLOCK);
        }
    }

    public static int getHeightBlock() {
        return HEIGHT_BLOCK;
    }

    public static int getWidthBlock() {
        return WIDTH_BLOCK;
    }

    public static int getHeightBall() {
        return HEIGHT_BALL;
    }

    public static int getWidthBall() {
        return WIDTH_BALL;
    }

    public static int getHeightBarShip() {
        return HEIGHT_BAR_SHIP;
    }

    public static int getWidthBarShip() {
        return WIDTH_BAR_SHIP;
    }

    public BufferedImage getBallSubBuffer() {
        return ballSubBuffer;
    }

    public BufferedImage getLiveUpSubBuffer() {
        return liveUpSubBuffer;
    }

    public BufferedImage getBackgroundBuffer() {
        return backgroundBuffer;
    }

    public BufferedImage[] getBarShipSubBuffer() {
        return barShipSubBuffer;
    }

    public BufferedImage[] getBarShipDestroidSubBuffer() {
        return barShipDestroidSubBuffer;
    }

    public BufferedImage[] getBlockSubBuffer() {
        return blockSubBuffer;
    }
}
