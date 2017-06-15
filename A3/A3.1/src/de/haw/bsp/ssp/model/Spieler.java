package de.haw.bsp.ssp.model;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
import java.util.Random;

public class Spieler {
	private Tisch tisch;
	private String name;
	private SpielObjekte[] ssp;
	private Random r;

	public Spieler(String name) {
		this.name = name;
		this.r = new Random();
		this.ssp = new SpielObjekte[3];
		this.ssp[0] = new Papier(this);
		this.ssp[1] = new Stein(this);
		this.ssp[2] = new Schere(this);
	}

	public SpielObjekte choose() {
		return ssp[r.nextInt(3)];
	}

	public Tisch getTisch() {
		return tisch;
	}

	public void setTisch(Tisch t) {
		this.tisch = t;
	}

	public SpielObjekte[] getSsp() {
		return ssp;
	}

	public void setSsp(SpielObjekte[] ssp) {
		this.ssp = ssp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
