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
 * Pantalla de juego perdido
 *
 * @author Jose Luis Luengo Ramos
 */
public class GameOverScreen implements IScreen {
    GamePanel gamePanel;
    Font initFont;
    Color textColor = Color.WHITE;
    public GameOverScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void initWindow() {
        initFont = this.gamePanel.getLoadMedia().getMainFont().deriveFont(Font.PLAIN, 18);
    }

    @Override
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


    @Override
    public void executeFrame() {
    }


    @Override
    public void clickMouse(MouseEvent e) {
        InitScreen initScreen = new InitScreen(gamePanel);
        initScreen.initWindow();
        gamePanel.setScreenActual(initScreen);

    }

    @Override
    public void resizeScreen(ComponentEvent e) {
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }

}
