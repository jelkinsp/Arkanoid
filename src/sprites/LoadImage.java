package sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Esta clase carga todas las imagenes y los tamaños de los sprites
 * Se pueden obtener mediante los GETTER.
 *
 *
 * @author : Jose Luis Luengo Ramos
 */
public class LoadImage {

    //Bloques
    private final static int POSITION_X_BLOCK = 0;
    private final static int POSITION_Y_BLOCK = 0;
    private final static int HEIGHT_BLOCK = 8;
    private final static int WIDTH_BLOCK = 160;
    //Bola de energia
    private final static int POSITION_X_BALL = 0;
    private final static int POSITION_Y_BALL = 12;
    private final static int HEIGHT_BALL_SPRITE = 9;
    private final static int WIDTH_BALL_SPRITE = 9;
    //bola sin sombra.
    private final static int HEIGHT_BALL = 5;
    private final static int WIDTH_BALL = 5;
    //Nave
    private final static int POSITION_X_BAR_SHIP = 0;
    private final static int POSITION_Y_BAR_SHIP = 25;
    private final static int HEIGHT_BAR_SHIP_SPRITE = 36;
    private final static int WIDTH_BAR_SHIP_SPRITE = 12;
    //Nave sin sombra.
    private final static int HEIGHT_BAR_SHIP = 32;
    private final static int WIDTH_BAR_SHIP = 8;

    private final static int SIZE_BETWEEN_BAR_SHIP = 5;



    private BufferedImage spritesSubBuffer;
    private BufferedImage ballSubBuffer;
    private BufferedImage backgroundSubBuffer;
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
    }

    /**
     * Inicializa el Sprite principal. Lee la imagen y la paso al spritesSubBuffer.
     * Obtenemos los bloques, la bola de energia, la nave con sus diferentes estados.
     *
     */
    private void initializeSpritesSubBuffer() {
        try {
            this.spritesSubBuffer = ImageIO.read(getClass().getResource("../image/spritesArkanoid.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initializeBallBuffer() {
        //Todo conectar todo, ya tengo la posicion y tamaños
    }

    private void initializeBackgroundBuffer() {
        //Todo
    }

    private void initializeShipBuffer() {
        //Todo conectar todo, ya tengo la posicion y tamaños
    }

    private void initializeShipDestroidBuffer() {
        //Todo
    }

    /**
     * Obtiene los Sprites correspondientes a los bloques, se obtiene mediante
     * subimagenes del sprite principal con posicionamiento y tamaño.
     *
     */
    private void initializeBlockBuffer() {
        this.blockSubBuffer = new BufferedImage[10];
        for (int i = 0; i < this.blockSubBuffer.length; i++) {
            this.blockSubBuffer[i] = this.spritesSubBuffer.getSubimage(
                    POSITION_X_BLOCK *i/* Todo hay que solucionar que vaya cogiendo la siguiente posicion*/,
                    POSITION_Y_BLOCK,
                    WIDTH_BLOCK * i,
                    HEIGHT_BLOCK);
        }
    }
}
