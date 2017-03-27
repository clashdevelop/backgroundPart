package com.kbzgame.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ListTest {
	public static void main(String[] args){
		Queue<String> list = new LinkedList<String>();
		list.offer("Hello");
		list.offer("hi");
		
		//while(!list.isEmpty()){
			String s = list.poll();
			System.out.println(s);
		//}
		System.out.println(list.toString());
		//System.out.println(list.toString());
	}
}
