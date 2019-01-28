package screen;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Interfaz que implementa los metodos prinpiales para el control de las pantallas.
 *
 * @author Jose Luis Luengo Ramos
 */
public interface IScreen {
    /**
     * Inicial la ventana
     */
    void initWindow();

    /**
     * Pinta los componenetes en la ventana
     *
     * @param g componentes grafico
     */
    void paintWindow(Graphics g);

    /**
     * Aqui se ejecutan las comprobaciones de las distintas pantallas
     */
    void executeFrame();

    /**
     * Metodo para realizar alguna accion segun se pulse el click del raton.
     *
     * @param e evento del raton
     */
    void clickMouse(MouseEvent e);

    /**
     * Metodo para redimensionar la pantalla
     *
     * @param e
     */
    void resizeScreen(ComponentEvent e);

    /**
     * Metodo para escuchar las acciones del teclado.
     *
     * @param e
     * @return
     */
    boolean dispatchKeyEvent(KeyEvent e);


}
