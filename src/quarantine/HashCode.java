package quarantine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class HashCode {

	public static void main(String[] args) throws IOException {

		Path path = Paths.get(System.getProperty("user.dir")).resolve("lantagare.txt");
		BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));

		Object Input = (JOptionPane.showInputDialog(null, "Skriv in ett namn eller personnummer"));

		Map<String, Integer> nameAndNumber = new HashMap<>();
		String line = reader.readLine();
		while (line != null) {
			System.out.println("Processeserade rader: " + line);
			if (!line.trim().equals(" ")) {
				String[] parts = line.split(";");

				for (String info : parts) {
					if (info == null || info.trim().equals("")) {
						continue;
					}
					Integer value = 0;

					if (info.equals(Input)) {
						nameAndNumber.put(info, 1);

					} else {
						nameAndNumber.put(info, value);

					}

				}
			}

			line = reader.readLine();
		}
		System.out.println(nameAndNumber);

		for (String info : nameAndNumber.keySet()) {
			if (nameAndNumber.get(Input) == 1) {
				JOptionPane.showConfirmDialog(null, "Namnet finns");
				break;
			} else {

			}
		}
	}
}
