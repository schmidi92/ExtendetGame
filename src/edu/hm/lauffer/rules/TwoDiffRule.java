package edu.hm.lauffer.rules;

import edu.hm.lauffer.Rule;

/**
 * Klasse mit Hoffstaedter Undercut Regeln. Dazu zusaetzliche Regel, bei einer
 * Differenz von 2 bekommt der Spieler mit der hoeheren Zahl alle Punkte.
 * 
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class TwoDiffRule implements Rule {

	@Override
	public int[] evaluateScores(int playerAChoice, int playerBChoice) {
		final int[] matchScore = new int[2];
		if (playerAChoice == playerBChoice - 1) {
			matchScore[0] = playerAChoice + playerBChoice;
		}
		// PlayerA macht undercut
		else if (playerBChoice == playerAChoice - 1) {
			matchScore[1] = playerAChoice + playerBChoice;
		}
		// playerB macht underCut

		else if (playerAChoice == playerBChoice + 2) {
			matchScore[0] = playerAChoice + playerBChoice;
		}
		// Wenn PlayerA 2 Punkte mehr hat, bekommt er alle Punkte;
		else if (playerBChoice == playerAChoice + 2) {
			matchScore[1] = playerAChoice + playerBChoice;
		}
		// Wenn PlayerB 2 Punkte mehr hat, bekommt er alle Punkte;
		else {
			matchScore[0] = playerAChoice;
			matchScore[1] = playerBChoice;
		}
		return matchScore;
	}

}
