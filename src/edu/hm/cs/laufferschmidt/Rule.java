package edu.hm.cs.laufferschmidt;

public interface Rule {
	int[] evaluateScores(int playerAChoice,int playerBChoice);
	boolean gameStillrunning(int playerAScore, int playerBScore, Parameter para );
	String determineWinner(int playerAScore, int playerBScore);
}
