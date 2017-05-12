package de.haw.bsp.ssp;

public class Schiedsrichter {
	private Tisch tisch;
	private int[] ergebnis;

	public Schiedsrichter() {
		ergebnis = new int[3];
	}

	public void auswerten() {
		if (tisch.getDesktop().size() != 2) {
			return;
		}
		Spieler winner = winner();
		if (winner == tisch.getS1()) {
			ergebnis[0]++;
		} else if (winner == tisch.getS2()) {
			ergebnis[2]++;
		} else {
			ergebnis[1]++;
		}

	}

	public Spieler winner() {
		Desktop<SpielObjekte> desktop = getTisch().getDesktop();
		int win = desktop.get(0).compareTo(desktop.get(1));
		Spieler winner = null;
		if (win == 1) {
			winner = desktop.get(0).getSpieler();
		} else if (win == -1) {
			winner = desktop.get(1).getSpieler();
		} else {
		}
		return winner;
	}

	public Tisch getTisch() {
		return tisch;
	}

	public void setTisch(Tisch t) {
		this.tisch = t;
	}

	public int[] getErgebnis() {
		return ergebnis;
	}

}
