package edu.hm.cs.laufferschmidt.parameters;

import edu.hm.cs.laufferschmidt.Parameter;

public class StandardParameters implements Parameter{
		@Override
		public int getScoreToWin(){
			return 40;
		}
		@Override
		public int getMaxChoice(){
			return 5;
		}
		
		public int getMinChoice(){
			return 1;
		}
		
		@Override
		public boolean isValidNumber(int number){
			return number>=getMinChoice()&&number<=this.getMaxChoice();
		}
		
		@Override
		public String toString(){
			return getMinChoice() + " - " + getMaxChoice();
		}
}
