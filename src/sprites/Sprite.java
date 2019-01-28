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

    private int width;
    private int height;
    private double posX;
    private double posY;
    private double totalSpeed;
    private double speedX;
    private double speedY;
    private BufferedImage bufferedImage;

    /**
     * Constructor simple para un Sprite sin imagen y sin velocidad.
     *
     * @param width  Ancho que ocupa el Sprite (en pixels)
     * @param height Altura que ocupa el Sprite (en pixels)
     * @param posX   posición horizontal del sprite en el mundo.
     * @param posY   posición vertical del Sprite en el mundo. El origen se sitúa en la parte superior.
     */
    public Sprite(int width, int height, int posX, int posY, BufferedImage bufferedImage) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.bufferedImage = bufferedImage;
        updateBuffer();
    }

    /**
     * Constructor para un Sprite sin imagen.
     *
     * @param width  Ancho que ocupa el Sprite (en pixels)
     * @param height Altura que ocupa el Sprite (en pixels)
     * @param posX   posición horizontal del sprite en el mundo.
     * @param posY   posición vertical del Sprite en el mundo. El origen se sitúa en la parte superior.
     * @param speedX velocidad horizontal del Sprite.
     * @param speedY velocidad vertical del Sprite.
     */
    public Sprite(int width, int height, int posX, int posY, int speedX, int speedY, BufferedImage bufferedImage) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.bufferedImage = bufferedImage;
        updateBuffer();
    }

    /**
     * Método para actualizar el buffer que guarda cada Sprite.
     * Por ahora sólo guarda un bufferedImage que está completamente relleno de un color.
     */
    public void updateBuffer() {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();

        try {
            BufferedImage imagenSprite = bufferedImage;
            g.drawImage(imagenSprite.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        } catch (Exception e) {
            g.setColor(color);
            g.fillRect(0, 0, width, height);
            g.dispose();
        }

    }

    /**
     * Método para comprobar si el Sprite colisiona con otro.
     *
     * @param other El otro Sprite con el que se comprueba si hay colisión.
     * @return verdadero si ambos Sprites collision.
     */
    public boolean collision(Sprite other) {
        double thisCenterX = this.getPosX() + (this.getWidth() - 4) / 2;
        double thisCenterY = this.getPosY() + (this.getHeight() - 4) / 2;
        double otherCenterX = other.getPosX() + other.getWidth() / 2;
        double otherCenterY = other.getPosY() + other.getHeight() / 2;

        boolean overlapX = Math.abs(thisCenterX - otherCenterX) <= ((this.getWidth() - 4) + other.getWidth()) / 2;
        boolean overlapY = Math.abs(thisCenterY - otherCenterY) <= ((this.getHeight() - 4) + other.getHeight()) / 2;

        return (overlapX && overlapY);
    }

    /**
     * Método para mover el Sprite por el mundo.
     *
     * @param widthPanel  width del mundo sobre el que se mueve el Sprite
     * @param heightPanel height del mundo sobre el que se mueve el Sprite
     */
    public boolean moveSprite(int widthPanel, int heightPanel) {

        if (posX >= widthPanel - width) { //por la derecha
            speedX = -1 * Math.abs(speedX);
        }
        if (posX <= 20) {//por la izquierda
            speedX = Math.abs(speedX);
        }
        if (posY >= heightPanel - height) {//por abajo
            speedY = -1 * Math.abs(speedY);
        }
        if (posY <= 20) { //Por arriba
            speedY = Math.abs(speedY);
        }
        posX = posX + speedX;
        posY = posY + speedY;

        if (posY > heightPanel - 20) {
            return true;
        }

        return false;
    }

    public void moveSprite() {
        if (speedX > 5) {
            speedX = 5;
        }

        if (speedX < -5) {
            speedX = -5;
        }

        posX = posX + speedX;
    }


    /**
     * Método que pinta el Sprite en el mundo teniendo en cuenta las características propias del Sprite.
     *
     * @param g Es el Graphics del mundo que se utilizará para pintar el Sprite.
     */
    public void paintSpriteInWord(Graphics g) {
        g.drawImage(buffer, (int) Math.round(posX), (int) Math.round(posY), null);
    }

    public double getTotalSpeed() {
        return totalSpeed;
    }

    public void setTotalSpeed(double totalSpeed) {
        this.totalSpeed = totalSpeed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setSpeedX(double speedX) {

        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

}
