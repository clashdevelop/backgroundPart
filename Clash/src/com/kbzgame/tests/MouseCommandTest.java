package com.kbzgame.tests;

import com.kbzgame.service.command.Command;
import com.kbzgame.service.command.MouseCommand;
import com.kbzgame.service.gamebase.Roller;

public class MouseCommandTest {
	public static void main(String[] args){
		Roller testRoller = new Roller("Bob");
		System.out.println("Bob Moving....");
		for(int i=4;i>0;i--){
			testRoller.move();
			System.out.println(""+testRoller);
		}
		System.out.println("Bob Recive a MouseCommand");
		Command mouseCommand = new MouseCommand(testRoller,5f,5f);
		mouseCommand.execute();
		
		System.out.println("Bob Moving After Recive a Command....");
		for(int i=4;i>0;i--){
			testRoller.move();
			System.out.println(""+testRoller);
		}
	}
}
