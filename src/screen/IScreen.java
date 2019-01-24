package screen;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

/**
 *
 *  @author Jose Luis Luengo Ramos
 */
public interface IScreen {
	void initWindow();
	void paintWindow(Graphics g);
	void executeFrame();
	void moveMouse(MouseEvent e);
	void clickMouse(MouseEvent e);
	void resizeScreen(ComponentEvent e);
	
}
