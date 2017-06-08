package de.haw.bsp.ssp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali Calis
 * @author Serhat Kocaoez
 * @author Anil Ersin Kaya
 * @version 0.1
 */
public class Desktop<T> {
	private List<T> myList;

	public Desktop() {
		this.myList = new ArrayList<>();
	}

	public synchronized void add(T elem) {
		getList().add(elem);
	}

	public synchronized void clear() {
		getList().clear();

	}

	public synchronized List<T> getList() {
		return this.myList;
	}

	public synchronized int size() {
		return getList().size();
	}

	public synchronized T get(int i) {
		return getList().get(i);
	}

}
