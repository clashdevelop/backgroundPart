package com.kbzgame.utils;

public class Vector {
	private double componentX;
	private double componentY;
	public Vector(){}
	public Vector(double size,double angle){//大小方向初始化
		//if(angle>=2*Math.PI){angle -= 2*Math.PI;}

		componentX = size*Math.cos(angle);
		componentY = size*Math.sin(angle);
	}
	public Vector(double x0,double y0,double x1,double y1){
		//起点、终点初始化
		componentX = x1-x0;
		componentY = y1-y0;
		//计算角度
		//double angle = caculateAngle(tx,ty);
		//计算x,y分量
		//double size = Math.sqrt(tx*tx+ty*ty);
		//sizeX = size*Math.cos(angle);
		//sizeY = size*Math.sin(angle);
	}
	
	public static Vector convertVectorToReferenceFrame(Vector source,Vector xvector){
		   double oldAngle = source.getAngle();
		   double angle = xvector.getAngle();
		   double newAngle = oldAngle-angle;
		   return new Vector(source.getSize(),newAngle);
	}
	public double getComponentX(){//返回x分量
		return componentX;
	}
	public double getComponentY(){//返回y分量
		return componentY;
	}
	public double getAngle(){
		return caculateAngle(componentX,componentY);
	}
	public double getSize(){
		return Math.sqrt(componentX*componentX+componentY*componentY);
	}
	public void addVector(Vector addendVector){//该向量加上另一个向量
		componentX += addendVector.getComponentX();
		componentY += addendVector.getComponentY();
	}
	public void resetComponent(double componentX,double componentY){
		this.componentX = componentX;
		this.componentY = componentY;
	}
	public Vector divByNum(double num){
		return new Vector(getSize()/num,getAngle());
	}
	
	private double caculateAngle(double componentX,double componentY){
		double angle = 0;//水平方向为准，0<=angle<2*Math.PI
		if(componentX==0){
			if(componentY>0){angle=Math.PI/2;}
			else if(componentY==0){angle=0;}
			else if(componentY<0){angle=Math.PI*3/2;}
			
		}
		else{
			double tangle = Math.atan(componentY/componentX);
			if(componentX>0 && componentY>=0){angle=tangle;}
			else if(componentX>0 && componentY<0){angle=tangle+2*Math.PI;}
			else{angle=tangle+Math.PI;}
		
		}
		return angle;
	}
}
