package edu.hm.cs.lauffer;
import java.io.IOException;

import edu.hm.cs.lauffer.dialog.ThreadSocketDialog;
import edu.hm.cs.lauffer.parameters.StandardParameters;
import edu.hm.cs.lauffer.rules.DrawPotRule;
import edu.hm.cs.rs.arch.a03_srp.UndercutMono;


/**
 * Main Klasse, startet das Programm.
 * @author Markus Schmidt und Jonas Lauffer
 *
 */

public class Main {
	
	/**
	 * Entry point.
	 * @param args Commandline args: none.
	 * @exception IOException on incomplete input.
	 * @throws InterruptedException 
	 */
	public static void main(String... args) throws IOException, InterruptedException {
		
		/**
		 * Objekt fuer die Spielparameter.
		 */
    	final Parameter para= new StandardParameters();
    	
    	/**
    	 * Objekt zur Dialog Fuehrung.
    	 */
    	final Dialog dial=new ThreadSocketDialog();
    	
    	/**
    	 * Obekt fur die Spielregeln.
    	 */
    	final Rule rules =  new DrawPotRule();
        new UndercutMono().play(para,dial,rules);
    }

}
