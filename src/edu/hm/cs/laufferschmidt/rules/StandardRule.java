package edu.hm.cs.laufferschmidt.rules;

import edu.hm.cs.laufferschmidt.Parameter;
import edu.hm.cs.laufferschmidt.Rule;

public class StandardRule implements Rule {

	@Override
	public int[] evaluateScores(int playerAChoice, int playerBChoice) {
		int[] matchScore = new int[2];
		if (playerAChoice == playerBChoice - 1)
			matchScore[0] = playerAChoice + playerBChoice;
		// playerAScore += playerAChoice + playerBChoice;
		else if (playerBChoice == playerAChoice - 1)
			matchScore[1] = playerAChoice + playerBChoice;
		// playerBScore += playerAChoice + playerBChoice;
		else {
			matchScore[0] = playerAChoice;
			matchScore[1] = playerBChoice;
			// playerAScore += playerAChoice;
			// playerBScore += playerBChoice;
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
