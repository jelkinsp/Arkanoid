package sprites;


import java.awt.*;
import java.awt.image.BufferedImage;

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
    private double posX;
    private double posY;
    //Variables para la velocidad
    private double totalSpeed;
    private double velocidadX;
    private double velocidadY;
    //Ruta de la imagen
    private BufferedImage bufferedImage;


    /**
     * Constructor simple para un Sprite sin imagen y sin velocidad.
     *
     * @param ancho Ancho que ocupa el Sprite (en pixels)
     * @param alto  Altura que ocupa el Sprite (en pixels)
     * @param posX  posición horizontal del sprite en el mundo.
     * @param posY  posición vertical del Sprite en el mundo. El origen se sitúa en la parte superior.
     */
    public Sprite(int ancho, int alto, int posX, int posY, BufferedImage bufferedImage) {
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.bufferedImage = bufferedImage;
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
    public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, BufferedImage bufferedImage) {
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.bufferedImage = bufferedImage;
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
            BufferedImage imagenSprite = bufferedImage;
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
        double thisCenterX = this.getPosX() + (this.getAncho() - 4) / 2;
        double thisCenterY = this.getPosY() + (this.getAlto() - 4) / 2;
        double otherCenterX = other.getPosX() + other.getAncho() / 2;
        double otherCenterY = other.getPosY() + other.getAlto() / 2;

        boolean overlapX = Math.abs(thisCenterX - otherCenterX) <= ((this.getAncho() - 4) + other.getAncho()) / 2;
        boolean overlapY = Math.abs(thisCenterY - otherCenterY) <= ((this.getAlto() - 4) + other.getAlto()) / 2;

        return (overlapX && overlapY);
    }


    /**
     * Método para mover el Sprite por el mundo.
     *
     * @param anchoMundo ancho del mundo sobre el que se mueve el Sprite
     * @param altoMundo  alto del mundo sobre el que se mueve el Sprite
     */
    public boolean moverSprite(int anchoMundo, int altoMundo) {



        if (posX >= anchoMundo - ancho) { //por la derecha
            velocidadX = -1 * Math.abs(velocidadX);
        }
        if (posX <= 20) {//por la izquierda
            velocidadX = Math.abs(velocidadX);
        }
        if (posY >= altoMundo - alto) {//por abajo
            velocidadY = -1 * Math.abs(velocidadY);
        }
        if (posY <= 20) { //Por arriba
            velocidadY = Math.abs(velocidadY);
        }
        posX = posX + velocidadX;
        posY = posY + velocidadY;

        if(posY > altoMundo-20){
            return true;
        }

        return false;
    }


    public void moverSprite() {
        if(velocidadX>5){
            velocidadX=5;
        }

        if(velocidadX<-5){
            velocidadX=-5;
        }

        posX = posX + velocidadX;
//        posY = posY + velocidadY;
    }


    /**
     * Método que pinta el Sprite en el mundo teniendo en cuenta las características propias del Sprite.
     *
     * @param g Es el Graphics del mundo que se utilizará para pintar el Sprite.
     */
    public void pintarSpriteEnMundo(Graphics g) {
        g.drawImage(buffer, (int) Math.round(posX), (int) Math.round(posY), null);
//        g.fillRect(posX,posY,ancho,alto);

    }

    public double getTotalSpeed() {
        return totalSpeed;
    }

    public void setTotalSpeed(double totalSpeed) {
        this.totalSpeed = totalSpeed;
    }

    //Métodos para obtener:
    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public double getVelocidadX() {
        return velocidadX;
    }

    public double getVelocidadY() {
        return velocidadY;
    }


    //métodos para cambiar:
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public void setVelocidadX(double velocidadX) {

        this.velocidadX = velocidadX;
    }

    public void setVelocidadY(double velocidadY) {
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
