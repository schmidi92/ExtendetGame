package edu.hm.cs.laufferschmidt.parameters;

import edu.hm.cs.laufferschmidt.Parameter;

public class QuickParameters implements Parameter{
		@Override
		public int getScoreToWin(){
			return 12;
		}
		@Override
		public int getMaxChoice(){
			return 3;
		}
		
		@Override
		public int getMinChoice() {
			return 1;
		}
		
		@Override
		public boolean isValidNumber(int number){
			System.out.println("Valid:" + (number>=getMinChoice()&&number<=this.getMaxChoice()));
			return number>=getMinChoice()&&number<=this.getMaxChoice();
		}
		
		@Override
		public String toString(){
			return getMinChoice() + " - " + getMaxChoice();
		}
}
