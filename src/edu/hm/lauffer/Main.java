package edu.hm.lauffer;
import java.io.IOException;

import edu.hm.cs.rs.arch.a03_srp.UndercutMono;
import edu.hm.lauffer.dialog.StandardDialog;
import edu.hm.lauffer.parameters.ChaosParameters;
import edu.hm.lauffer.rules.DrawPotRule;


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
    	final Parameter para= new ChaosParameters();
    	
    	/**
    	 * Objekt zur Dialog Fuehrung.
    	 */
    	final Dialog dial=new StandardDialog();
    	
    	/**
    	 * Obekt fur die Spielregeln.
    	 */
    	final Rule rules =  new DrawPotRule();
        new UndercutMono().play(para,dial,rules);
    }

}
