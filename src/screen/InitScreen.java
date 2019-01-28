package screen;

import base.GamePanel;
import sprites.LoadMedia;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Pantalla principal, aqui se pintan los titulos, los iconos y los texto.
 *
 * @author Jose Luis Luengo Ramos
 */
public class InitScreen implements IScreen {
//    @FXML
//    MediaView mediaView;

    GamePanel gamePanel;
    Font initFont;
    Color textColor = Color.WHITE;

    public InitScreen(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }


    public void initWindow() {
        initFont = this.gamePanel.getLoadMedia().getMainFont().deriveFont(Font.PLAIN, 18);
    }


    public void paintWindow(Graphics g) {
        this.gamePanel.getScoreHeader().getScreenActual().setScore(0);
        BufferedImage title = gamePanel.getLoadMedia().getSpritesInitSubBuffer()[0];
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
        g.drawImage(
                title,
                (gamePanel.getWidth() / 2) - ((LoadMedia.TITLE_WIDTH + 325) / 2),
                gamePanel.getHeight() / 8,
                Math.round(title.getWidth() * 2.5f),
                Math.round(title.getHeight() * 2.5f),
                null);
        g.setColor(textColor);
        aligmentText(g);

    }

    /**
     * Añade y alinea al centro los textos del drawString.
     *
     * @param g
     */
    private void aligmentText(Graphics g) {
        FontMetrics metrics = g.getFontMetrics(initFont);
        BufferedImage icon = gamePanel.getLoadMedia().getSpritesInitSubBuffer()[1];
        String word;
        int x;
        int aux;
//        int y;
        g.setFont(initFont);
        word = "PUSH";
        x = gamePanel.getX() + (gamePanel.getWidth() - metrics.stringWidth(word)) / 2;
        aux = x;
//        y = gamePanel.getY() + ((gamePanel.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(word, x, 300);
        word = "ONLY 1 PLAYER BUTTON";
        x = gamePanel.getX() + (gamePanel.getWidth() - metrics.stringWidth(word)) / 2;
        g.drawString(word, x, 400);
        g.drawImage(icon,
                aux - 46,
                600,
                icon.getWidth() * 2,
                icon.getHeight() * 2,
                null);
        word = "© 1986 TAITO CORP JAPAN";
        x = gamePanel.getX() + (gamePanel.getWidth() - metrics.stringWidth(word)) / 2;
        g.drawString(word, x, 700);
        word = "ALL RIGHT RESERVED";
        x = gamePanel.getX() + (gamePanel.getWidth() - metrics.stringWidth(word)) / 2;
        g.drawString(word, x, 730);

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
        GameScreen gameScreen = new GameScreen(gamePanel);
        gameScreen.initWindow();
        gamePanel.setScreenActual(gameScreen);

    }
// Reproducir un video
//    public void playMovie() {
//
//        String url = "media/initGameVideo.mp4";
//        Media media = null;
//            media = new Media(new File(url).getPath());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaView.setFitWidth(gamePanel.getWidth());
//        mediaView.setFitHeight(gamePanel.getHeight());
////        mediaView.setFitWidth(600);
////        mediaView.setFitHeight(600);
//        mediaView.setMediaPlayer(mediaPlayer);
//        mediaPlayer.play();
//
//
//    }

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
