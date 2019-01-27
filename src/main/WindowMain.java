package main;

import base.GamePanel;
import base.ScoreHeader;

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
    ScoreHeader scoreHeader;


    public WindowMain() {
        window = new JFrame();
        window.setBounds(100, 50, 632, 684);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

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
        window.setLayout(new GridBagLayout());
        GridBagConstraints setting = new GridBagConstraints();

        setting.gridx = 0;
        setting.gridy = 0;
        setting.weightx = 1;
        setting.weighty = 1;
        setting.fill = GridBagConstraints.BOTH;

        scoreHeader = new ScoreHeader();
//        scoreHeader.setBackground(Color.BLACK);
        window.add(scoreHeader,setting);

        setting = new GridBagConstraints();
        setting.gridx = 0;
        setting.gridy = 1;
        setting.weightx = 1;
        setting.weighty = 10;
        setting.fill = GridBagConstraints.BOTH;
        gamePanel = new GamePanel(scoreHeader);
        window.add(gamePanel,setting);
    }
}
