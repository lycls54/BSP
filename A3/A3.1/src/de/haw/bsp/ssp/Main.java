package de.haw.bsp.ssp;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
public class Main {

	public static void main(String[] args) {
		SchiedsrichterThread s = new SchiedsrichterThread();
		SpielerThread s1 = new SpielerThread("Spieler 1");
		SpielerThread s2 = new SpielerThread("Spieler 2");
		@SuppressWarnings("unused")
		Tisch t = new Tisch(s, s1, s2);
		s1.start();
		s2.start();
		s.start();
		s.stopDasSpiel(10000);
		s.printErgebniss();
	}
}
