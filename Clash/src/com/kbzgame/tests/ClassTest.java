package com.kbzgame.tests;

import com.kbzgame.service.gamebase.Crashable;
import com.kbzgame.service.gamebase.Movable;
import com.kbzgame.service.gamebase.Roller;

public class ClassTest {
	public static void main(String[] args){
		Roller roller = new Roller("Bob");
		Crashable c1 = roller;
		if(Movable.class.isInstance(roller)){
			System.out.println("Ok");
		}
		if(Movable.class.isInstance(c1)){
			System.out.println("Ok  too");
		}
	}
}
