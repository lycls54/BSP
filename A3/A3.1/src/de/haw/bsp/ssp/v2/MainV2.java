package de.haw.bsp.ssp.v2;

import de.haw.bsp.ssp.model.Tisch;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.2
 */

public class MainV2 {

	public static void main(String[] args) {
		SpielerThreadV2 cp = new SpielerThreadV2("Spieler 1");
		SpielerThreadV2 cp2 = new SpielerThreadV2("Spieler 2");
		SchiedsrichterThreadV2 cc = new SchiedsrichterThreadV2();
		@SuppressWarnings("unused")
		Tisch t = new Tisch(cc, cp, cp2);
		cp.start();
		cp2.start();
		cc.start();

		// stop the game
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			cc.getT().interrupt();
		}
		cp.getT().interrupt();
		cp2.getT().interrupt();
		cc.getT().interrupt();
		System.out.println("Stopped");
		//
		cc.printErgebniss();
	}

}