package com.kbzgame.service.gamebase;

import com.kbzgame.utils.Circle;
import com.kbzgame.utils.Point;
import com.kbzgame.utils.Shape;
import com.kbzgame.utils.Vector;

public class Roller implements Crashable{
	private static int count = 0;
	
	private final float k =  0.01f;
	private final float g = 0.02f;
	private final int id = count++;
	private final double maxV = 0.01;
	private Point position;
	private float r;
	private Vector mouseF;//����ṩ������
	private Vector otherF;//��������
	private Vector v;//�ٶ�
	private Vector a;//���ٶ�
	private int m;//����
	private String name;
	private boolean alive;
	public Roller(String name){
		this.name = name;
		position = new Point(0,0);
		a = new Vector(0,0);
		v = new Vector(0,0);
		alive = true;
		mouseF = new Vector(0,0);
		otherF = new Vector(0,0);
		r = 5;
		m = 1;
	}
	@Override
	public void move(){
		Vector fTotal = new Vector(0,0);
		if(v.getSize()>0){
			//System.out.println("V isn't 0.");
			float fSize = k*m*g;//����Ħ�����Ĵ�С
			Vector f = new Vector(fSize,v.getAngle()+Math.PI);//�������ٶȷ����෴
//			System.out.println("f:"+f.getSize()+","+f.getAngle());
			//System.out.println("f:"+f.getAngle()+",v:"+v.getAngle());
			fTotal.addVector(f);
		}
		fTotal.addVector(otherF);//��ӿ��ܵ�����
		fTotal.addVector(mouseF);//�������ṩ����
//		System.out.println("mouseF:"+mouseF.getSize()+","+mouseF.getAngle());
		otherF.resetComponent(0,0);//����λ��֮����������
		mouseF.resetComponent(0, 0);//����֮�����������
		
		//System.out.println(""+fTotal.getSize()+" "+fTotal.getComponentX()+" "+fTotal.getComponentY());
		a = fTotal.divByNum(m);
		//System.out.println(id+"a:"+a.getSize());
		changeV();
//		System.out.println(id+"v:"+v.getSize()+v.getAngle());
		changePosition();
		
		
	}
	@Override
	public int getM() {
		// TODO Auto-generated method stub
		return m;
	}
	@Override
	public Vector getV(){
		return v;
	}

	@Override
	public void outAction(Vector backVector) {
		// TODO Auto-generated method stub
		//System.out.println("**Out!!**");
		position.changeBy(backVector.getComponentX(),backVector.getComponentY());
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Circle(position,getShapeR());
	}
	@Override
	public boolean alive(){
		return alive;
	}
	@Override
	public void crashAction(Vector F) {
		// TODO Auto-generated method stub
		//System.out.println("**Crash!!**");
		otherF.addVector(F);
	}
	public void dead(){
		alive = false;
	}
	public void addMouseF(Vector mouseF){
		this.mouseF = mouseF;
	}
	public Point getPosition(){return position;}
	public int getID(){return id;}
	
	public String toString(){
		return id+"("+position+")"+name;
	}
	public void acceptJsonVisitor(JsonVisitor.Message message){
		message.setId(id);
		message.setName(name);
		message.setPosition(position);
	}
	
	private void changeV(){
		v.addVector(a);
		if(v.getSize()>=maxV){
			v = new Vector(maxV,v.getAngle());
		}
		if(v.getSize()<= k*g){
			v = new Vector(0,0);
		}
	}
	private void changePosition(){
		position.changeBy(v.getComponentX(),v.getComponentY());
	}
	private float getShapeR(){
		return r;
	}
}
