package com.kbzgame.service.event;

import com.kbzgame.service.gamebase.Crashable;
import com.kbzgame.utils.Vector;

public class CrashEvent extends Event{
	private Crashable crasherA;
	private Crashable crasherB;
	public CrashEvent(Crashable crasherA,Crashable crasherB){
		this.crasherA = crasherA;
		this.crasherB = crasherB;
	}
	@Override
	public boolean happen() {
		// TODO Auto-generated method stub
		return ShapeCrashManager.testShape(crasherA.getShape(), crasherB.getShape());
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		//得到A和B有效撞击的方向
		Vector crashDirectionAtoB = ShapeCrashManager.getShapeCrashDirection(crasherA.getShape(),crasherB.getShape());
		Vector va = crasherA.getV();
		//将A的速度进行坐标系转换，以撞击方向为x轴正方向
		Vector convertVa = Vector.convertVectorToReferenceFrame(va,crashDirectionAtoB);
		//得到A在撞击方向的有效速度大小，负值说明对B没有撞击作用
		double va_crashComponent = convertVa.getComponentX();
		if(va_crashComponent>0){
			//A的撞击力的大小
			double crashfSize = crasherA.getM()*va_crashComponent;
			Vector crashFa = new Vector(crashfSize,crashDirectionAtoB.getAngle());
			crasherB.crashAction(crashFa);
		}
		//同理计算B对A的撞击力
		Vector crashDirectionBtoA = ShapeCrashManager.getShapeCrashDirection(crasherB.getShape(),crasherA.getShape());
		Vector vb = crasherB.getV();
		Vector convertVb = Vector.convertVectorToReferenceFrame(vb,crashDirectionBtoA);
		double vb_crashComponent = convertVb.getComponentX();
		if(vb_crashComponent>0){
			double crashfSize = crasherB.getM()*vb_crashComponent;
			Vector crashFb = new Vector(crashfSize,crashDirectionBtoA.getAngle());
			crasherA.crashAction(crashFb);
		}
		
	}
	@Override
	public boolean alive() {
		// TODO Auto-generated method stub
		return crasherA.alive() && crasherB.alive();
	}
}
