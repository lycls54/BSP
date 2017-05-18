package de.haw.bsp.ssp;

import java.util.concurrent.Semaphore;

public class SchiedsrichterThread extends Schiedsrichter implements Runnable {
	private Thread t;

	public Semaphore semaphore = new Semaphore(0);

	public SchiedsrichterThread() {

	}

	public void run() {
		System.out.println("Das spiel geht weiter");
		while (!t.isInterrupted()) {
			// if (getTisch().getDesktop().size() == 2) {
			try {
				semaphore.acquire();
				auswerten();
				getTisch().getDesktop().clear();
			} catch (InterruptedException e) {
				getT().interrupt();
			}

			synchronized (this) {
				notifyAll();
			}
			// }
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void stopDasSpiel(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("stopped");
		((SpielerThread) getTisch().getS1()).getT().interrupt();
		((SpielerThread) getTisch().getS2()).getT().interrupt();

		this.t.interrupt();
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
