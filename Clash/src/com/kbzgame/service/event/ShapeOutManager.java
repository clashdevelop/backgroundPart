package com.kbzgame.service.event;

import com.kbzgame.utils.Circle;
import com.kbzgame.utils.Point;
import com.kbzgame.utils.Rect;
import com.kbzgame.utils.Shape;
import com.kbzgame.utils.Vector;

public class ShapeOutManager {
	private static Vector backVector= new Vector(0,0);//出界时应该退回的长度
	public static boolean testShape(Shape shape,Shape borderShape){
		if(Circle.class.isInstance(shape) && Rect.class.isInstance(borderShape)){
			return testCircleInRect((Circle)shape,(Rect)borderShape);
		}
		return false;
	}
	//检测碰撞同时记录应该回退的矢量
	public static boolean testCircleInRect(Circle circle,Rect borderRect){
		boolean outView = false;
		Point beginPoint = borderRect.getTopLeftPonit();
		Point endPoint = borderRect.getLowerRight();
		double circleR = circle.getR();
		double beginX = beginPoint.getX()+circleR;
		double beginY = beginPoint.getY()+circleR;
		double endX = endPoint.getX()-circleR;
		double endY = endPoint.getY()-circleR;
		double circleX = circle.getPosition().getX();
		double circleY = circle.getPosition().getY();
		if(circleX<beginX ){
			backVector.resetComponent(beginX-circleX,backVector.getComponentY());
			outView = true;
		}
		else if(circleX>endX ){
			backVector.resetComponent(endX-circleX, backVector.getComponentY());
			outView = true;
		}
		if(circleY<beginY ){
			backVector.resetComponent(backVector.getComponentX(),beginY-circleY);
			outView = true;
		}
		else if(circleY>endY){
			backVector.resetComponent(backVector.getComponentX(),endY-circleY);
			outView = true;
		}
		return outView;
	}
	public static Vector getBackVector(){
		Vector copyBackVector = new Vector(0,0);
		copyBackVector.resetComponent(backVector.getComponentX(),backVector.getComponentY());
		backVector.resetComponent(0, 0);
		return copyBackVector;
	}
}
