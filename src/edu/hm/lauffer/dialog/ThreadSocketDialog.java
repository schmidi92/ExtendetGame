package edu.hm.lauffer.dialog;

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
	 * return Values.
	 */
	private final int[] playerInput = new int[2];


	
	/**
	 * Innere Threadklasse.
	 * @author  Markus Schmidt und Jonas Lauffer
	 *
	 */
	/* default */class InnerRun implements Runnable{
		//beginning of the nested class
		/**
		 * is playerA.
		 */
		private final boolean playerA;
		//Kein default Konstruktor
		/**
		 * Construktore.
		 * @param player welcher Spieler
		 */		
		/* default */InnerRun(boolean player){
			this.playerA = player;
		}
		@Override
		public void run() {
			try {
				if (playerA) {
					playerInput[0] =ThreadSocketDialog.this.getAChoice();
//					ThreadSocketDialog.this.setPlayA(true);
//					value[0] = ThreadSocketDialog.this.getNumber();
					System.out.println(playerInput[0]+ "a");
				} else {
					
					playerInput[1] =ThreadSocketDialog.this.getBChoice();
//					ThreadSocketDialog.this.setPlayA(false);
//					value[1] = ThreadSocketDialog.this.getNumber();
					System.out.println(playerInput[1] + "b");
				}
			} catch (IOException exception) {
				System.out.println("IOException: " + exception);
			}
			
		}
		
	}
	
	
	
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

		
		final Thread threadA = new Thread(new InnerRun(true) );
		final Thread threadB = new Thread(new InnerRun(false) );
		
		threadA.start();
		threadB.start();
		
		threadA.join();
		threadB.join();
		
		final int[] output = new int[2];
		System.arraycopy(playerInput, 0, output, 0, playerInput.length);
		return output;

	}


//	public int[] getPlayerInput() {
//		
//		final int[] output = new int[2];
//		System.arraycopy(playerInput, 0, output, 0, playerInput.length);
//		return output;
//	}
	

}