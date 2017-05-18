package de.haw.bsp.ssp;

public class SpielerThread extends Spieler implements Runnable {

	private Thread t;

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

	public void setT(Thread t) {
		this.t = t;
	}
}
