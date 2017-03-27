package com.kbzgame.tests;

import java.util.ArrayList;
import java.util.List;

import com.kbzgame.utils.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Vector v1 = new Vector(1,Math.PI/2);
		Vector v2 = new Vector(1,Math.PI*3/2);
		Vector v3 = new Vector(1,0);
		Vector v4 = new Vector(4,Math.PI/2);
		Vector v5 = new Vector(0,0,1,1);
		Vector v6 = new Vector(0,0,1,1);
		v6.addVector(v1);
		Vector v7 = new Vector(0,0,0,1);
		Vector v8 = new Vector(0,0,0,-1);
		Vector v9 = new Vector(0,0,1,0);
		Vector v10 = new Vector(0,0);
		List<Vector> outputTestList = new ArrayList<Vector>();
		outputTestList.add(v1);
		outputTestList.add(v2);
		outputTestList.add(v3);
		outputTestList.add(v4);
		outputTestList.add(v5);
		outputTestList.add(v6);
		outputTestList.add(v7);
		outputTestList.add(v8);
		outputTestList.add(v9);
		outputTestList.add(v10);
		for(Vector v:outputTestList){
			System.out.println("x= "+v.getComponentX());
			System.out.println("y= "+v.getComponentY());
			System.out.println("angle= "+v.getAngle());
			System.out.println("______________________");
		}*/
		Vector v = new Vector(4,-Math.PI/4);
		System.out.println(v.getComponentX()+","+v.getComponentY());
	}

}
