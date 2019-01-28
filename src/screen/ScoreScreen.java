package screen;

import base.ScoreHeader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Pantalla del panel de la cabecera para los highScore
 *
 */
public class ScoreScreen implements IScreen {

    private int highScore;
    private int score;
    private Font mainFont;
    private ScoreHeader scoreHeader;

    public ScoreScreen(ScoreHeader scoreHeader) {
        this.highScore = 0;
        this.score = 0;
        this.scoreHeader = scoreHeader;
    }

    @Override
    public void initWindow() {

        score = 0;

        this.mainFont = this.scoreHeader.getLoadMedia().getMainFont().deriveFont(Font.BOLD, 28);
    }

    @Override
    public void paintWindow(Graphics g) {
        FontMetrics metrics = g.getFontMetrics(mainFont);
        String word;
        int x;
        g.setFont(mainFont);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, this.scoreHeader.getWidth(), this.scoreHeader.getHeight());
        g.setColor(Color.RED);
        word = "1UP  HIGH SCORE";
        x = scoreHeader.getX() + (scoreHeader.getWidth() - metrics.stringWidth(word)) / 2;
        g.drawString(word, x - 50, 35);
        g.setColor(Color.WHITE);
        word = String.valueOf(score);
        x = scoreHeader.getX() + (scoreHeader.getWidth() - metrics.stringWidth(word)) / 2;
        g.drawString(word, x - 220, 70);
        word = String.valueOf(highScore);
        x = scoreHeader.getX() + (scoreHeader.getWidth() - metrics.stringWidth(word)) / 2;
        g.drawString(word, x + 30, 70);
        g.dispose();
    }

    @Override
    public void executeFrame() {
        if (score >= highScore) {
            highScore = score;
        }
    }

    @Override
    public void clickMouse(MouseEvent e) {
    }

    @Override
    public void resizeScreen(ComponentEvent e) {
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }

    /**
     * Puntucion por bloque destruido
     */
    public void addPoint() {
        score += 100;
    }

    /**
     * Metodo para leer el fichero con la punticion mas alta.
     *
     */
    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("saveScore/highScore.txt"))) {
            String aux = reader.readLine();
            this.highScore = Integer.valueOf(aux);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para guarda la puntiacion mas alta.
     *
     */
    public void writeFile() {
        if (this.highScore <= this.score) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("saveScore/highScore.txt"))) {
                writer.println(this.score);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * SETTER
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

}