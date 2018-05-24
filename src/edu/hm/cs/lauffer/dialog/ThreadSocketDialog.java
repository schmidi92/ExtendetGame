package edu.hm.cs.lauffer.dialog;

import java.io.IOException;

/**
 * Klasse die die Kommunikation mit den Spielern verwaltet. Dateneingabe und
 * Ausgabe findet ueber Netzwerk statt.
 * Inklusive Threading.
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class ThreadSocketDialog extends SocketDialog {

	/**
	 * Innere Threadklasse.
	 * @author  Markus Schmidt und Jonas Lauffer
	 *
	 */
	class InnerRun implements Runnable{

		/**
		 * is playerA.
		 */
		private boolean playerA;
		
		/**
		 * Construktore.
		 * @param player welcher Spieler
		 */
		InnerRun(boolean player){
			this.playerA = player;
		}
		@Override
		public void run() {
			try {
				if (playerA) {
					value[0] =ThreadSocketDialog.this.getAChoice();
//					ThreadSocketDialog.this.setPlayA(true);
//					value[0] = ThreadSocketDialog.this.getNumber();
					System.out.println(value[0]+ "a");
				} else {
					
					value[1] =ThreadSocketDialog.this.getBChoice();
//					ThreadSocketDialog.this.setPlayA(false);
//					value[1] = ThreadSocketDialog.this.getNumber();
					System.out.println(value[1] + "b");
				}
			} catch (IOException e) {
				System.out.println("IOException: " + e);
			}
			
		}
		
	}
	
	/**
	 * return Values.
	 */
	private final int[] value = new int[2];


	/**
	 * Thread von spieler A.
	 */
	private Thread threadA;
	
	/**
	 * Thread von spieler A.
	 */
	private Thread threadB;
	
	/**
	 * Konstruktor.
	 * @throws IOException weil halt Exception
	 */
	public ThreadSocketDialog() throws IOException {
		super();
	}

	/**
	 * PlayerA fragen.
	 * @return die Nummer die PlayerA eingegeben hat.
	 * @throws IOException weil halt Exception
	 */
	public int askA() throws IOException {
		setPlayA(true);
		return getNumber();
	}

	/**
	 * PlayerB fragen.
	 * @return die Nummer die PlayerB eingegeben hat.
	 * @throws IOException weil halt Exception
	 */
	public int askB() throws IOException {
		setPlayA(false);
		return getNumber();
	}

	/**
	 * Erstellt und startet thread fuer spieler A und B.
	 * @return int[] arry
	 * @throws InterruptedException weil halt.
	 * @throws IOException weil halt.
	 */
	public int[] runAll() throws InterruptedException, IOException {

		
		threadA = new Thread(new InnerRun(true) );
		threadB = new Thread(new InnerRun(false) );
		
		threadA.start();
		threadB.start();
		
		threadA.join();
		threadB.join();
		
		return value;

	}



	public int[] getValue() {
		return value;
	}
	

}