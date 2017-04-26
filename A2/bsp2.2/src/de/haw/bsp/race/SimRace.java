package de.haw.bsp.race;

import java.util.Arrays;

public class SimRace {

	private int autosAnzahl;
	private int runden;
	private Car[] cars;

	public SimRace(int autosAnzahl, int runden) {
		this.autosAnzahl = autosAnzahl;
		this.runden = runden;
		this.cars = new Car[autosAnzahl];

	}
	public static void main(String[] args) {
		int laps = 10;
		int cars = 5;
		Accident a = new Accident("kaza");
		a.start();
		SimRace s = new SimRace(cars, laps);
		s.prepareCarsAndStartRace();
		while (Thread.activeCount() > 2) {
			// wait for car-threads
		}
		a.getT().interrupt();
		s.printResult();
	}

	public void prepareCarsAndStartRace() {
		for (int i = 0; i < getAutosAnzahl(); i++) {
			getCars()[i] = new Car("Car " + i);
			getCars()[i].start(this.runden);

			// wait for threads to end
			// try {
			// getCars().get(i).getT().join();
			//
			// } catch (Exception e) {
			// System.out.println("Interrupted");
			// }
		}
	}

	public void printResult() {
		sortCars();
		for (int i = 0; i < getCars().length; i++) {
			System.out.println(
					(i + 1) + ". Platz: " + getCars()[i].getThreadName() + " Zeit: " + getCars()[i].getGesFahrZeit());
		}
	}

	public void stopAllCars() {

	}

	public void setAutosAnzahl(int autosAnzahl) {
		this.autosAnzahl = autosAnzahl;
	}

	public void setRunden(int runden) {
		this.runden = runden;
	}

	public void setCars(Car[] cars) {
		this.cars = cars;
	}

	public int getAutosAnzahl() {
		return this.autosAnzahl;
	}

	public int getRunden() {
		return this.runden;
	}

	public Car[] getCars() {
		return this.cars;
	}

	public void sortCars() {
		Arrays.sort(this.cars);
	}

}
