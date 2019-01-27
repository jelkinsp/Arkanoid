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
    private Font font;
    private ScoreHeader scoreHeader;

    public ScoreScreen(ScoreHeader scoreHeader) {
        this.highScore = 0;
        this.score = 0;
        this.scoreHeader = scoreHeader;
    }

    @Override
    public void initWindow() {
        try {
            ImageIO.read(new File("image/blackbackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        score = 0;
        font = new Font("Arial", Font.BOLD, 32);
    }

    @Override
    public void paintWindow(Graphics g) {
//        g.drawImage(bufferedImage,0,20,null);
//        g.drawRect(0,0,this.scoreHeader.getWidth(),this.scoreHeader.getHeight());
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,this.scoreHeader.getWidth(),this.scoreHeader.getHeight());
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("1UP          HIGH SCORE",this.scoreHeader.getWidth()/5, 30);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(score),this.scoreHeader.getWidth()/5, 60);
        g.drawString("                   "+highScore,this.scoreHeader.getWidth()/5, 60);
        g.dispose();
    }

    @Override
    public void executeFrame() {
        if (score >= highScore){
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


    public void addPoint(){
        score+=100;
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