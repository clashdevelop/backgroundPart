package com.kbzgame.tests;

public class TestThread {

	public static void main(String[] args)
	{
//		Thread t=new Thread(new Target());
//		t.start();
		
		
	}
	
	public void gg()
	{
		Thread t=new Thread(new Target());
		t.start();
				
	}
	public  class Target implements Runnable
	{
		Integer t=0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true)
			{
				System.out.println("hello"+(t++));
			}
		}
		
	}
}
