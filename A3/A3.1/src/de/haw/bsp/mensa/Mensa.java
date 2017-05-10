package de.haw.bsp.mensa;

import java.util.ArrayList;
import java.util.List;

public class Mensa {

	private List<Kasse> kassen;
	private boolean closed;

	public Mensa(int kassenAnzahl) {
		closed = false;
		kassen = new ArrayList<>();

		for (int i = 1; i <= kassenAnzahl; i++) {
			kassen.add(new Kasse(i + ""));
		}
	}

	public List<Kasse> getKassen() {
		return kassen;
	}

	public boolean isEmpty() {

		for (int i = 0; i < kassen.size() - 1; i++) {
			if (!kassen.get(i).getWarteSchlange().isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}
}
