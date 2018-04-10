package edu.hm.cs.laufferschmidt.rules;

import edu.hm.cs.laufferschmidt.Parameter;
import edu.hm.cs.laufferschmidt.Rule;

public class TwoDiffRule implements Rule {
	
	@Override
	public int[] evaluateScores(int playerAChoice,int playerBChoice){
		int[] matchScore = new int[2];
		 if(playerAChoice == playerBChoice - 1)
			 matchScore[0]=playerAChoice + playerBChoice;
            // PlayerA macht undercut
         else if(playerBChoice == playerAChoice - 1)
        	 matchScore[1]=playerAChoice + playerBChoice;
            // playerB macht underCut
         
         else if(playerAChoice == playerBChoice + 2)
			 matchScore[0]=playerAChoice + playerBChoice;
            //Wenn PlayerA 2 Punkte mehr hat, bekommt er alle Punkte;
         else if(playerBChoice == playerAChoice + 2)
        	 matchScore[1]=playerAChoice + playerBChoice;
		 //Wenn PlayerB 2 Punkte mehr hat, bekommt er alle Punkte;
         else {
        	 matchScore[0]=playerAChoice;
        	 matchScore[1]=playerBChoice;
     //        playerAScore += playerAChoice;
       //      playerBScore += playerBChoice;
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


