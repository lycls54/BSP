package de.haw.bsp.mensa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Kasse {

	private ReentrantLock semaphore;
	private Queue<Studenten> warteSchlange;
	private String name;

	public Kasse(String name) {
		this.name = name;
		semaphore = new ReentrantLock();
		warteSchlange = new LinkedList<>();
	}

	public void kasseLock() {
		try {
			semaphore.lockInterruptibly();
			// System.out.println("Locks acquired");
			// System.out.println("Locks remaining >> " +
			// semaphore.availablePermits());
		} catch (InterruptedException ie) {
			// ie.printStackTrace();
		}
	}

	public void kasseRelease() {
		try {
			semaphore.unlock();
		} catch (IllegalMonitorStateException e) {

		}
	}

	public String getName() {
		return name;
	}

	public ReentrantLock getSemaphore() {
		return semaphore;
	}

	public synchronized Queue<Studenten> getWarteSchlange() {
		return warteSchlange;
	}

	public synchronized void addZuWarteSchlange(Studenten student) {
		this.warteSchlange.add(student);
	}

	public synchronized Studenten removeFromWarteSchlange() {
		return this.warteSchlange.poll();
	}

}
