package edu.hm.cs.laufferschmidt.rules;

import edu.hm.cs.laufferschmidt.Rule;

/**
 * Klasse mit Hoffstaedter Undercut Regeln.
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class StandardRule implements Rule {

	@Override
	public int[] evaluateScores(int playerAChoice, int playerBChoice) {
		final int[] playerPoints = new int[2];
		playerPoints[0] = playerAChoice;
		playerPoints[1] = playerBChoice;
		//Player A undercut Player b, A bekommt alle Punkte
		if (playerAChoice == playerBChoice - 1) {
			playerPoints[0] = playerAChoice + playerBChoice;
			playerPoints[1] = 0;
		}
		//Player b undercut Player A, b Bekommt alle Punkte
		else if (playerBChoice == playerAChoice - 1) {
			playerPoints[1] = playerAChoice + playerBChoice;
			playerPoints[0] = 0;
		}
		//beide bekommen ihre Punkte

		return playerPoints;
	}

	

}
