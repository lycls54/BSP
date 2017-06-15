package de.haw.bsp.ssp.model;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
public class Schiedsrichter {
	private Tisch tisch;
	private int[] ergebnis;

	public Schiedsrichter() {
		ergebnis = new int[3];
	}

	public void auswerten() {
		if (tisch.getDesktop().size() != 2) {
			// System.out.println(ergebnis[0] + ergebnis[1] + ergebnis[2] + 1 +
			// ". runde ist nicht auswertbar");
			// System.out.println(2 - (tisch.getDesktop().size()) + " spieler
			// haben nicht gespielt \n");
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
		System.out.println(desktop.get(0) + "" + desktop.get(1) + " winner = " + winner + desktop.get(0).getSpieler()
				+ "" + desktop.get(1).getSpieler() + " winner = " + winner);
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
