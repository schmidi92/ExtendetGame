package edu.hm.cs.laufferschmidt.dialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.hm.cs.laufferschmidt.Dialog;
import edu.hm.cs.laufferschmidt.Parameter;

public class FileDialog implements Dialog {

	final private File fileToRead = new File(System.getProperty("java.io.tmpdir") + "undercut.in.txt");
	final private File fileToWrite = new File(System.getProperty("java.io.tmpdir") + "undercut.out.txt");
	final private BufferedReader reader;
	final private BufferedWriter writer;

	public FileDialog() throws IOException {
		reader = new BufferedReader(new FileReader(fileToRead));
		writer = new BufferedWriter(new FileWriter(fileToWrite));
	}
	

	@Override
	public int getNumber() throws IOException {
		
		int test;
		try {
			test = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException file) {
			throw new IllegalArgumentException("zu wenig Befehle im TextFile");
		} 
		
		System.out.println(test);
		return test;
		// int result = System.in.read();
		// System.in.skip(50000);
		// if(result < 0)
		// throw new IOException(); // bomb out on end of input
		// return result;

	}

	@Override
	public void askForNumber(String player, Parameter para) throws IOException {
		writer.write("Player " + player.toUpperCase() + ", your choice " + para.toString() + '\n');
		writer.flush();
	}

	@Override
	public void endOfRound(int roundsPlayed, int playerAScore, int playerBScore) throws IOException {
		writer.write("Round " +roundsPlayed +", Player A: "+ playerAScore+ ", Player B: "+ playerBScore+ '\n');
		writer.flush();
	}

	@Override
	public void printWinner(String winner) throws IOException {
		writer.write(winner + '\n');
		writer.flush();
	}
}
