package edu.hm.cs.laufferschmidt.rules;

import edu.hm.cs.laufferschmidt.Parameter;
import edu.hm.cs.laufferschmidt.Rule;

public class DrawPotRule implements Rule {

	private int PotValue = 0;
	private int consecutiveDraw = 0;

	@Override
	public int[] evaluateScores(int playerAChoice, int playerBChoice) {
		int[] matchScore = new int[2];
		if (playerAChoice == playerBChoice - 1) {
			matchScore[0] = playerAChoice + playerBChoice + PotValue;
			PotValue = 0;
			consecutiveDraw = 0;
		}

		// playerAScore += playerAChoice + playerBChoice;
		else if (playerBChoice == playerAChoice - 1) {
			matchScore[1] = playerAChoice + playerBChoice + PotValue;
			PotValue = 0;
			consecutiveDraw = 0;
		}

		// playerBScore += playerAChoice + playerBChoice;
		else if (playerAChoice == playerBChoice) {
			PotValue += (playerAChoice + playerBChoice);
			++consecutiveDraw;
			if (consecutiveDraw == 3) {

			}
		} else {
			matchScore[0] = playerAChoice;
			matchScore[1] = playerBChoice;
			consecutiveDraw = 0;
			// playerAScore += playerAChoice;
			// playerBScore += playerBChoice;
		}
		return matchScore;
	}

	@Override
	public boolean gameStillrunning(int playerAScore, int playerBScore, Parameter para) {
		boolean stillRunning = playerAScore < para.getScoreToWin() && playerBScore < para.getScoreToWin();
		if (consecutiveDraw == 3) {
			stillRunning = false;
		}
		
		return stillRunning;
	}

	@Override
	public String determineWinner(int playerAScore, int playerBScore) {
		String winner;
		if (consecutiveDraw == 3 || playerAScore == playerBScore)
			winner = "Tie";
		else if (playerAScore > playerBScore)
			winner = "Player A wins";
		else
			winner = "Player B wins";
		return winner;
	}
}
