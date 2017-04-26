package com.kbzgame.tests;

import java.util.concurrent.TimeUnit;

import com.kbzgame.controller.GameView;
import com.kbzgame.service.gamebase.Roller;

public class GameViewTest {
	static GameView game;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game  = new GameView();
	
		
		DeleyThread deley = new DeleyThread();
		deley.game = game;
		deley.start();
	}
	static class DeleyThread extends Thread{
		public GameView game;
		private int count = 0;
		@Override
		public void run(){
			Roller testRoller = new Roller("test1");
			try{
			while(count<4){
				if(count==0)
			//	game.addRoller(testRoller.clone());
				
				if(count==2){
					System.out.println("change roller");
					//testRoller.setName("Jack");
					//game.updateRoller(testRoller.clone());
					}
					try {
						TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) 
						{
					// TODO Auto-generated catch block
						System.out.println("addRoller Thread was Interrupted!!");
						break;
					}
					count++;
					System.out.println(count);
				}
			}
			finally{game.dispose();}
		}
	}
}
