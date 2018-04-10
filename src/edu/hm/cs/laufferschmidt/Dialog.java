package edu.hm.cs.laufferschmidt;

import java.io.IOException;

public interface Dialog {
	int getNumber() throws IOException;
	void askForNumber(String s,Parameter para) throws IOException;
	void endOfRound(int roundsPlayed, int playerAScore, int playerBScore) throws IOException;
	void printWinner(String winner) throws IOException;
}
