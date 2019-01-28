package base;

import screen.IScreen;
import screen.InitScreen;
import sprites.LoadMedia;

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
public class GamePanel extends JPanel implements Runnable, MouseListener, ComponentListener, MouseMotionListener, KeyListener{

    private static final long serialVersionUID = 1L;
    IScreen screenActual;
    ScoreHeader scoreHeader;
    LoadMedia loadMedia;



    /**
     * Constructor de GamePanel.
     * - Inicializa el arrayList de cuadrados.
     * - Asigna el mouse listener que implementa la propia clase para lanazar nuevos cuadrados.
     * - Inicia un hilo para actualizar el juego periódicamente.
     * @param scoreHeader
     */
    public GamePanel(ScoreHeader scoreHeader, LoadMedia loadMedia) {
        this.loadMedia = loadMedia;

        this.addMouseListener(this);
        this.addComponentListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        new Thread(this).start();
        this.scoreHeader = scoreHeader;

        this.setFocusable(true);

        this.screenActual = new InitScreen(this);
        this.screenActual.initWindow();
    }

    public void setScreenActual(IScreen screenActual) {
        this.screenActual = screenActual;
    }


    /**
     * Sobreescritura del método paintComponent. Este método se llama automáticamente cuando se inicia el componente,
     * se redimensiona o bien cuando se llama al método "repaint()". Nunca llamarlo directamente.
     *
     * @param g Es un Graphics que nos proveé JPanel para poner pintar el componente a nuestro antojo.
     */

    public void paintComponent(Graphics g) {
        screenActual.paintWindow(g);
        this.scoreHeader.repaint();
    }

    public void run() {
            scoreHeader.getScreenActual().readFile();
        while (true) {


            try {
                Thread.sleep(10);
//                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            screenActual.executeFrame();

            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent e) {
        screenActual.clickMouse(e);
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
        screenActual.resizeScreen(e);
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
//        screenActual.moveMouse(e);
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        this.screenActual.dispatchKeyEvent(e);
    }

    public void keyReleased(KeyEvent e) {
        this.screenActual.dispatchKeyEvent(e);

    }

    public LoadMedia getLoadMedia() {
        return loadMedia;
    }

    public void setLoadMedia(LoadMedia loadMedia) {
        this.loadMedia = loadMedia;
    }

    public ScoreHeader getScoreHeader() {
        return scoreHeader;
    }


}










