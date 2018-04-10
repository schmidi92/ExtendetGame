package edu.hm.cs.rs.arch.a03_srp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PlayerA {
	private final int PORT = 2001;

	public static void main(String... strings) throws UnknownHostException, IOException {
		Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 2001);
		char[] cbuf = new char[300];
		BufferedReader rea = new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedWriter writerA = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

		int string = rea.read(cbuf);
		System.out.println(String.valueOf(cbuf, 0, string));
		int toSend = System.in.read();
		System.in.skip(300);
		System.out.println("nummer" + toSend);
		writerA.write(toSend);
		writerA.flush();
		System.out.println("flushed" + toSend);

		while (true) {
			
			
			string = rea.read(cbuf);
			System.out.println(String.valueOf(cbuf, 0, string));
			string = rea.read(cbuf);
			System.out.println(String.valueOf(cbuf, 0, string));
			
			toSend = System.in.read();
			System.in.skip(300);
			System.out.println("nummer" + toSend);
			writerA.write(toSend);
			writerA.flush();
			System.out.println("flushed" + toSend);
		}
		
	}
}
