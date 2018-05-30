/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 2003 MHz, 16000 MByte RAM)
 **/
package edu.hm.cs.rs.arch.a03_srp; //X

import java.io.IOException;

import edu.hm.lauffer.Dialog;
import edu.hm.lauffer.Parameter;
import edu.hm.lauffer.Rule;

/**
 * Monolithic version of Undercut. Violates lots of design principles.
 * 
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-03-30
 * @see <a href=
 *      "http://www.ams.org/publicoutreach/msamhome/96-undercut-index.html">Undercut</a>
 */
public class UndercutMono {

	/**
	 * Runs an Undercut game. Gets input from System.in, prints output to
	 * System.out.
	 * 
	 * @param para
	 *            Parameterobjekt welche Parameter das Spiel verwendet
	 * @param dialog
	 *            Dialogobjekt wie die Dialogfuehrung funktioniert
	 * @param rules
	 *            Regelobjekt welche Regeln das Spiel hat
	 * @exception IOException
	 *                on incomplete input.
	 * @throws InterruptedException Exception
	 */
	public void play(Parameter para, Dialog dialog, Rule rules) throws IOException, InterruptedException {
		int playerAScore = 0;
		int playerBScore = 0;
		int roundsPlayed = 0;

		System.out.printf("Undercut start%n"); // initial message to both players

		// loop until a player wins ...
		while (rules.gameStillrunning(playerAScore, playerBScore, para)) {
			// read players' choices; if invalid, discard and retry
//			dialog.askForNumber("a", para.toString(false)); // to player A
//			dialog.askForNumber("b", para.toString(true)); // to player B
			
			final int[] playerChoices = requestRightInput(dialog, para);

			// do {
			//
			// playerBChoice = dialog.getNumber();
			// System.out.println("Player B: " +playerBChoice);
			// }
			// while(!para.isValidNumber(playerBChoice));

			// update scores
			final int[] score = rules.evaluateScores(playerChoices[0], playerChoices[1]);
			playerAScore += score[0];
			playerBScore += score[1];
			roundsPlayed++;

			// publish scores to both players
			dialog.endOfRound(roundsPlayed, playerAScore, playerBScore);
		}

		// announce final results to both players
		dialog.printWinner(rules.determineWinner(playerAScore, playerBScore));
	}

	/**
	 * Wartet auf richtigen Input von beiden Spielern.
	 * @param dialog dialogKlasse
	 * @param para paraKlasse
	 * @return Int[] mit Eingabe der Spieler
	 * @throws InterruptedException ex
	 * @throws IOException ex
	 */
	private int[] requestRightInput(Dialog dialog, Parameter para) throws InterruptedException, IOException {
		final int[] playerChoices = new int[2];
		boolean aNotValid = true;
		boolean bNotValid = true;
//		final ThreadSocketDialog dialog1 = (ThreadSocketDialog) dialog;

		do {
			if (aNotValid && bNotValid) {
				final int[] playerNumbers = dialog.runAll(para.toString(false), para.toString(true));
				playerChoices[0] = playerNumbers[0]; // dialog.getNumber();
				playerChoices[1] = playerNumbers[1]; // dialog.getNumber();
				aNotValid = !para.isValidNumber(playerChoices[0] , false);
				bNotValid = !para.isValidNumber(playerChoices[1], true);
			} else if (aNotValid) {
				playerChoices[0]  = dialog.getNumber(true, para.toString(false));
				aNotValid = !para.isValidNumber(playerChoices[0] , false);
			} else {
				playerChoices[1] = dialog.getNumber(false, para.toString(true));
				bNotValid = !para.isValidNumber(playerChoices[1], true);
			}

			System.out.println("A:" + aNotValid + playerChoices[0] + ",B:" + bNotValid + playerChoices[1]);

		} while (aNotValid || bNotValid);
		
		return playerChoices;
	}
	

}
