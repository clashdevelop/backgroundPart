package com.kbzgame.utils;

public class Rect implements Shape{
	Point topLeft;
	Point lowerRight;
	public Rect(Point topLeft,Point lowerRight){
		this.topLeft = topLeft;
		this.lowerRight = lowerRight;
	}
	public Point getTopLeftPonit(){
		return topLeft;
	}
	public Point getLowerRight(){
		return lowerRight;
	}
}
