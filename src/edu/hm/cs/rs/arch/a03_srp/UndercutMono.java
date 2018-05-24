/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 2003 MHz, 16000 MByte RAM)
 **/
package edu.hm.cs.rs.arch.a03_srp; //X

import java.io.IOException;

import edu.hm.cs.lauffer.Dialog;
import edu.hm.cs.lauffer.Parameter;
import edu.hm.cs.lauffer.Rule;
import edu.hm.cs.lauffer.dialog.ThreadSocketDialog;

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
            boolean aIsValid=false;
            boolean bIsValid = false;
            final ThreadSocketDialog dialog1 = (ThreadSocketDialog) dialog;
           
            do {
            	if(!aIsValid && !bIsValid){
            		final int [] playerNumbers = dialog1.runAll();
                    playerAChoice =playerNumbers[0]; //dialog.getNumber();
                    playerBChoice =playerNumbers[1]; //dialog.getNumber();
                    aIsValid=para.isValidNumber(playerAChoice,false);
                    bIsValid=para.isValidNumber(playerBChoice,true);
            	}
            	else if(!aIsValid){
            		playerAChoice = dialog1.askA();
            		aIsValid=para.isValidNumber(playerAChoice,false);
            	}
            	else {
            		playerBChoice = dialog1.askB();
            		bIsValid=para.isValidNumber(playerBChoice,true);
            	}
                
                System.out.println("A:"+aIsValid + playerAChoice + ",B:" +bIsValid + playerBChoice);
                
            }
            while(!aIsValid||!bIsValid);

            
           
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
