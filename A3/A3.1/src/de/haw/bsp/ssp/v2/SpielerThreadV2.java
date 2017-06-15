package de.haw.bsp.ssp.v2;

import de.haw.bsp.ssp.model.Spieler;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.2
 */

public class SpielerThreadV2 extends Spieler implements Runnable {

	private Thread t;

	public SpielerThreadV2(String name) {
		super(name);
	}

	@Override
	public void run() {
		while (!t.isInterrupted()) {
			((SchiedsrichterThreadV2) getTisch().getS()).lockObj.lock();

			try {
				getTisch().getDesktop().add((choose()));
				((SchiedsrichterThreadV2) getTisch().getS()).condSchiedsrichter.signal();
				((SchiedsrichterThreadV2) getTisch().getS()).condSpieler.await();
			} catch (InterruptedException e) {
				getT().interrupt();
			}
			((SchiedsrichterThreadV2) getTisch().getS()).lockObj.unlock();

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

}