package de.haw.bsp.ssp.v2;

import java.util.concurrent.Semaphore;

import de.haw.bsp.ssp.Schiedsrichter;
import de.haw.bsp.ssp.Spieler;

/**
 * @author Crunchify.com
 * 
 */

public class ConsumerProducer {

	static Semaphore semaphore = new Semaphore(0);
	static Semaphore mutex = new Semaphore(2);

	static class SpielerThreadV2 extends Spieler implements Runnable {

		private Thread t;

		public SpielerThreadV2(String name) {
			super(name);
		}

		public void run() {

			try {
				while (!t.isInterrupted()) {
					mutex.acquire();

					getTisch().getDesktop().add(choose());

					mutex.release();

					// release lock
					if (getTisch().getDesktop().size() >= 2) {
						semaphore.release();
					}
					Thread.sleep(500);
				}
			} catch (Exception x) {
				t.interrupt();
			}

		}

		public void start() {
			if (t == null) {
				t = new Thread(this, getName());
				t.start();
			}
		}

		public Thread getT() {
			return t;
		}

		public void setT(Thread t) {
			this.t = t;
		}
	}

	static class SchiedsrichterThreadV2 extends Schiedsrichter implements Runnable {
		private Thread t;

		public void run() {
			System.out.println("Das Spiel geht weiter");
			try {
				while (!t.isInterrupted()) {

					semaphore.acquire();
	
					mutex.acquire();

					auswerten();
					getTisch().getDesktop().clear();
					mutex.release();
				}
			} catch (Exception e) {
				t.interrupt();
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
			t.interrupt();
			}
			((SpielerThreadV2) getTisch().getS1()).getT().interrupt();
			((SpielerThreadV2) getTisch().getS2()).getT().interrupt();

			this.t.interrupt();

			System.out.println("Stopped");
		}

		public void printErgebniss() {
			System.out.println("Spieler 1 wins " + getErgebnis()[0] + " || Unentschieden " + getErgebnis()[1]
					+ " || Spieler 2 wins " + getErgebnis()[2]);
		}

		public Thread getT() {
			return t;
		}

		public void setT(Thread t) {
			this.t = t;
		}
	}

}