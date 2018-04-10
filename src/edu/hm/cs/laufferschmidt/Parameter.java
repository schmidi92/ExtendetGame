package edu.hm.cs.laufferschmidt;

public interface Parameter {
	int getScoreToWin();
	int getMaxChoice();
	int getMinChoice();
	String toString();
	boolean isValidNumber(int number);
}
