package com.kbzgame.service.gamebase;

import com.kbzgame.utils.Point;
import com.kbzgame.utils.Vector;

public class Roller {
	private final static float k =  0.25f;
	private final static float g = 9.8f;
	private Point position;
	private Vector F;//外力
	private Vector v;//速度
	private Vector a;//加速度
	private int m;//质量
	private String name;
	public Roller(String name){
		this.name = name;
		position = new Point(0,0);
		a = new Vector(0,0);
		v = new Vector(0,0);
		F = new Vector(0,0);
		m = 1;
	}
	
	
	public void move(){
		Vector fTotal = new Vector(0,0);
		if(v.getSize()!=0){
			//System.out.println("V isn't 0.");
			float fSize = k*m*g;//计算摩擦力的大小
			Vector f = new Vector(fSize,v.getAngle()+Math.PI);//方向与速度方向相反
			fTotal.addVector(f);
		}
		fTotal.addVector(F);
		//F.reset(0,0);//更新位置之后，力变化
		//System.out.println(""+fTotal.getSize()+" "+fTotal.getComponentX()+" "+fTotal.getComponentY());
		a = fTotal.divByNum(m);
		changeV();
		changePosition();
		
	}

	public void clearF(){
		F.reset(0,0);
	}
	public void addF(Vector addendF){
		F.addVector(addendF);
	}
	public double getPositionX(){
		return position.getX();
	}
	public double getPositionY(){
		return position.getY();
	}
	
	
	private void changeV(){
		v.addVector(a);
	}
	private void changePosition(){
		position.changeBy(v.getComponentX(),v.getComponentY());
	}
	/*public  void test(){
		System.out.println("F: "+F.getSize()+" "+F.getComponentX()+" "+F.getComponentY());
		System.out.println("v: "+v.getSize()+" "+v.getComponentX()+" "+v.getComponentY());
		System.out.println("a: "+a.getSize()+" "+a.getComponentX()+" "+a.getComponentY());
	}*/
	public String toString(){
		return name+"("+getPositionX()+","+getPositionY()+")";
	}
}
