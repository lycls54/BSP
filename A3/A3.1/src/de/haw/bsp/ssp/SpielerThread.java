package de.haw.bsp.ssp;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
public class SpielerThread extends Spieler implements Runnable {

	private Thread t;
	private static final Object lock = new Object();

	public SpielerThread(String name) {
		super(name);
	}

	public void run() {
		while (!t.isInterrupted()) {
			synchronized (getTisch().getS()) {
				getTisch().getDesktop().add((choose()));

				if (getTisch().getDesktop().size() == 2) {
					((SchiedsrichterThread) getTisch().getS()).semaphore.release();
				}

				try {
					synchronized (lock) {

						lock.notifyAll();
					}
					getTisch().getS().wait();

				} catch (InterruptedException e) {
					t.interrupt();
				}
			}
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

	public static Object getLock() {
		return lock;
	}
}
