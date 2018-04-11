package edu.hm.cs.laufferschmidt.parameters;

import edu.hm.cs.laufferschmidt.Parameter;
/**
 * Parameterklasse.
 * Parameter varieren von Runde zu Runde.
 * @author Markus Schmidt und Jonas Lauffer
 *
 */
public class ChaosParameters implements Parameter{
	
	/**
	 * Array mit den unterschiedlichen Parametern.
	 */
	private final int[][] para = {{1,4},{1,3,5},{2,5}};
	
	/**
	 * Hilfsattribut um die Parameter rotieren zu lassen.
	 */
	private  int instableCount;
	/**
	 * Punktezahl, bei der das Spiel gewonnen ist.
	 */
	private static final int SCORETOWIN = 12;
	
	/**
	 * Anzahl der unterschiedlichen Parameter.
	 */
	private static final int PARACOUNT = 3;
	
	@Override
	public int getScoreToWin(){
		return SCORETOWIN;
	}
	@Override
	public int getMaxChoice(){
		
		return para[instableCount%PARACOUNT][para[instableCount%PARACOUNT].length-1];
	}
	
	@Override
	public int getMinChoice() {
		
		return para[instableCount%PARACOUNT][0];
	}
	
	@Override
	public boolean isValidNumber(int number){
		boolean isValid = false; //bleibt in der Schleife
		if(instableCount%PARACOUNT == 1){
			isValid= number==para[1][0] || number==para[1][1] || number==para[1][2] ;
		} else {
			isValid = number>=getMinChoice()&&number<=getMaxChoice();
		}	
		if(isValid){
			++instableCount;
		}
		return isValid;
	}
	
	@Override
	public String toString(){
		final String resultString;
		if(instableCount%PARACOUNT == 1){
			resultString= para[1][0] + " / " + para[1][1] + " / " + para[1][2] ;
		} else {
			resultString= getMinChoice() + " - " + getMaxChoice();
		}
		return resultString;
		
	}
}
