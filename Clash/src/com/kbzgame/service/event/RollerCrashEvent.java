package com.kbzgame.service.event;

import com.kbzgame.service.gamebase.Roller;

public class RollerCrashEvent extends Event{
	private Roller rollerA;
	private Roller rollerB;
	public void setRollerA(Roller rollerA){
		this.rollerA = rollerA;
	}
	public void setRollerB(Roller rollerB){
		this.rollerB = rollerB;
	}
	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean happen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
	
}
