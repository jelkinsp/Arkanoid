package main;

import java.awt.*;

/**
 *
 * Clase principal
 */
public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				WindowMain windowMain = new WindowMain();
				windowMain.initialize();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});	
	}
}
