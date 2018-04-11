package edu.hm.cs.laufferschmidt.rules;

import edu.hm.cs.laufferschmidt.Parameter;
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
		int[] matchScore = new int[2];
		//Player A undercut Player b, A bekommt alle Punkte
		if (playerAChoice == playerBChoice - 1)
			matchScore[0] = playerAChoice + playerBChoice;
		//Player b undercut Player A, b Bekommt alle Punkte
		else if (playerBChoice == playerAChoice - 1)
			matchScore[1] = playerAChoice + playerBChoice;
		//beide bekommen ihre Punkte
		else {
			matchScore[0] = playerAChoice;
			matchScore[1] = playerBChoice;
		}
		return matchScore;
	}

	@Override
	public boolean gameStillrunning(int playerAScore, int playerBScore, Parameter para) {
		return playerAScore < para.getScoreToWin() && playerBScore < para.getScoreToWin();
	}

	@Override
	public String determineWinner(int playerAScore, int playerBScore) {
		String winner;
		if (playerAScore == playerBScore)
			winner = "Tie";
		else if (playerAScore > playerBScore)
			winner = "Player A wins";
		else
			winner = "Player B wins";
		return winner;
	}
}
