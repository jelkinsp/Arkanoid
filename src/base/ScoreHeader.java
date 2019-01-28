package base;


import screen.ScoreScreen;
import sprites.LoadMedia;

import javax.swing.*;
import java.awt.*;

/**
 * Panel con la cabecera
 *
 */
public class ScoreHeader extends JPanel {

    private ScoreScreen screenActual;
    LoadMedia loadMedia;

    public ScoreHeader(LoadMedia loadMedia) {
        this.loadMedia = loadMedia;
        this.screenActual = new ScoreScreen(this);
        this.screenActual.initWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        screenActual.paintWindow(g);
    }

    public LoadMedia getLoadMedia() {
        return loadMedia;
    }

    public ScoreScreen getScreenActual() {
        return screenActual;
    }

}
