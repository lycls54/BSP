package de.haw.bsp.mensa;

import java.util.Comparator;
import java.util.Random;

public class Studenten implements Runnable {
	private String name;
	private Thread t;
	private Mensa m;
	private boolean anDerKasse;
	private Random r;

	public Studenten(String name) {
		this.name = name;
		r = new Random();
		anDerKasse = false;
	}

	public void run() {
		Kasse kasse = new Kasse("");
		while (!t.isInterrupted()) {
			if (m.isClosed()) {
				t.interrupt();
			} else {
				try {
					kommtZurück();
				} finally {
					kasse = geheZuKasse(m);
				}
			}

			if (kasse.getWarteSchlange().peek() == this) {
				System.out.println("my name is " + name + " und ich bin an der kasse " + kasse.getName());
				try {
					kasse.kasseLock();
				} catch (Exception e) {

					e.printStackTrace();
				}
				bezahlen();
				kasse.kasseRelease();
				kasse.removeFromWarteSchlange();
				anDerKasse = false;
				essen();

			}

		}
	}

	public void start(Mensa m) {
		if (t == null) {
			t = new Thread(this, name);
			t.start();
		}
		this.m = m;
	}

	private void essen() {

		try {
			Thread.sleep(r.nextInt(100));
		} catch (InterruptedException e) {
			t.interrupt();
		}
	}

	private void bezahlen() {

		try {
			Thread.sleep(r.nextInt(2000));
		} catch (InterruptedException e) {
			t.interrupt();
		}
	}

	private void kommtZurück() {
		try {
			Thread.sleep(r.nextInt(100));
		} catch (InterruptedException e) {
			t.interrupt();
		}
	}

	private Kasse geheZuKasse(Mensa m) {
		Kasse min = m.getKassen().stream().min(new Comparator<Kasse>() {
			@Override
			public int compare(Kasse o1, Kasse o2) {
				if (o1.getWarteSchlange().size() > o2.getWarteSchlange().size()) {
					return 1;
				} else if (o1.getWarteSchlange().size() < o2.getWarteSchlange().size()) {
					return -1;
				} else {
					return 0;
				}
			}
		}).get();
		anDerKasse = true;
		min.addZuWarteSchlange(this);
		return min;
	}

	public Thread getT() {
		return t;
	}

	public String getName() {
		return name;
	}

	public boolean isAnDerKasse() {
		return anDerKasse;
	}
}
