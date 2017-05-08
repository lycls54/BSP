package de.haw.bsp.mensa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Mensa mensa = new Mensa(3);
		List<Studenten> stList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			stList.add(new Studenten("Student " + i));
			stList.get(i).start(mensa);
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		for (Iterator<Studenten> iterator = stList.iterator(); iterator.hasNext();) {
			Studenten studenten = (Studenten) iterator.next();
			studenten.getT().interrupt();
		}

	}

}
