package gu1;

import java.io.IOException;
import javax.swing.SwingUtilities;

public class MainLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller("Media.txt", "Lantagare.txt");
				} catch (IOException e) {
					System.out.print(e);
				}
			}
		});
	}
}
