package edu.hm.cs.laufferschmidt;
import java.io.IOException;

import edu.hm.cs.laufferschmidt.dialog.SocketDialog;
import edu.hm.cs.laufferschmidt.parameters.QuickParameters;
import edu.hm.cs.laufferschmidt.rules.DrawPotRule;
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
    	final Parameter para= new QuickParameters();
    	
    	/**
    	 * Objekt zur Dialog Fuehrung.
    	 */
    	final Dialog dial=new SocketDialog();
    	
    	/**
    	 * Obekt fur die Spielregeln.
    	 */
    	final Rule rules =  new DrawPotRule();
        new UndercutMono().play(para,dial,rules);
    }

}
