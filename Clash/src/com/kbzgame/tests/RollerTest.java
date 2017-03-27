package com.kbzgame.tests;

import com.kbzgame.service.gamebase.Roller;
import com.kbzgame.utils.Vector;

public class RollerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Roller roller = new Roller("Jack");
		System.out.println("Befor addF:");
		System.out.println("Move.....");
		roller.move();
		//roller.test();
		//System.out.println("Roller's position:"+roller.getPositionX()+" "+roller.getPositionY());
		Vector f1 = new Vector(0,0,1,1);
	//	roller.addF(f1);
		System.out.println("After addF:");
		System.out.println("Move.....");
		roller.move();
		//roller.test();
		//System.out.println("Roller's position:"+roller.getPositionX()+" "+roller.getPositionY());
		System.out.println("Move.....");
		roller.move();
		//roller.test();
		//System.out.println("Roller's position:"+roller.getPositionX()+" "+roller.getPositionY());
	}

}
