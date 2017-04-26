package de.haw.bsp.race;

import java.util.Random;

public class Accident implements Runnable {
	private Thread t;
	private String threadName;

	public Accident(String name) {
		this.threadName = name;
	}

	@Override
	public void run() {
		Random rand = new Random();
		int r = rand.nextInt(2000);
		try {

			Thread.sleep(r);
			System.out.println("crash");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Kein Unfall passiert.");
			t.interrupt();
		}
		Thread[] t = new Thread[Thread.activeCount()];
		Thread.enumerate(t);
		for (int i = 0; i < t.length; i++) {
			if (!t[i].getName().equals("main") && !t[i].getName().equals(threadName)) {
				t[i].interrupt();
			}
		}
		if (!this.t.isInterrupted()) {
			System.exit(0);
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
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
