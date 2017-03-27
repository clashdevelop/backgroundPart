package com.kbzgame.utils;

public class Circle implements Shape{
	private Point position;
	private double r;
	public Circle(Point position,double r){
		this.position = position;
		this.r = r;
	}
	public Point getPosition(){
		return position;
	}
	public double getR(){
		return r;
	}
}
