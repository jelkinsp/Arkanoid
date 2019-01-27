package screen;

import base.GamePanel;
import base.ScoreHeader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Jose Luis Luengo Ramos
 */
public class InitScreen implements IScreen {

    GamePanel gamePanel;

    BufferedImage bufferedImage;
    Image imageScalingStart;
    Font initFont;
    //Inicio pantalla
    Color textColor = Color.YELLOW;
    private ScoreHeader scoreHeader;


    public InitScreen(GamePanel gamePanel/*, ScoreHeader scoreHeader*/) {

        this.gamePanel = gamePanel;
    }


    public void initWindow() {
        try {
            bufferedImage = ImageIO.read(new File("image/FondoInicio3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initFont = new Font("Arial", Font.BOLD, 20);
    }


    public void paintWindow(Graphics g) {
        g.drawImage(imageScalingStart, 0, 0, null);
        g.setColor(textColor);
        g.setFont(initFont);
        g.drawString("ASTEROIDES: CLIC PARA EMPEZAR", gamePanel.getWidth() / 2 - 160, gamePanel.getHeight() / 2 - 15);

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
        GameScreen gameScreen = new GameScreen(gamePanel);
        gameScreen.initWindow();
        gamePanel.setScreenActual(gameScreen);

    }


    public void resizeScreen(ComponentEvent e) {
        imageScalingStart = bufferedImage.getScaledInstance(gamePanel.getWidth(), gamePanel.getHeight(), Image.SCALE_SMOOTH);


    }

    public void keyPressed(KeyEvent e) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }

}
