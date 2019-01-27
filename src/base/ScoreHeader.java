package base;


import screen.ScoreScreen;

import javax.swing.*;
import java.awt.*;

public class ScoreHeader extends JPanel {

    private ScoreScreen screenActual;

    public ScoreHeader() {
        this.screenActual = new ScoreScreen(this);
        this.screenActual.initWindow();
    }

    public void paintComponent(Graphics g) {
        screenActual.paintWindow(g);
    }

    public ScoreScreen getScreenActual() {
        return screenActual;
    }

    public void setScreenActual(ScoreScreen screenActual) {
        this.screenActual = screenActual;
    }
}
