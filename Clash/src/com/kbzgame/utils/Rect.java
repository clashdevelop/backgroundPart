package com.kbzgame.utils;
//倾斜度
public class Rect implements Shape{
	private Point topLeft;
	private Point lowerRight;
//	private Point rotateTopLeft;
//	private Point rotateTopRight;
//	private Point rotateLowerLeft;
//	private Point rotateLowerRight;
	//private Point center;
	private double angle;
	//获取中心
	public Point getCenter() {
	
		return new Point((topLeft.getX()+lowerRight.getX())/2,(topLeft.getY()+lowerRight.getY())/2);
	}//获取旋转过后的点
	public Point getRotateTopLeft() {
		return rotateByCenter(getTopLeftPonit().getX(),getTopLeftPonit().getY());
	}
	public Point getRotateTopRight() {
		return rotateByCenter(getLowerRight().getX(),getTopLeftPonit().getY());
	}
	public Point getRotateLowerLeft() {
		return rotateByCenter(getTopLeftPonit().getX(),getLowerRight().getY());
	}
	public Point getRotateLowerRight() {
		return rotateByCenter(getLowerRight().getX(),getLowerRight().getY());
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public Rect(Point topLeft,Point lowerRight){
		
		this.topLeft = topLeft;
		this.lowerRight = lowerRight;
	}
	public Rect(Point topLeft,Point lowerRight,double angle){
		this.angle=angle;
		this.topLeft = topLeft;
		this.lowerRight = lowerRight;
	}
	public Point getTopLeftPonit(){
		return topLeft;
	}
	public Point getLowerRight(){
		return lowerRight;
	}
	public Point rotateByCenter(double x,double y)//矩形各边角绕中心旋转
	{
		
		 double x0= ((x-getCenter().getX())*Math.cos(angle)-(y-getCenter().getY())*Math.sin(angle))+getCenter().getX();
		 double y0=((x-getCenter().getX())*Math.sin(angle)+(y-getCenter().getY())*Math.cos(angle))+getCenter().getY();
//		Point point=new Point(x0,y0);
		 return new Point(x0,y0);
	}
}
