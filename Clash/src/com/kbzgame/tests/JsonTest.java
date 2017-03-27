package com.kbzgame.tests;

import com.kbzgame.service.command.CommandFactory;
import com.kbzgame.service.gamebase.Roller;

public class JsonTest {
	public static void main(String[] args){
		Roller roller = new Roller("iiij");
		CommandFactory.creatCommand("{\"type\":\"mouse\",\"content\":{\"x\":\"0.00\",\"y\":\"0.00\"}}", roller);
	}
}
