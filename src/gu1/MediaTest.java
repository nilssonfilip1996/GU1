package gu1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class MediaTest<E> implements List<E> {

	public static void main(String[] args) throws IOException {

		File file = new File("Media.txt");
		Scanner s = new Scanner(file);
		ArrayList<String> list = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		while (s.hasNextLine())
			list.add(s.nextLine());
		s.close();

		for (String x : list) {
			System.out.println(x);
		}

		String str;
		String[] values;
		Media media;
		while ((str = reader.readLine()) != null) {
			values = str.split(";");
			//lantagare = new Lantagare( values[ 0 ], values[ 1 ], values[ 2 ] );
			//map.put( values[ 0 ], lantagare );

			System.out.println(str);
		}
	}
}

