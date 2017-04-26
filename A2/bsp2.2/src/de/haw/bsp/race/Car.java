package de.haw.bsp.race;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Car implements Runnable, Comparable<Car> {
	private Thread t;
	private String threadName;
	private int gesFahrZeit;
	private Map<Integer, Integer> lapDur;
	private Random rand;
	private int laps;

	public Car(String name) {
		this.threadName = name;
		this.rand = new Random();
		this.lapDur = new HashMap<>();
	}

	public void run() {
		int l = 0;
		while (!t.isInterrupted() && l != laps) {
			// continue processing
			l++;
			Integer i = rand.nextInt(100);
			addLapDur(l, i);
			setGesFahrZeit(i + getGesFahrZeit());

			try {
				Thread.sleep(i);
			} catch (InterruptedException e) {
				// good practice
				t.interrupt();

			}
		}
	}

	public void start(int laps) {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();

		}
		this.laps = laps;
	}

	public void raceDuration() throws InterruptedException {
		for (Integer l = 0; l < laps; l++) {
			Integer i = rand.nextInt(100);
			addLapDur(l, i);
			setGesFahrZeit(i + getGesFahrZeit());

			Thread.sleep(i);
		}

	}

	public Map<Integer, Integer> getLapDur() {
		return lapDur;
	}

	public void setLapDur(Map<Integer, Integer> lapDur) {
		this.lapDur = lapDur;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
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

	public int getGesFahrZeit() {
		return gesFahrZeit;
	}

	public void setGesFahrZeit(int gesFahrZeit) {
		this.gesFahrZeit = gesFahrZeit;
	}

	public int getLapDur(Integer key) {
		return lapDur.get(key);
	}

	public void addLapDur(Integer lap, Integer dur) {
		this.lapDur.put(lap, dur);
	}

	@Override
	public String toString() {
		return threadName + ", " + gesFahrZeit;
	}

	@Override
	public int compareTo(Car o) {
		if (this.getGesFahrZeit() > o.gesFahrZeit) {
			return 1;
		} else if (this.getGesFahrZeit() < o.gesFahrZeit) {
			return -1;
		}
		return 0;
	}

}