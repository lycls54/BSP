package de.haw.bsp.ssp.model;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
public class Schere implements SpielObjekte {
	Spieler spieler;

	public Schere(Spieler spieler) {
		this.spieler = spieler;
	}

	@Override
	public int compareTo(SpielObjekte o) {
		if (o instanceof Papier) {
			return 1;
		} else if (o instanceof Stein) {
			return -1;
		} else {
			return 0;
		}

	}

	public Spieler getSpieler() {
		return spieler;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Schere";
	}

}