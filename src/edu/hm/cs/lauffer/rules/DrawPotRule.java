package edu.hm.cs.lauffer.rules;

import edu.hm.cs.lauffer.Parameter;
import edu.hm.cs.lauffer.Rule;
/**
 * Klasse fuer neue Spielregeln. Bei Gleichstand werden beide Punktzahlen in einen Topf geworfen
 * und erst beim naechsten undercut dem Gewinner zugeschrieben. Bei dreimaligen Gleichstand hintereinander 
 * endet das Spiel in einem Tie.
 * 
 * @author  Markus Schmidt und Jonas Lauffer
 *
 */
public class DrawPotRule implements Rule {
	
	/**
	 * Schwelle nach wie vielen unentschieden, das Spiel bendet wird.
	 */
	private static final int DRAWTHRESHOLD = 3;
	
	
	/**
	 * Die punktezahl im gemeinsamen Topf.
	 */
	private int pointsInPot;
	/**
	 * Die Anzahlt an hintereinander gekommenen Gleichstaenden.
	 */
	private int consecutiveDraw;
	

	@Override
	public int[] evaluateScores(int playerAChoice, int playerBChoice) {
		final int[] matchScore = new int[2];
		//bei A undercut B bekommt A die Punkte und den Topf, consecutiveDraw reset
		if (playerAChoice == playerBChoice - 1) {
			matchScore[0] = playerAChoice + playerBChoice + pointsInPot;
			pointsInPot = 0;
			consecutiveDraw = 0;
		}

		//bei B undercut A bekommt B die Punkte und den Topf, consecutiveDraw reset
		else if (playerBChoice == playerAChoice - 1) {
			matchScore[1] = playerAChoice + playerBChoice + pointsInPot;
			pointsInPot = 0;
			consecutiveDraw = 0;
		}

		//Gleichstand, Punkte kommen in den Topf, consecutiveDraw wird rauf gezaehlt
		else if (playerAChoice == playerBChoice) {
			pointsInPot += playerAChoice + playerBChoice;
			++consecutiveDraw;
		}
		//Beide bekommen ihre Punkte, concsecutiveDraw reset
		else {
			matchScore[0] = playerAChoice;
			matchScore[1] = playerBChoice;
			consecutiveDraw = 0;
		}
		return matchScore;
	}

	@Override
	public boolean gameStillrunning(int playerAScore, int playerBScore, Parameter para) {
		boolean stillRunning = playerAScore < para.getScoreToWin() && playerBScore < para.getScoreToWin();
		//Falls drei Gleichstande hintereinander waren bricht das Spiel ab
		if (consecutiveDraw == DRAWTHRESHOLD) {
			stillRunning = false;
		}
		
		return stillRunning;
	}

	@Override
	public String determineWinner(int playerAScore, int playerBScore) {
		String winner;
		//Spiel ist Unentschieden, da drei Gleichstaende hintereinander waren
		if (consecutiveDraw == DRAWTHRESHOLD || playerAScore == playerBScore){
			winner = "Tie";}
		else if (playerAScore > playerBScore){
			winner = "Player A wins";}
		else{
			winner = "Player B wins";}
		return winner;
	}
}
