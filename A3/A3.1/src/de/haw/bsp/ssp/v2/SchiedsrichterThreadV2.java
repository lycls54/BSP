package de.haw.bsp.ssp.v2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.haw.bsp.ssp.model.Schiedsrichter;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.2
 */

public class SchiedsrichterThreadV2 extends Schiedsrichter implements Runnable {
	private Thread t;
	Lock lockObj = new ReentrantLock();
	final Condition condSchiedsrichter = lockObj.newCondition();
	final Condition condSpieler = lockObj.newCondition();

	@Override
	public void run() {

		System.out.println("Das spiel geht weiter");
		while (!t.isInterrupted()) {
			lockObj.lock();
			try {
				while (getTisch().getDesktop().size() != 2) {
					condSchiedsrichter.await();
				}
				auswerten();
				getTisch().getDesktop().clear();
				// do it
				condSpieler.signalAll();
			} catch (InterruptedException e) {
				getT().interrupt();
			}
			lockObj.unlock();
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void stopDasSpiel(long l) {

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