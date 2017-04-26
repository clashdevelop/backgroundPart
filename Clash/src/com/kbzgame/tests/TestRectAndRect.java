package com.kbzgame.tests;

import com.kbzgame.service.event.ShapeCrashManager;
import com.kbzgame.utils.Point;
import com.kbzgame.utils.Rect;

public class TestRectAndRect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Rect t1=new Rect(new Point(1,2),new Point(2,1),Math.PI/4);
		Rect t2=new Rect(new Point(2.206,2),new Point(3,1),0);
		System.out.println(ShapeCrashManager.testRectAndRect(t2, t1));
	}

}
