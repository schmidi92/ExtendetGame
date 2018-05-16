package edu.hm.cs.lauffer.dialog;

import java.io.IOException;

public class ThreadSocketDialog extends SocketDialog {
	
	class InnerRun implements Runnable{

		boolean playerA;
		InnerRun(boolean player){
			this.playerA = player;
		}
		@Override
		public void run() {
			try {
				if (playerA) {
					ThreadSocketDialog.this.setPlayA(true);
					value[0] = ThreadSocketDialog.this.getNumber();
					System.out.println(value[0]+ "a");
				} else {
					ThreadSocketDialog.this.setPlayA(false);;
					value[1] = ThreadSocketDialog.this.getNumber();
					System.out.println(value[1] + "b");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	final int[] value = new int[2];
//	private boolean playerA=false;

	private Thread threadA;
	private Thread threadB;
	public ThreadSocketDialog() throws IOException {
		super();
	}

	public int askA() throws IOException {
		setPlayA(true);
		return getNumber();
	}

	public int askB() throws IOException {
		setPlayA(false);
		return getNumber();
	}

	public int[] runAll() throws InterruptedException, IOException {
//		this.playerA=true;
//		Thread thread1 = new Thread(this);
//		thread1.start();
//		Thread.sleep(200);
//		
//		ThreadSocketDialog ttt = new ThreadSocketDialog();
//		Thread thread2 = new Thread(ttt);
//
//		thread2.start();
//		thread1.join();
//		thread2.join();
//		value[1]= ttt.value[1];
//		return value;
		
		threadA = new Thread(new InnerRun(true) );
		threadB = new Thread(new InnerRun(false) );
		
		threadA.start();
		threadB.start();
		
		threadA.join();
		threadB.join();
		
		return value;

	}

	

	
//	@Override
//	public void run() {
//		
//		
//		try {
//			if (playerA) {
//				super.setPlayA(true);
//				value[0] = super.getNumber();
//				System.out.println(value[0]+ "a");
//			} else {
//				super.setPlayA(false);
//				value[1] = super.getNumber();
//				System.out.println(value[1] + "b");
//			}
//		} catch (IOException e) {
//			System.out.println("IOException: " + e);
//		}
//
//	}

	public int[] getValue() {
		return value;
	}

}