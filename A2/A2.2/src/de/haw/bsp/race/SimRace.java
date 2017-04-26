package de.haw.bsp.race;

import java.util.Arrays;

/**
 * This class simulates a race between objects of the class Car.
 * 
 * @author Anil Ersin Kaya
 * @author Ali Calis
 * @version 0.1
 */

public class SimRace {

	private int carCount;
	private int laps;
	private Car[] cars;
	private boolean raceStoped;

	public SimRace(int cars, int laps) {
		this.carCount = cars;
		this.laps = laps;
		this.cars = new Car[cars];
		this.raceStoped = false;

	}

	public static void main(String[] args) {
		int laps = 10;
		int cars = 5;
		SimRace s = new SimRace(cars, laps);
		Accident a = new Accident("kaza", s, 2000);
		s.prepareCarsAndStartRace();
		a.start();

		// while (Thread.activeCount() > 2) {
		// }

		while (!s.isRaceStoped() && !s.isRaceCompleted()) {
			// wait for car-threads
		}
		if (!a.getT().isInterrupted() && s.isRaceCompleted()) {
			a.getT().interrupt();
			System.out.println("**** Endstand ****");
			s.printResult();
		} else {
			System.out.println("crash");
		}
	}

	public void prepareCarsAndStartRace() {
		for (int i = 0; i < getAutosAnzahl(); i++) {
			getCars()[i] = new Car("Car " + i);
			getCars()[i].start(this.laps);

			// wait for threads to end
			// try {
			// getCars().get(i).getT().join();
			//
			// } catch (Exception e) {
			// System.out.println("Interrupted");
			// }
		}
	}

	public boolean isRaceCompleted() {
		boolean flag = true;
		for (int i = 0; i < getCars().length; i++) {
			if (getCars()[i].getLapDur().entrySet().size() < getRunden()) {
				flag = false;
			}
		}
		return flag;
	}

	public void printResult() {
		sortCars();
		for (int i = 0; i < getCars().length; i++) {
			System.out.println(
					(i + 1) + ". Platz: " + getCars()[i].getThreadName() + " Zeit: " + getCars()[i].getGesFahrZeit());
		}
	}

	public void stopAllCars() {
		for (int i = 0; i < getCars().length; i++) {
			getCars()[i].getT().interrupt();
		}
		setRaceStoped(true);
	}

	public boolean isRaceStoped() {
		return raceStoped;
	}

	public void setRaceStoped(boolean raceStoped) {
		this.raceStoped = raceStoped;
	}

	public void setAutosAnzahl(int autosAnzahl) {
		this.carCount = autosAnzahl;
	}

	public void setRunden(int runden) {
		this.laps = runden;
	}

	public void setCars(Car[] cars) {
		this.cars = cars;
	}

	public int getAutosAnzahl() {
		return this.carCount;
	}

	public int getRunden() {
		return this.laps;
	}

	public Car[] getCars() {
		return this.cars;
	}

	public void sortCars() {
		Arrays.sort(this.cars);
	}

}
