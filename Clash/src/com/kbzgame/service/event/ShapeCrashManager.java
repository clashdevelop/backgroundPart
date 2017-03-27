package com.kbzgame.service.event;

import com.kbzgame.utils.Circle;
import com.kbzgame.utils.Shape;
import com.kbzgame.utils.Vector;

public class ShapeCrashManager {
	public static boolean testShape(Shape shapeA,Shape shapeB){
		if(isCircle(shapeA) && isCircle(shapeB)){
			return testCircleAndCircle((Circle)shapeA,(Circle)shapeB);
		}
		return false;
	}
	public static boolean testCircleAndCircle(Circle c1,Circle c2){
		double dx = c1.getPosition().getX()-c2.getPosition().getX();
		double dy = c2.getPosition().getY()-c2.getPosition().getY();
		double dis = Math.sqrt(dx*dx+dy*dy);
		double crashDis = c1.getR()+c2.getR();
		if(dis<crashDis){return true;}
		return false;
	}
	public static Vector getShapeCrashDirection(Shape beginShapeA,Shape endShapeB){
		if(isCircle(beginShapeA) && isCircle(endShapeB)){
			return getCircleCrashDirection((Circle)beginShapeA,(Circle)endShapeB);
		}
		return null;
	}
	public static Vector getCircleCrashDirection(Circle beginCircle,Circle endCircle){
		Vector directionVector = null;
		double beginX = beginCircle.getPosition().getX();
		double beginY = beginCircle.getPosition().getY();
		double endX = endCircle.getPosition().getX();
		double endY = endCircle.getPosition().getY();
		directionVector = new Vector(beginX,beginY,endX,endY);
		return directionVector;
	}
	private static boolean isCircle(Shape shape){
		return Circle.class.isInstance(shape);
	}
}
