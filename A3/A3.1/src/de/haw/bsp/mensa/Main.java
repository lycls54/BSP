package de.haw.bsp.mensa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
public class Main {

	public static void main(String[] args) {
		Mensa mensa = new Mensa(3);
		List<Studenten> stList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Studenten s = new Studenten("Student " + i);
			stList.add(s);
			s.start(mensa);
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		mensa.setClosed(true);
		// while (Thread.activeCount() > 1) {
		// // wait
		// }
		// System.out.println(Thread.activeCount());
	}

}
