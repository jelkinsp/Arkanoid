package screen;

import base.ScoreHeader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class ScoreScreen implements IScreen {

    BufferedImage bufferedImage;
    //    Image imageScaling;
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

        this.mainFont = this.scoreHeader.getLoadMedia().getMainFont().deriveFont(Font.BOLD, 32);
    }

    @Override
    public void paintWindow(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, this.scoreHeader.getWidth(), this.scoreHeader.getHeight());
        g.setFont(mainFont);
        g.setColor(Color.RED);
        g.drawString("1UP  HIGH SCORE", 50, 35);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(score), 70, 70);
        g.drawString(String.valueOf(highScore), 320, 70);
        g.dispose();
    }

    @Override
    public void executeFrame() {
        if (score >= highScore) {
            highScore = score;
        }
    }


    @Override
    public void moveMouse(MouseEvent e) {

    }

    @Override
    public void clickMouse(MouseEvent e) {

    }

    @Override
    public void resizeScreen(ComponentEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;

    }


    public void addPoint() {
        score += 100;
    }

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

    public void writeFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("saveScore/highScore.txt"))) {
            writer.println(this.highScore);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}