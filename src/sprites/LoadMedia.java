package sprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Esta clase carga todas las imagenes y los tamaños de los sprites
 * Se pueden obtener mediante los GETTER.
 *
 * @author : Jose Luis Luengo Ramos
 */
public class LoadMedia {

    //Bloques
    public final static int POSITION_X_BLOCK = 0;
    public final static int POSITION_Y_BLOCK = 0;
    public final static int WIDTH_BLOCK = 16;
    public final static int HEIGHT_BLOCK = 8;
    //Bola de energia
    public final static int POSITION_X_BALL = 0;
    public final static int POSITION_Y_BALL = 12;
    public final static int WIDTH_BALL_SPRITE = 9;
    public final static int HEIGHT_BALL_SPRITE = 9;
    //bola sin sombra.
    public final static int WIDTH_BALL = 5;
    public final static int HEIGHT_BALL = 5;
    //Nave
    public final static int POSITION_X_BAR_SHIP = 0;
    public final static int POSITION_Y_BAR_SHIP = 25;
    public final static int WIDTH_BAR_SHIP_SPRITE = 108;
    public final static int HEIGHT_BAR_SHIP_SPRITE = 36;
    //Nave sin sombra.
    public final static int WIDTH_BAR_SHIP = 96;
    public final static int HEIGHT_BAR_SHIP = 24;
    //Nave
    public final static int ORIG_WIDTH_BAR_SHIP_SPRITE = 36;
    public final static int ORIG_HEIGHT_BAR_SHIP_SPRITE = 12;
    //Nave sin sombra.
    public final static int ORIG_WIDTH_BAR_SHIP = 32;
    public final static int ORIG_HEIGHT_BAR_SHIP = 8;
    //Distacia entre las nave dentro de la imagen principal
    private final static int SIZE_BETWEEN_BAR_SHIP = 5;

    //Fondo
    public static final int BACKGROUND_POSITION_X = 0;
    public static final int BACKGROUND_POSITION_Y = 0;
    public static final int BACKGROUND_WIDTH = 175;
    public static final int BACKGROUND_HEIGHT = 209;
    public final static int SIZE_BETWEEN_BACKGROUND = 11;
    //Titulo
    public static final int TITLE_POSITION_X = 0;
    public static final int TITLE_POSITION_Y = 0;
    public static final int TITLE_WIDTH = 215;
    public static final int TITLE_HEIGHT = 49;
    //Icono
    public static final int ICON_POSITION_X = 0;
    public static final int ICON_POSITION_Y = 106;
    public static final int ICON_WIDTH = 94;
    public static final int ICON_HEIGHT = 30;
    public static final int GAME_OVER_POSITION_X = 0;
    public static final int GAME_OVER_POSITION_Y = 52;
    public static final int GAME_OVER_WIDTH = 144;
    public static final int GAME_OVER_HEIGHT = 17;
    public static final int CONTINUE_POSITION_X = 0;
    public static final int CONTINUE_POSITION_Y = 72;
    public static final int CONTINUE_WIDTH = 131;
    public static final int CONTINUE_HEIGHT = 14;


    private BufferedImage spritesBuffer;
    private BufferedImage spritesInitBuffer;
    private BufferedImage[] spritesInitSubBuffer;
    private BufferedImage ballSubBuffer;
    private BufferedImage liveUpSubBuffer;
    private BufferedImage backgroundBuffer;
    private BufferedImage[] backgroundSubBuffer;
    private BufferedImage[] barShipSubBuffer;
    private BufferedImage[] barShipDestroidSubBuffer;
    private BufferedImage[] blockSubBuffer;
    private Font mainFont;


    /**
     * Constructor para comenzar a cargar las imagenes
     *
     */
    public LoadMedia() {
        initializeFont();
        initializeSpritesSubBuffer();
        initializeSpritesInitSubBuffer();
        initializeBallBuffer();
        initializeBackgroundBuffer();
        initializeShipBuffer();
        initializeShipDestroidBuffer();
        initializeBlockBuffer();
        initializeLiveUpBuffer();
    }


    private void initializeFont() {
            try {
                mainFont = Font.createFont(Font.TRUETYPE_FONT,new File("fonts/fontPixelArkanoid.ttf"));
//                        LoadMedia.class.getResourceAsStream("fonts/fontPixelArkanoid.ttf"));
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    private void initializeSpritesInitSubBuffer() {
        try {
            this.spritesInitBuffer = ImageIO.read(new File("image/spritesInitGame.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.spritesInitSubBuffer = new BufferedImage[4];
        this.spritesInitSubBuffer[0] = this.spritesInitBuffer.getSubimage(
                TITLE_POSITION_X,
                TITLE_POSITION_Y,
                TITLE_WIDTH,
                TITLE_HEIGHT);
        this.spritesInitSubBuffer[1] = this.spritesInitBuffer.getSubimage(
                ICON_POSITION_X,
                ICON_POSITION_Y,
                ICON_WIDTH,
                ICON_HEIGHT);
        this.spritesInitSubBuffer[2] = this.spritesInitBuffer.getSubimage(
                GAME_OVER_POSITION_X,
                GAME_OVER_POSITION_Y,
                GAME_OVER_WIDTH,
                GAME_OVER_HEIGHT);
        this.spritesInitSubBuffer[3] = this.spritesInitBuffer.getSubimage(
                CONTINUE_POSITION_X,
                CONTINUE_POSITION_Y,
                CONTINUE_WIDTH,
                CONTINUE_HEIGHT);

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
     */
    private void initializeBackgroundBuffer() {
        this.backgroundSubBuffer = new BufferedImage[3];

        try {
            this.backgroundBuffer = ImageIO.read(new File("image/spritesBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < this.backgroundSubBuffer.length; i++) {
            this.backgroundSubBuffer[i] = this.backgroundBuffer.getSubimage(
                    BACKGROUND_POSITION_X + ((SIZE_BETWEEN_BACKGROUND + BACKGROUND_WIDTH) * i),
                    BACKGROUND_POSITION_Y,
                    BACKGROUND_WIDTH,
                    BACKGROUND_HEIGHT);
        }
    }

    /**
     * Inicializa la imagen de la nave
     */
    private void initializeShipBuffer() {
        this.barShipSubBuffer = new BufferedImage[4];
        for (int i = 0; i < this.barShipSubBuffer.length; i++) {
            this.barShipSubBuffer[i] = this.spritesBuffer.getSubimage(
                    POSITION_X_BAR_SHIP + ((SIZE_BETWEEN_BAR_SHIP + ORIG_WIDTH_BAR_SHIP_SPRITE) * i),
                    POSITION_Y_BAR_SHIP,
                    ORIG_WIDTH_BAR_SHIP_SPRITE,
                    ORIG_HEIGHT_BAR_SHIP_SPRITE);
        }
    }

    /**
     * Inicializa la imagen de la nave destruyendose
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

    public BufferedImage[] getSpritesInitSubBuffer() {
        return spritesInitSubBuffer;
    }

    public void setSpritesInitSubBuffer(BufferedImage[] spritesInitSubBuffer) {
        this.spritesInitSubBuffer = spritesInitSubBuffer;
    }

    public Font getMainFont() {
        return mainFont;
    }

    public void setMainFont(Font mainFont) {
        this.mainFont = mainFont;
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

    public BufferedImage[] getBackgroundBuffer() {
        return backgroundSubBuffer;
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
