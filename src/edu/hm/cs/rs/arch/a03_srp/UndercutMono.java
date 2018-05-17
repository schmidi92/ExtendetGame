/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 2003 MHz, 16000 MByte RAM)
 **/
package edu.hm.cs.rs.arch.a03_srp; //X

import java.io.IOException;

import edu.hm.cs.lauffer.*;
import edu.hm.cs.lauffer.Parameter;
import edu.hm.cs.lauffer.Rule;
import edu.hm.cs.lauffer.dialog.ThreadSocketDialog;
import edu.hm.cs.lauffer.parameters.ChaosParameters;

/**
 * Monolithic version of Undercut. Violates lots of design principles.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-03-30
 * @see <a href="http://www.ams.org/publicoutreach/msamhome/96-undercut-index.html">Undercut</a>
 */
public class UndercutMono {
    

    /**
     * Runs an Undercut game.
     * Gets input from System.in, prints output to System.out.
     * @param para Parameterobjekt welche Parameter das Spiel verwendet
     * @param dialog Dialogobjekt wie die Dialogfuehrung funktioniert
     * @param rules Regelobjekt welche Regeln das Spiel hat
     * @exception IOException on incomplete input.
     * @throws InterruptedException 
     */
    public void play(Parameter para,Dialog dialog, Rule rules) throws IOException, InterruptedException {
        int playerAScore = 0;
        int playerBScore = 0;
        int roundsPlayed = 0;

        System.out.printf("Undercut start%n"); // initial message to both players

        // loop until a player wins ...
        while(rules.gameStillrunning(playerAScore, playerBScore, para)) {
            int playerAChoice =0;
            int playerBChoice =0;
            // read players' choices; if invalid, discard and retry
            dialog.askForNumber("a", para.toString(false)); // to player A
            dialog.askForNumber("b", para.toString(true));  // to player B
            boolean aa=false;
            boolean bb = false;
            ThreadSocketDialog dialog1 = (ThreadSocketDialog) dialog;
           
            do {
            	if(!aa && !bb){
            		int [] i = dialog1.runAll();
                    playerAChoice =i[0]; //dialog.getNumber();
                    playerBChoice =i[1]; //dialog.getNumber();
            	}
            	else if(!aa){
            		playerAChoice = dialog1.askA();
            	}
            	else 
            		playerBChoice = dialog1.askB();
                
               
                aa=para.isValidNumber(playerAChoice,false);
                bb=para.isValidNumber(playerBChoice,true);
                
                System.out.println("A:"+aa + ",B:" +bb);
                
            }
            while(!aa||!bb);

            
           
//            do {
//            	
//            	playerBChoice = dialog.getNumber();
//                System.out.println("Player B: " +playerBChoice);
//            }
//            while(!para.isValidNumber(playerBChoice));

            // update scores
            final int [] score = rules.evaluateScores(playerAChoice, playerBChoice);
          	playerAScore+=score[0];
          	playerBScore+=score[1];
          	roundsPlayed++;


            // publish scores to both players
            dialog.endOfRound(roundsPlayed, playerAScore, playerBScore);
        }

        // announce final results to both players
        dialog.printWinner(rules.determineWinner(playerAScore, playerBScore));
    }

}
