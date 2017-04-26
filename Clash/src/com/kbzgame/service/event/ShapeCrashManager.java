package com.kbzgame.service.event;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kbzgame.utils.Circle;
import com.kbzgame.utils.Point;
import com.kbzgame.utils.Rect;
import com.kbzgame.utils.Shape;
import com.kbzgame.utils.Vector;

public class ShapeCrashManager {
	
	private static boolean state=false;
	public static boolean testShape(Shape shapeA,Shape shapeB){
		if(isCircle(shapeA) && isCircle(shapeB)){
			return testCircleAndCircle((Circle)shapeA,(Circle)shapeB);
		}
		else if(isCircle(shapeA)&&isRect(shapeB))
			return testCircleAndRect((Circle)shapeA,(Rect)shapeB);
		else if(isRect(shapeA)&&isRect(shapeB))
			return testRectAndRect((Rect)shapeA,(Rect)shapeB);
		return false;
	}
	public static boolean testCircleAndRect(Circle c1,Rect c2)
	{
		
		Vector standardV=new Vector(c2.getRotateLowerLeft().getX(),c2.getRotateLowerLeft().getY(),c2.getRotateLowerRight().getX(),c2.getRotateLowerRight().getY());
		Vector zeroToTopLeft=new Vector(0,0,c2.getRotateTopLeft().getX(),c2.getRotateTopLeft().getY());
		Vector zeroToLowerRight=new Vector(0,0,c2.getRotateLowerRight().getX(),c2.getRotateLowerRight().getY());
		Vector zeroToCircleCenter=new Vector(0,0,c1.getPosition().getX(),c1.getPosition().getY());
		Vector n1=Vector.convertVectorToReferenceFrame(zeroToTopLeft,standardV);//变换坐标系求左上点在新坐标的投影，没有合理的名字命名了
		double x=n1.getSize()*Math.cos(n1.getAngle());//左上边点变换坐标系后的新坐标x
		double y=n1.getSize()*Math.sin(n1.getAngle());//新坐标y
		Point topLeftWcs=new Point(x,y);//新坐标系
		//右下坐标转换
		Point lowerRightWcs=new Point(Vector.convertVectorToReferenceFrame(zeroToLowerRight, standardV).getSize()*Math.cos(Vector.convertVectorToReferenceFrame(zeroToLowerRight, standardV).getAngle()),
				Vector.convertVectorToReferenceFrame(zeroToLowerRight, standardV).getSize()*Math.sin(Vector.convertVectorToReferenceFrame(zeroToLowerRight, standardV).getAngle()));
	   //圆心坐标转换
		Point circleCenter=new Point(Vector.convertVectorToReferenceFrame(zeroToCircleCenter, standardV).getSize()*Math.cos(Vector.convertVectorToReferenceFrame(zeroToCircleCenter, standardV).getAngle()),
				Vector.convertVectorToReferenceFrame(zeroToCircleCenter, standardV).getSize()*Math.sin(Vector.convertVectorToReferenceFrame(zeroToCircleCenter, standardV).getAngle()));
		
		double d1=dis(topLeftWcs.getX(),topLeftWcs.getY(),circleCenter.getX(),circleCenter.getY());
		double d2=dis(lowerRightWcs.getX(),lowerRightWcs.getY(),circleCenter.getX(),circleCenter.getY());
		double d3=dis(topLeftWcs.getX(),lowerRightWcs.getY(),circleCenter.getX(),circleCenter.getY());
		double d4=dis(lowerRightWcs.getX(),topLeftWcs.getY(),circleCenter.getX(),circleCenter.getY());
		if(d1<=c1.getR()||d2<=c1.getR()||d3<=c1.getR()||d4<=c1.getR())
			state=true;

			
		else if(circleCenter.getX()>=topLeftWcs.getX()&&circleCenter.getX()<=lowerRightWcs.getX()&&circleCenter.getY()<=topLeftWcs.getY()+c1.getR()&&circleCenter.getY()>=lowerRightWcs.getY()-c1.getR())
			state=true;
		else if(circleCenter.getX()>=topLeftWcs.getX()-c1.getR()&&circleCenter.getX()<=lowerRightWcs.getX()+c1.getR()&&circleCenter.getY()<=topLeftWcs.getY()&&circleCenter.getY()>=lowerRightWcs.getY())
			state=true;
		
		return state;
		
	}
	public static double dis(double x0,double y0,double x1,double y1)//求点与点之间的距离  矩形的四个顶点与圆心的距离
	{
		return Math.sqrt((x0-x1)*(x0-x1)+(y0-y1)*(y0-y1));
	}
	
	public static boolean testRectAndRect(Rect r1,Rect r2)
	{	
		 boolean sign=true;
		List<Point> r1List=new ArrayList<Point>();
		r1List.add(r1.getRotateLowerLeft());
		r1List.add(r1.getRotateLowerRight());
		r1List.add(r1.getRotateTopLeft());
		r1List.add(r1.getRotateTopRight());
		List<Point> r2List=new ArrayList<Point>();
		r2List.add(r2.getRotateLowerLeft());
		r2List.add(r2.getRotateLowerRight());
		r2List.add(r2.getRotateTopLeft());
		r2List.add(r2.getRotateTopRight());
				
		List<Vector> standardV=new ArrayList<Vector>();
		
		standardV.add(new Vector(r1.getRotateLowerLeft().getX(),r1.getRotateLowerLeft().getY(),r1.getRotateLowerRight().getX(),r1.getRotateLowerRight().getY())
		);
		standardV.add(new Vector(r1.getRotateLowerLeft().getX(),r1.getRotateLowerLeft().getY(),r1.getRotateTopLeft().getX(),r1.getRotateTopLeft().getY()));
		standardV.add(new Vector(r2.getRotateLowerLeft().getX(),r2.getRotateLowerLeft().getY(),r2.getRotateLowerRight().getX(),r2.getRotateLowerRight().getY()));
		standardV.add(new Vector(r2.getRotateLowerLeft().getX(),r2.getRotateLowerLeft().getY(),r2.getRotateTopLeft().getX(),r2.getRotateTopLeft().getY())
		);
		double a[]=new double [4];
		double b[]=new double [4];
		for(int i=0;i<4;i++)
		{
			Vector standard=standardV.get(i);
			
			for(int j=0;j<4;j++)
			{
				a[j]=Vector.convertVectorToReferenceFrame(new Vector(0,0,r1List.get(j).getX(), r1List.get(j).getY()),standard).getComponentX();
				b[j]=Vector.convertVectorToReferenceFrame(new Vector(0,0,r2List.get(j).getX(), r2List.get(j).getY()),standard).getComponentX();
			}
			Arrays.sort(a);
			Arrays.sort(b);
			if(a[3]<b[0]||a[0]>b[3])
			{
				sign=false;break;
			}
		}	
		return sign;
	}

	public static boolean testCircleAndCircle(Circle c1,Circle c2){
		double dx = c1.getPosition().getX()-c2.getPosition().getX();
		double dy = c2.getPosition().getY()-c2.getPosition().getY();
		double dis = Math.sqrt(dx*dx+dy*dy);
		double crashDis = c1.getR()+c2.getR();
		if(dis<crashDis){return true;}
		return false;
	}
	public static Vector getShapeCrashDirection(Shape beginShapeA,Shape endShapeB){
		if(isCircle(beginShapeA) && isCircle(endShapeB)){
			return getCircleCrashDirection((Circle)beginShapeA,(Circle)endShapeB);
		}
		else if(isCircle(beginShapeA) && isRect(endShapeB))
		{
			return getRectCrashDirection((Circle)beginShapeA,(Rect)endShapeB);
		}
		
		return null;
	}
	public static Vector getCircleCrashDirection(Circle beginCircle,Circle endCircle){
		Vector directionVector = null;
		double beginX = beginCircle.getPosition().getX();
		double beginY = beginCircle.getPosition().getY();
		double endX = endCircle.getPosition().getX();
		double endY = endCircle.getPosition().getY();
		directionVector = new Vector(beginX,beginY,endX,endY);
		return directionVector;
	}
	public static Vector getRectAndRectDirection(Rect r1,Rect r2)
	{
		return null;
		//未完成
		
	}
	public static Vector getRectCrashDirection(Circle circle,Rect rect){
		
		List<Point> list=new ArrayList<Point>();
		list.add(rect.getRotateLowerRight());
		list.add(rect.getRotateLowerLeft());
		list.add(rect.getRotateTopLeft());
		list.add(rect.getRotateTopRight());
		for(Point t:list)
		{	
			if(dis(t.getX(),t.getY(),circle.getPosition().getX(),circle.getPosition().getY())<=circle.getR())
			{
				return new Vector(circle.getPosition().getX(),circle.getPosition().getY(),t.getX(),t.getY());
			}
				
		}
		Vector vx=new Vector(rect.getRotateLowerLeft().getX(),rect.getRotateLowerLeft().getY(),rect.getRotateLowerRight().getX(),rect.getRotateLowerRight().getY());
		Vector zeroToTopLeft=new Vector(0,0,rect.getRotateTopLeft().getX(),rect.getRotateTopLeft().getY());
			Vector zeroToLowerRight=new Vector(0,0,rect.getRotateLowerRight().getX(),rect.getRotateLowerRight().getY());
			Vector zeroToCircleCenter=new Vector(0,0,circle.getPosition().getX(),circle.getPosition().getY());
		
			Vector n1=Vector.convertVectorToReferenceFrame(zeroToTopLeft, vx);
			Vector n2=Vector.convertVectorToReferenceFrame(zeroToLowerRight, vx);
			Vector n3=Vector.convertVectorToReferenceFrame(zeroToCircleCenter, vx);
			
			double x0= n1.getComponentX()<n2.getComponentX()?n1.getComponentX():n2.getComponentX();
			double y0=n1.getComponentY()>n2.getComponentY()?n1.getComponentY():n2.getComponentY();
			double x1=n1.getComponentX()>n2.getComponentX()?n1.getComponentX():n2.getComponentX();
			double y1=n1.getComponentY()<n2.getComponentY()?n1.getComponentY():n2.getComponentY();
			double x2=n3.getComponentX();
			double y2=n3.getComponentY();
			if(x2>x1)return Vector.convertVectorToReferenceFrame(new Vector(x1,y1,x0,y1), new Vector(1,0));
			else if(y2<y1) return Vector.convertVectorToReferenceFrame(new Vector(x1,y1,x1,y0), new Vector(1,0));
			else if(x2<x1)return Vector.convertVectorToReferenceFrame(new Vector(x0,y0,x1,y0), new Vector(1,0));
			else if(y2>y1)return Vector.convertVectorToReferenceFrame(new Vector(x1,y0,x1,y1), new Vector(1,0));
		return null;
	}
	private static boolean isCircle(Shape shape){
		return Circle.class.isInstance(shape);
	}
	private static boolean isRect(Shape shape)
	{
		return Rect.class.isInstance(shape);
	}
}
