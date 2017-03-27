package com.kbzgame.tests;

import com.kbzgame.service.gamebase.JsonVisitor;
import com.kbzgame.service.gamebase.Roller;

public class VisitorTest {
	public static void main(String[] args){
		Roller roller = new Roller("jack");
		JsonVisitor visitor = new JsonVisitor(roller);
		
		System.out.println(visitor);
	}
}
