package sprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Jose Luis Luengo Ramos
 * Clase Sprite. Representa un elemento pintable y colisionable del juego.
 */
public class Sprite {

    private BufferedImage buffer;
    private Color color = Color.BLACK;
    //Variables de dimensión
    private int ancho;
    private int alto;
    //Variables de colocación
    private int posX;
    private int posY;
    //Variables para la velocidad
    private int velocidadX;
    private int velocidadY;
    //Ruta de la imagen
    private String rutaImagen;


    /**
     * Constructor simple para un Sprite sin imagen y sin velocidad.
     *
     * @param ancho Ancho que ocupa el Sprite (en pixels)
     * @param alto  Altura que ocupa el Sprite (en pixels)
     * @param posX  posición horizontal del sprite en el mundo.
     * @param posY  posición vertical del Sprite en el mundo. El origen se sitúa en la parte superior.
     */
    public Sprite(int ancho, int alto, int posX, int posY, String rutaImagen) {
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.rutaImagen = rutaImagen;
        actualizarBuffer();
    }

    /**
     * Constructor para un Sprite sin imagen.
     *
     * @param ancho      Ancho que ocupa el Sprite (en pixels)
     * @param alto       Altura que ocupa el Sprite (en pixels)
     * @param posX       posición horizontal del sprite en el mundo.
     * @param posY       posición vertical del Sprite en el mundo. El origen se sitúa en la parte superior.
     * @param velocidadX velocidad horizontal del Sprite.
     * @param velocidadY velocidad vertical del Sprite.
     */
    public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, String rutaImagen) {
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.rutaImagen = rutaImagen;
        actualizarBuffer();
    }

    /**
     * Método para actualizar el buffer que guarda cada Sprite.
     * Por ahora sólo guarda un bufferedImage que está completamente relleno de un color.
     */
    public void actualizarBuffer() {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();

        try {
            BufferedImage imagenSprite = ImageIO.read(new File(rutaImagen));
            //pinto en el buffer la imagen
            g.drawImage(imagenSprite.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);

        } catch (Exception e) {
            g.setColor(color);
            g.fillRect(0, 0, ancho, alto);
            g.dispose();
        }

    }


    /**
     * Método para comprobar si el Sprite colisiona con otro.
     *
     * @param other El otro Sprite con el que se comprueba si hay colisión.
     * @return verdadero si ambos Sprites colisionan.
     */
    public boolean colisionan(Sprite other) {

        boolean colisionAncho = false;
        boolean colisionAlto = false;
//        int top, right, bottom, left;
//        int top2, right2, bottom2, left2;
//
//        top = ancho;
//        right = alto;
//        bottom = ancho;
//        left = alto;
//
//        top2 = otroSprite.getAncho();
//        right2 = otroSprite.getAlto();
//        bottom2 = otroSprite.getAncho();
//        left2 = otroSprite.getAlto();
//
//        Rectangle r1 = new Rectangle(posX, posY,ancho, alto);
//        Rectangle r2 = new Rectangle(posX, posY, ancho, alto);
//
//        return r2.intersects(r1);
        //Checkeamos si comparten algún espacio a lo ancho:
//        if (posX <=  otroSprite.getPosX()) { //El Sprite actual se encuentra más cerca del eje de las X.
//            colisionAncho = posX + ancho >= otroSprite.getPosX();
//        } else { //El otro Sprite se encuentra más cerca del eje de las X.
//            colisionAncho = otroSprite.getPosX() + otroSprite.getAncho() >= posX;
//        }
//
//        if (posY <= otroSprite.getPosY()) {
//            colisionAlto = posY - alto > otroSprite.getPosY();
//        } else {
//            colisionAlto = otroSprite.getAlto() > posY - otroSprite.getPosY();
//        }
//        return colisionAncho && colisionAlto;
        int thisCenterX = this.getPosX() + this.getAncho() / 2;
        int thisCenterY = this.getPosY() + this.getAlto() / 2;
        int otherCenterX = other.getPosX() + other.getAncho() / 2;
        int otherCenterY = other.getPosY() + other.getAlto() / 2;

        boolean overlapX = Math.abs(thisCenterX - otherCenterX) <= (this.getAncho() + other.getAncho()) / 2;
        boolean overlapY = Math.abs(thisCenterY - otherCenterY) <= (this.getAlto() + other.getAlto()) / 2;

        return (overlapX && overlapY);
    }


    /**
     * Método para mover el Sprite por el mundo.
     *
     * @param anchoMundo ancho del mundo sobre el que se mueve el Sprite
     * @param altoMundo  alto del mundo sobre el que se mueve el Sprite
     */
    public void moverSprite(int anchoMundo, int altoMundo) {
        if (posX >= anchoMundo - ancho) { //por la derecha
            velocidadX = -1 * Math.abs(velocidadX);
        }
        if (posX <= 0) {//por la izquierda
            velocidadX = Math.abs(velocidadX);
        }
        if (posY >= altoMundo - alto) {//por abajo
            velocidadY = -1 * Math.abs(velocidadY);
        }
        if (posY <= 0) { //Por arriba
            velocidadY = Math.abs(velocidadY);
        }
        posX = posX + velocidadX;
        posY = posY + velocidadY;
    }


    public void moverSprite() {
        posX = posX + velocidadX;
        posY = posY + velocidadY;
    }


    /**
     * Método que pinta el Sprite en el mundo teniendo en cuenta las características propias del Sprite.
     *
     * @param g Es el Graphics del mundo que se utilizará para pintar el Sprite.
     */
    public void pintarSpriteEnMundo(Graphics g) {
        g.drawImage(buffer, posX, posY, null);
//        g.fillRect(posX,posY,ancho,alto);

    }


    //Métodos para obtener:
    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public int getVelocidadX() {
        return velocidadX;
    }

    public int getVelocidadY() {
        return velocidadY;
    }


    //métodos para cambiar:
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public void setVelocidadX(int velocidadX) {
        this.velocidadX = velocidadX;
    }

    public void setVelocidadY(int velocidadY) {
        this.velocidadY = velocidadY;
    }


    public Color getColor() {
        return color;
    }


    public void setColor(Color color) {
        this.color = color;
        actualizarBuffer();


    }


}
