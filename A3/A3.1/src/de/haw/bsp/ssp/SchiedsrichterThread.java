package de.haw.bsp.ssp;

public class SchiedsrichterThread extends Schiedsrichter implements Runnable {
	private Thread t;

	public SchiedsrichterThread() {

	}

	public void run() {
		System.out.println("Das spiel geht weiter");
		while (!t.isInterrupted()) {
			try {
				Thread.sleep(500);
				// ((SpielerThread) getTisch().getS1()).getT().join();
				// ((SpielerThread) getTisch().getS2()).getT().join();
				auswerten();
				getTisch().getDesktop().clear();
				// prepareForNextRound();
//				System.out.println("notify");
	
			} catch (InterruptedException e) {
				t.interrupt();
			}
			synchronized (this) {

			
				notifyAll();
			}

		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void stopDasSpiel(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("stopped");
		((SpielerThread) getTisch().getS1()).getT().interrupt();
		((SpielerThread) getTisch().getS2()).getT().interrupt();

		this.t.interrupt();
	}

	public void printErgebniss() {
		System.out.println("Spieler 1 wins " + getErgebnis()[0] + " || Unentschieden " + getErgebnis()[1]
				+ " || Spieler 2 wins " + getErgebnis()[2]);
	}

//	private void prepareForNextRound() {
//		SpielerThread s1 = new SpielerThread(getTisch().getS1().getName());
//		SpielerThread s2 = new SpielerThread(getTisch().getS2().getName());
//		getTisch().setS1(s1);
//		getTisch().setS2(s2);
//		s2.setTisch(getTisch());
//		s1.setTisch(getTisch());
//		s1.start();
//		s2.start();
//	}

	public Thread getT() {
		return t;
	}

}
