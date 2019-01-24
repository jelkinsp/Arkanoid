package main;

import base.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Clase WindowMain. En ella se pinta el juego.
 *
 * @author Jose Luis Luengo Ramos
 */
public class WindowMain {


    //Sigo teniendo la window
    JFrame window;
    GamePanel gamePanel;


    public WindowMain() {
        window = new JFrame();
        window.setBounds(100, 50, 637, 684);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    /**
     * Método que realiza todas las llamadas necesarias para initialize la window correctamente.
     */
    public void initialize() {
        window.setVisible(true);
        initializeComponents();
    }

    /**
     * Método que inicializa todos los componentes de la window
     */
    public void initializeComponents() {
        window.setLayout(new GridLayout(1, 1));
        gamePanel = new GamePanel();
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        window.add(gamePanel);
    }
}
