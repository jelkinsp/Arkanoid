package screen;

import base.GamePanel;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import sprites.LoadMedia;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * @author Jose Luis Luengo Ramos
 */
public class GameOverScreen implements IScreen {
    @FXML
    MediaView mediaView;

    GamePanel gamePanel;
    Font initFont;
    Color textColor = Color.WHITE;

    public GameOverScreen(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }


    public void initWindow() {
        initFont = this.gamePanel.getLoadMedia().getMainFont().deriveFont(Font.PLAIN, 18);
    }


    public void paintWindow(Graphics g) {
        BufferedImage gameover = gamePanel.getLoadMedia().getSpritesInitSubBuffer()[2];
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
        g.drawImage(
                gameover,
                (gamePanel.getWidth() / 2) - (Math.round(LoadMedia.CONTINUE_WIDTH * 2.5f) / 2)-15,
                gamePanel.getHeight() / 8,
                Math.round(gameover.getWidth() * 2.5f),
                Math.round(gameover.getHeight() * 2.5f),
                null);
        g.setColor(textColor);
        BufferedImage question = gamePanel.getLoadMedia().getSpritesInitSubBuffer()[3];
        g.drawImage(
                question,
                (gamePanel.getWidth() / 2) - (Math.round(LoadMedia.CONTINUE_WIDTH * 2.5f) / 2)-15,
                gamePanel.getHeight() / 5,
                Math.round(gameover.getWidth() * 2.5f),
                Math.round(gameover.getHeight() * 2.5f),
                null);
        gamePanel.getScoreHeader().getScreenActual().writeFile();
    }


    public void executeFrame() {
//        if (contadorColorFrames % CAMBIO_COLOR_INICIO == 0) {
//
//            if (textColor.equals(Color.YELLOW)) {
//                textColor = Color.RED;
//            } else {
//                textColor = Color.YELLOW;
//            }
//        }

    }


    public void moveMouse(MouseEvent e) {

    }


    public void clickMouse(MouseEvent e) {

//        playMovie();


        InitScreen initScreen = new InitScreen(gamePanel);
        initScreen.initWindow();
        gamePanel.setScreenActual(initScreen);

    }

    public void playMovie() {

//        String url = "initGameVideo.mp4";
        String url = "media/initGameVideo.mp4";
        Media media = null;
        media = new Media(new File(url).getPath());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setFitWidth(gamePanel.getWidth());
        mediaView.setFitHeight(gamePanel.getHeight());
//        mediaView.setFitWidth(600);
//        mediaView.setFitHeight(600);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();


    }

    public void resizeScreen(ComponentEvent e) {
//        imageScalingStart = bufferedImage.getScaledInstance(gamePanel.getWidth(), gamePanel.getHeight(), Image.SCALE_SMOOTH);


    }

    public void keyPressed(KeyEvent e) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }

}
