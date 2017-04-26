package de.haw.bsp.race;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class represents the cars in our SimRace.
 * 
 * @author Anil Ersin Kaya
 * @author Ali Calis
 * @version 0.1
 * @see java.lang.Thread
 * @see java.lang.Runnable
 */

public class Car implements Runnable, Comparable<Car> {
	private Thread t;
	private String threadName;
	private int finishTime;
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
			l++;
			Integer i = rand.nextInt(100);
			try {
				Thread.sleep(i);
			} catch (InterruptedException e) {
				t.interrupt();
			}
			addLapDur(l, i);
			setGesFahrZeit(i + getGesFahrZeit());
		}
	}

	public void start(int laps) {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
		this.laps = laps;
	}

	// public void raceDuration() {
	// for (Integer l = 0; l < laps; l++) {
	// Integer i = rand.nextInt(100);
	// addLapDur(l, i);
	// setGesFahrZeit(i + getGesFahrZeit());
	// try {
	// Thread.sleep(i);
	// } catch (InterruptedException e) {

	// e.printStackTrace();
	// }
	// }
	// }

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
		return finishTime;
	}

	public void setGesFahrZeit(int gesFahrZeit) {
		this.finishTime = gesFahrZeit;
	}

	public int getLapDur(Integer key) {
		return lapDur.get(key);
	}

	public void addLapDur(Integer lap, Integer dur) {
		this.lapDur.put(lap, dur);
	}

	@Override
	public String toString() {
		return threadName + ", " + finishTime;
	}

	@Override
	public int compareTo(Car o) {
		if (this.getGesFahrZeit() > o.finishTime) {
			return 1;
		} else if (this.getGesFahrZeit() < o.finishTime) {
			return -1;
		}
		return 0;
	}
}