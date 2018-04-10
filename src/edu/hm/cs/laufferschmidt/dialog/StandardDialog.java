package edu.hm.cs.laufferschmidt.dialog;

import java.io.BufferedReader;
import java.io.IOException;

import edu.hm.cs.laufferschmidt.Dialog;
import edu.hm.cs.laufferschmidt.Parameter;

public class StandardDialog implements Dialog{
	
	@Override
	public int getNumber() throws IOException{
		
		int result = System.in.read();
		System.in.skip(50000);
		if(result < 0)
            throw new IOException(); // bomb out on end of input
		return result - '0';
		
	}
	@Override
	public void askForNumber(String player,Parameter para){
		System.out.printf("Player "+player.toUpperCase() +", your choice " + para.toString()+"%n");
	}
	@Override
	public void endOfRound(int roundsPlayed, int playerAScore, int playerBScore){
		System.out.printf("Round %d, Player A: %d, Player B: %d%n", roundsPlayed, playerAScore, playerBScore);
	}
	@Override
	public void printWinner(String winner){
		System.out.println(winner);
	}
}
