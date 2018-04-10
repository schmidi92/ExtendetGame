package edu.hm.cs.rs.arch.a03_srp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PlayerB {
	private final int PORT= 2001;
	
	public static void main(String ...strings ) throws UnknownHostException, IOException{
		Socket s= new Socket(InetAddress.getByName("127.0.0.1"), 2002);
		BufferedReader rea = new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedWriter writerA = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		while(true){		
		
		String readLine = rea.readLine();
		System.out.println(readLine);
		
		int toSend = System.in.read();
		System.in.skip(300);
		System.out.println("nummer"+toSend);
		
		writerA.write(toSend);
		writerA.flush();
		System.out.println("flushed" + toSend);
		
		while(true){
			readLine = rea.readLine();
			System.out.println(readLine);
			readLine = rea.readLine();
			System.out.println(readLine);
			
			
			System.out.println("vor readin");
			toSend = System.in.read();
			System.in.skip(300);
			System.out.println("nummer"+toSend);
			
			writerA.write(toSend);
			writerA.flush();
			System.out.println("flushed" + toSend);
		}
		
		}
	}
}
