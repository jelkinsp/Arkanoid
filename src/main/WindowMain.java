package main;

import base.GamePanel;
import base.ScoreHeader;
import sprites.LoadMedia;

import javax.swing.*;
import java.awt.*;

/**
 * Clase WindowMain. En ella se pinta el juego.
 *
 * @author Jose Luis Luengo Ramos
 */
public class WindowMain {

    JFrame window;
    GamePanel gamePanel;
    ScoreHeader scoreHeader;

    public WindowMain() {
        window = new JFrame();
        window.setBounds(100, 50, 632, 884);
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

        LoadMedia loadMedia = new LoadMedia();
        scoreHeader = new ScoreHeader(loadMedia);
        window.add(scoreHeader,setting);

        setting = new GridBagConstraints();
        setting.gridx = 0;
        setting.gridy = 1;
        setting.weightx = 1;
        setting.weighty = 10;
        setting.fill = GridBagConstraints.BOTH;
        gamePanel = new GamePanel(scoreHeader, loadMedia);
        window.add(gamePanel,setting);
    }
}
