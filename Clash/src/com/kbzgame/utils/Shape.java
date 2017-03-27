package com.kbzgame.utils;

public interface Shape {
	public static Circle newCircle(Point center,double r){
		return new Circle(center,r);
	}
}
