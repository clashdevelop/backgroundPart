package com.kbzgame.service.command;

import com.kbzgame.service.gamebase.Roller;

public class KeyCommand extends Command{
	private Roller reciver;
	private KEY pressKey;
	public KeyCommand(KEY pressKey,Roller reciver){
		this.reciver = reciver;
		this.pressKey = pressKey;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		switch(pressKey){
		case SKILLONE:
			break;
		case SKILLTWO:
			break;
		case SKILLTHREE:
			break;
		case ATACK:
			break;
		default:
			System.out.println("Enum KEY Error!!");
		}
	}
}
