package edu.hm.cs.laufferschmidt;
import java.io.IOException;

import edu.hm.cs.laufferschmidt.dialog.SocketDialog;
import edu.hm.cs.laufferschmidt.parameters.QuickParameters;
import edu.hm.cs.laufferschmidt.rules.DrawPotRule;
import edu.hm.cs.rs.arch.a03_srp.UndercutMono;
/**
 * Entry point.
 * @param args Commandline args: none.
 * @exception IOException on incomplete input.
 */

public class Main {
	public static void main(String... args) throws IOException {
    	Parameter para= new QuickParameters();
    	Dialog dial=new SocketDialog();
    	Rule rules =  new DrawPotRule();
        new UndercutMono().play(para,dial,rules);
    }

}
