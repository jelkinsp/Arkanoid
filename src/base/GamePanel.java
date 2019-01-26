package base;

import screen.IScreen;
import screen.InitScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * @author Jose Luis Luengo Ramos
 * Clase GamePanel. Controla los gráficos del Juego. Por ahora también controla la lógica del Juego.
 * Extiende de JPanel. Todos los gráficos se gestionan mediante los gráficos de un JPanel.
 * Implementa Runnable porque en el constructor se lanza un hilo que permite actualizar el Juego periódicamente.
 * Implementa MouseListener para que pueda capturar las pulsaciones del ratón.
 */
public class GamePanel extends JPanel implements Runnable, MouseListener, ComponentListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    IScreen IScreenActual;


    /**
     * Constructor de GamePanel.
     * - Inicializa el arrayList de cuadrados.
     * - Asigna el mouse listener que implementa la propia clase para lanazar nuevos cuadrados.
     * - Inicia un hilo para actualizar el juego periódicamente.
     */
    public GamePanel() {

        this.addMouseListener(this);
        this.addComponentListener(this);
        this.addMouseMotionListener(this);
        new Thread(this).start();

        IScreenActual = new InitScreen(this);
        IScreenActual.initWindow();

    }


    public IScreen getIScreenActual() {
        return IScreenActual;
    }


    public void setIScreenActual(IScreen IScreenActual) {
        this.IScreenActual = IScreenActual;
    }


    /**
     * Sobreescritura del método paintComponent. Este método se llama automáticamente cuando se inicia el componente,
     * se redimensiona o bien cuando se llama al método "repaint()". Nunca llamarlo directamente.
     *
     * @param g Es un Graphics que nos proveé JPanel para poner pintar el componente a nuestro antojo.
     */

    public void paintComponent(Graphics g) {
        IScreenActual.paintWindow(g);
    }

    public void run() {

        while (true) {


            try {
                Thread.sleep(16);
//                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            IScreenActual.executeFrame();
            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent e) {
        IScreenActual.clickMouse(e);
    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void componentResized(ComponentEvent e) {
        IScreenActual.resizeScreen(e);
    }

    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseMoved(MouseEvent e) {
        IScreenActual.moveMouse(e);
    }
}










