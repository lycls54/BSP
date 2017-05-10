package de.haw.bsp.mensa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Kasse {

	private Semaphore semaphore;
	private Queue<Studenten> warteSchlange;
	private String name;

	public Kasse(String name) {
		this.name = name;
		semaphore = new Semaphore(1);
		warteSchlange = new LinkedList<>();
	}

	public void kasseLock() {
		try {
			semaphore.acquire();
			// System.out.println("Locks acquired");
			// System.out.println("Locks remaining >> " +
			// semaphore.availablePermits());
		} catch (InterruptedException ie) {
			// ie.printStackTrace();
		}
	}

	public void kasseRelease() {
		semaphore.release();
	}

	public String getName() {
		return name;
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public Queue<Studenten> getWarteSchlange() {
		return warteSchlange;
	}

	public void addWarteSchlange(Studenten student) {
		this.warteSchlange.add(student);
	}

}
