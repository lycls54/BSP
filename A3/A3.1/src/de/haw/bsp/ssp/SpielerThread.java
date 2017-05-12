package de.haw.bsp.ssp;

public class SpielerThread extends Spieler implements Runnable {

	private Thread t;

	public SpielerThread(String name) {
		super(name);
	}

	public void run() {
		getTisch().getDesktop().add((choose()));
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
