package com.kbzgame.service.gamebase;

import com.kbzgame.utils.Circle;
import com.kbzgame.utils.Point;
import com.kbzgame.utils.Shape;
import com.kbzgame.utils.Vector;

public class Roller implements Crashable{
	private static int count = 0;
	
	private final float k =  0.25f;
	private final float g = 9.8f;
	private final int id = count++;
	
	private Point position;
	private float r;
	//private Vector mouseF;//����ṩ������
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
	//	mouseF = new Vector(0,0);
		otherF = new Vector(0,0);
		r = 5;
		m = 1;
	}
	@Override
	public void move(){
		Vector fTotal = new Vector(0,0);
		if(v.getSize()!=0){
			//System.out.println("V isn't 0.");
			float fSize = k*m*g;//����Ħ�����Ĵ�С
			Vector f = new Vector(fSize,v.getAngle()+Math.PI);//�������ٶȷ����෴
			fTotal.addVector(f);
		}
		fTotal.addVector(otherF);
		otherF.resetComponent(0,0);//����λ��֮�����仯
		//System.out.println(""+fTotal.getSize()+" "+fTotal.getComponentX()+" "+fTotal.getComponentY());
		a = fTotal.divByNum(m);
		changeV();
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
		otherF.addVector(F);;
	}
	public void dead(){
		alive = false;
	}
	public void addMouseF(Vector mouseF){
		otherF.addVector(mouseF);;
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
	}
	private void changePosition(){
		position.changeBy(v.getComponentX(),v.getComponentY());
	}
	private float getShapeR(){
		return r;
	}
}
