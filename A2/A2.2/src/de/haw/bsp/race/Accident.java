package de.haw.bsp.race;

import java.util.Random;

/**
 * @author Anil Ersin Kaya
 * @author Ali Calis
 * @version 0.1
 */

public class Accident implements Runnable {
	private Thread t;
	private String threadName;
	private SimRace sr;
	private int accidentInterval;

	public Accident(String name, SimRace sr, int accidentInterval) {
		this.threadName = name;
		this.accidentInterval = accidentInterval;
		this.sr = sr;
	}

	@Override
	public void run() {
		Random rand = new Random();
		int r = rand.nextInt(accidentInterval);
		try {
			Thread.sleep(r);
		} catch (InterruptedException e) {
			t.interrupt();
		}
		getSr().stopAllCars();
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public SimRace getSr() {
		return sr;
	}

	public void setSr(SimRace sr) {
		this.sr = sr;
	}

	public int getAccidentInterval() {
		return accidentInterval;
	}

	public void setAccidentInterval(int accidentInterval) {
		this.accidentInterval = accidentInterval;
	}

	public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
