package de.haw.bsp.mensa;

import java.util.ArrayList;
import java.util.List;

public class Mensa {

	private List<Kasse> kassen;

	public Mensa(int kassenAnzahl) {
		kassen = new ArrayList<>();
		for (int i = 1; i <= kassenAnzahl; i++) {
			kassen.add(new Kasse(i + ""));
		}
	}	

	public List<Kasse> getKassen() {
		return kassen;
	}

}
