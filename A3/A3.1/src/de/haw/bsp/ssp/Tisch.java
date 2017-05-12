package de.haw.bsp.ssp;

public class Tisch {
	private Schiedsrichter s;
	private Spieler s1;
	private Spieler s2;
	private Desktop<SpielObjekte> desktop;

	public Tisch(Schiedsrichter s, Spieler s1, Spieler s2) {
		this.s = s;
		this.s1 = s1;
		this.s2 = s2;
		this.desktop = new Desktop<>();
		s.setTisch(this);
		s1.setTisch(this);
		s2.setTisch(this);
	}

	public Schiedsrichter getS() {
		return s;
	}

	public void setS(Schiedsrichter s) {
		this.s = s;
	}

	public Spieler getS1() {
		return s1;
	}

	public void setS1(Spieler s1) {
		this.s1 = s1;
	}

	public Spieler getS2() {
		return s2;
	}

	public void setS2(Spieler s2) {
		this.s2 = s2;
	}

	public Desktop<SpielObjekte> getDesktop() {
		return desktop;
	}

	public void addDesktop(SpielObjekte spielObject) {
		this.desktop.add(spielObject);
	}
}
