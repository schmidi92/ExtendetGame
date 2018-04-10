package edu.hm.cs.laufferschmidt.parameters;

import edu.hm.cs.laufferschmidt.Parameter;

public class ChaosParameters implements Parameter{
	
	private final int[][] para = {{1,4},{1,3,5},{2,5}};
	private int instableCount = 0;
	private final int SCORETOWIN = 12;
	
	@Override
	public int getScoreToWin(){
		return SCORETOWIN;
	}
	@Override
	public int getMaxChoice(){
		return para[instableCount%3][para[instableCount%3].length-1];
	}
	
	@Override
	public int getMinChoice() {
		return para[instableCount%3][0];
	}
	
	@Override
	public boolean isValidNumber(int number){
		boolean isValid = false; //bleibt in der Schleife
		if(instableCount%3 == 1){
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
		
		if(instableCount%3 == 1){
			return para[1][0] + " / " + para[1][1] + " / " + para[1][2] ;
		} else {
			return getMinChoice() + " - " + getMaxChoice();
		}	
		
	}
}
