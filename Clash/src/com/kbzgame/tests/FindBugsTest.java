package com.kbzgame.tests;

import java.util.Random;

public class FindBugsTest {
	 
	
	private static String strAb;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="123";
		StringBuffer strb=new StringBuffer("123");
		System.out.println(str.equals(strb));
		
		String str1="Check_string";
		
			if(str!=null)
				{
				if(str!=null)
				{
				System.err.println(str1);
				}
		}
			fun99();
	}
		//Target1 target=new Target1();
		
	
		
	private static void fun99()
	{
		String str1="123";
		String str2="456";
		String str3=String.format("{0} {1}", 123,str2);
		System.out.println(String.format("{0:N1}", 123));
		 System.out.println(String.format("{0:F2}",56789));
		 
		//String.for
		System.out.println(str3);
	}
	

	private static String func11()
	{
		return strAb;
	}
//	public static int func7(int seed)
//	{
//		return 
//	}
	public static void func9()
	{
		String str="function 4";
		System.out.println(str.toString());
	}


	}

