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
		//�õ�A��B��Чײ���ķ���
		Vector crashDirectionAtoB = ShapeCrashManager.getShapeCrashDirection(crasherA.getShape(),crasherB.getShape());
		Vector va = crasherA.getV();
		//��A���ٶȽ�������ϵת������ײ������Ϊx��������
		Vector convertVa = Vector.convertVectorToReferenceFrame(va,crashDirectionAtoB);
		//�õ�A��ײ���������Ч�ٶȴ�С����ֵ˵����Bû��ײ������
		double va_crashComponent = convertVa.getComponentX();
		if(va_crashComponent>0){
			//A��ײ�����Ĵ�С
			double crashfSize = crasherA.getM()*va_crashComponent;
			Vector crashFa = new Vector(crashfSize,crashDirectionAtoB.getAngle());
			crasherB.crashAction(crashFa);
		}
		//ͬ�����B��A��ײ����
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
