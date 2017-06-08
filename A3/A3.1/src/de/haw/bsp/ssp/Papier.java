package de.haw.bsp.ssp;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
public class Papier implements SpielObjekte {
	private Spieler spieler;

	public Papier(Spieler s) {
		this.spieler = s;
	}

	@Override
	public int compareTo(SpielObjekte o) {
		if (o instanceof Stein) {
			return 1;
		} else if (o instanceof Schere) {
			return -1;
		} else {
			return 0;
		}

	}

	public Spieler getSpieler() {
		return spieler;
	}

}
