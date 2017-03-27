package com.kbzgame.service.event;

import com.kbzgame.controller.GameView;
import com.kbzgame.service.gamebase.Movable;

public  class OutsideEvent extends Event{
	private Movable outTester;
	public OutsideEvent(Movable outTester){
		this.outTester = outTester;
	}
	@Override
	public boolean happen() {
		// TODO Auto-generated method stub
		return ShapeOutManager.testShape(outTester.getShape(),GameView.gameShape);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		outTester.outAction(ShapeOutManager.getBackVector());
	}
	@Override
	public boolean alive() {
		// TODO Auto-generated method stub
		
		return outTester.alive();
	}
	
}
