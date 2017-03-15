package com.kbzgame.utils;

public class Point {
//	对点的定义  x,y轴，加减操作
	private double x;
	private double y;
	public Point(double x,double y){
		this.x = x;
		this.y = y;
	}
	public void changeBy(double tx,double ty){
		x+=tx;
		y+=ty;
	}
	public void changeTo(double x,double y){
		this.x = x;
		this.y = y;
	} 
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public String toString(){
		return x+" "+y;
	} 
}
