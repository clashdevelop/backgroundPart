package com.kbzgame.tests;

import java.util.ArrayList;
import java.util.List;

public class RemoveAllTest {
	public static void main(String[] args){
		List<String> strList= new ArrayList<String>();
		strList.add("abc");
		strList.add("de");
		strList.add("efg");
		List<String> deletList= new ArrayList<String>();
		deletList.add("abc");
		deletList.add("de");
		deletList.add("efg");
		strList.removeAll(deletList);
		for(String str:strList){
			System.out.println(str);
		}
	}
	
}
