package de.haw.bsp.ssp.v2;

import de.haw.bsp.ssp.Tisch;
import de.haw.bsp.ssp.v2.ConsumerProducer.SpielerThreadV2;
import de.haw.bsp.ssp.v2.ConsumerProducer.SchiedsrichterThreadV2;

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
		cc.stopDasSpiel(10000);
		cc.printErgebniss();
	}

}
