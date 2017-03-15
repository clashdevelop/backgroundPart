package com.kbzgame.service.gamebase;

import com.kbzgame.utils.Point;
import com.kbzgame.utils.Vector;

public class Roller {
	private final static float k =  0.25f;
	private final static float g = 9.8f;
	private Point position;
	private Vector F;//����
	private Vector v;//�ٶ�
	private Vector a;//���ٶ�
	private int m;//����
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
			float fSize = k*m*g;//����Ħ�����Ĵ�С
			Vector f = new Vector(fSize,v.getAngle()+Math.PI);//�������ٶȷ����෴
			fTotal.addVector(f);
		}
		fTotal.addVector(F);
		//F.reset(0,0);//����λ��֮�����仯
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
