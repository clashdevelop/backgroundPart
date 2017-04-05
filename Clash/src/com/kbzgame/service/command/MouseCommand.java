package com.kbzgame.service.command;


import com.kbzgame.service.gamebase.Roller;
import com.kbzgame.utils.Point;
import com.kbzgame.utils.Vector;

public class MouseCommand extends Command{
	private Roller reciver;
	private double mouseX;
	private double mouseY;
	public MouseCommand(Roller reciver,double mouseX,double mouseY){
		this.reciver = reciver;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub		
		Vector mouseF = new Vector(0,0);
		float times = 0.1f;
		//Point rollerpos = reciver.getPosition();
		double dx = 1 * times;
		double dy = 1 * times;
		mouseF.resetComponent(dx,dy);;
		reciver.addMouseF(mouseF);
	}
	public String toString(){
		return reciver+"mouseX"+mouseX+"mouseY"+mouseY;
	}

}
