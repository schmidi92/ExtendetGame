package edu.hm.cs.lauffer;
import java.io.IOException;

import edu.hm.cs.lauffer.dialog.FileDialog;
import edu.hm.cs.lauffer.parameters.ChaosParameters;
import edu.hm.cs.lauffer.rules.StandardRule;
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
	 */
	public static void main(String... args) throws IOException {
		
		/**
		 * Objekt fuer die Spielparameter.
		 */
    	final Parameter para= new ChaosParameters();
    	
    	/**
    	 * Objekt zur Dialog Fuehrung.
    	 */
    	final Dialog dial=new FileDialog();
    	
    	/**
    	 * Obekt fur die Spielregeln.
    	 */
    	final Rule rules =  new StandardRule();
        new UndercutMono().play(para,dial,rules);
    }

}
