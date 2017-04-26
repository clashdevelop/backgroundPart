package com.kbzgame.service.event;

import com.kbzgame.service.gamebase.Crashable;
import com.kbzgame.service.gamebase.Fixed;
import com.kbzgame.utils.Vector;

public class FixedCrashEvent extends Event{
	private Crashable crasherA;
	private Fixed fixedB;
	public FixedCrashEvent(Crashable crasherA,Fixed fixedB){
		this.crasherA = crasherA;
		this.fixedB = fixedB;
	}

	@Override
	public boolean happen() {
		// TODO Auto-generated method stub
		return ShapeCrashManager.testShape(crasherA.getShape(), fixedB.getShape());
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		Vector crashDirectionAtoB=ShapeCrashManager.getShapeCrashDirection(crasherA.getShape(), fixedB.getShape());
		Vector va=crasherA.getV();
		Vector convertVa = Vector.convertVectorToReferenceFrame(va,crashDirectionAtoB);
		double va_crashComponent = convertVa.getComponentX();
		if(va_crashComponent>0){
			//A的撞击力的大小
			double crashfSize = crasherA.getM()*va_crashComponent;
			Vector crashFa=new Vector(crashfSize,crashDirectionAtoB.getAngle()+Math.PI);
			crasherA.crashAction(crashFa);
	
		}
	}

	@Override
	public boolean alive() {
		// TODO Auto-generated method stub
		return crasherA.alive()&&fixedB.alive();
	}

}
