package gu1;

import javax.swing.SwingUtilities;

public class MainLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller("Media.txt", "Lantagare.txt");
			}
		});
	}
}

