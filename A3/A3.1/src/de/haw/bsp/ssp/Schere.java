package de.haw.bsp.ssp;

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

}