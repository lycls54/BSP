package de.haw.bsp.ssp;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */

public class SchiedsrichterThread extends Schiedsrichter implements Runnable {
	private Thread t;

	public void run() {
		System.out.println("Das spiel geht weiter");
		while (!t.isInterrupted()) {
			synchronized (SpielerThread.lock) {
				while (getTisch().getDesktop().size() != 2 && !t.isInterrupted()) {
					try {
						SpielerThread.lock.wait();
					} catch (InterruptedException e) {
						getT().interrupt();
					}
				}
			}
			auswerten();
			getTisch().getDesktop().clear();

			synchronized (this) {
				notifyAll();
			}
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void printErgebniss() {
		System.out.println("Spieler 1 wins " + getErgebnis()[0] + " || Unentschieden " + getErgebnis()[1]
				+ " || Spieler 2 wins " + getErgebnis()[2] + " gesammte Runden Anzahl = "
				+ (getErgebnis()[0] + getErgebnis()[1] + getErgebnis()[2]));
	}

	public Thread getT() {
		return t;
	}

}
