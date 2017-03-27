package com.kbzgame.tests;


import com.kbzgame.service.event.ShapeCrashManager;
import com.kbzgame.service.event.ShapeOutManager;
import com.kbzgame.utils.Circle;
import com.kbzgame.utils.Point;

public class ShapeTest {
	public static void main(String[] args){
		Circle circle1 = new Circle(new Point(1,0),1);
		Circle circle2 = new Circle(new Point(3,0),2);
		Point point1 = new Point(1,1);
		Point point2 = new Point(10,10);
		System.out.println("test crash");
		if(ShapeCrashManager.testCircleAndCircle(circle1,circle2)){
			System.out.println("two circle crash!");
		}
		if(ShapeCrashManager.testShape(circle1,circle2)){
			System.out.println("two circle crash!");
		}
		
	}
}
