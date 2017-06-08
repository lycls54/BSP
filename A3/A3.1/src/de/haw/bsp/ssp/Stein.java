package de.haw.bsp.ssp;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
public class Stein implements SpielObjekte {
	private Spieler spieler;

	public Stein(Spieler spieler) {
		this.spieler = spieler;
	}

	@Override
	public int compareTo(SpielObjekte o) {
		if (o instanceof Schere) {
			return 1;
		} else if (o instanceof Papier) {
			return -1;
		} else {
			return 0;
		}

	}

	public Spieler getSpieler() {
		return spieler;
	}
}