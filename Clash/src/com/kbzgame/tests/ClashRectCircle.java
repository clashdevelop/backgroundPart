package com.kbzgame.tests;

import com.kbzgame.service.event.ShapeCrashManager;
import com.kbzgame.utils.Circle;
import com.kbzgame.utils.Point;
import com.kbzgame.utils.Rect;

public class ClashRectCircle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point x1=new Point(3,4);
		Point x2=new Point(4,3);
		Point x3=new Point(2,3.5);
		Rect rect=new Rect(x1,x2,0);
		rect.setAngle(Math.PI/4);
		Circle circle=new Circle(x3,1.0);
		System.out.println(ShapeCrashManager.testCircleAndRect(circle, rect));;
		System.out.println(ShapeCrashManager.getRectCrashDirection(circle, rect).getSize()+"  //s"+ShapeCrashManager.getRectCrashDirection(circle, rect).getAngle() );
	}

}
