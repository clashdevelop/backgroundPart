package com.kbzgame.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import com.kbzgame.service.command.Command;
import com.kbzgame.service.event.Event;
import com.kbzgame.service.event.EventFactory;
import com.kbzgame.service.gamebase.Crashable;
import com.kbzgame.service.gamebase.GameScene;
import com.kbzgame.service.gamebase.JsonVisitor;
import com.kbzgame.service.gamebase.Movable;
import com.kbzgame.service.gamebase.Roller;
import com.kbzgame.utils.Point;
import com.kbzgame.utils.Rect;
import com.kbzgame.utils.Shape;

public class GameView {
	public static Shape gameShape = new Rect(new Point(0,0),new Point(800,800));
	private List<Roller> rollerList = new ArrayList<Roller>();
	private List<GameScene> sceneList = new ArrayList<GameScene>();
	private List<Movable> moverList = new ArrayList<Movable>();
	private List<Event> eventList = new ArrayList<Event>();
	private List<Crashable> crashableList = new ArrayList<Crashable>();
	
	private ExecutorService taskManager = Executors.newCachedThreadPool();	
	
	private String message;
	private volatile boolean updateMess;
	public GameView(){
		updateMess = true;
		taskManager.execute(new UpdateTask());
	
	}
	public void addRoller(Roller newRoller){
		synchronized (this){
			addCrashable(newRoller);
			addMovable(newRoller);
			rollerList.add(newRoller);		
		}
	}
	public void exeCommand(Command exeCommand){
		synchronized (this) {
			//System.out.println("Execute command");
			exeCommand.execute();//命令的执行需要改变roller对象,临界资源
		}
	}
	public void removeRoller(Roller deadRoller){
		synchronized (this){
			deadRoller.dead();
		}
	}
	public synchronized String getMessage(){
		if(updateMess){
			updateMess = false;
			message = "";
			JSONObject sendMess = new JSONObject();
			sendMess.put("type", "allBall");
			for(Roller roller:rollerList){
				JsonVisitor jsonVisitor = new JsonVisitor(roller);
				sendMess.put("content", jsonVisitor.toString());
			}
			message += sendMess.toString();
		}
		return message;
	}
	public void dispose(){//游戏结束用于终结启动的线程
		synchronized (this) {
			taskManager.shutdownNow();
		}
	}		
	protected class UpdateTask implements Runnable{
		@Override
		public void run(){
			//对玩家位置更新
			try {
				while(!Thread.interrupted()){
//					System.out.println("update roller!");
					List<Movable> deadMoverList = new ArrayList<Movable>();
					List<Event> deadEventLsit = new ArrayList<Event>();
					synchronized (this){
						for(Movable mover:moverList){
							if(mover.alive()){
								mover.move();		
							}
							else{
								deadMoverList.add(mover);
							}
						}
						
						for(Event event:eventList){
							if(event.alive()){
								if(event.happen())
									event.action();
							}else{
								deadEventLsit.add(event);
							}		
						}
						//清理不存在的event和movable
						eventList.removeAll(deadEventLsit);
						moverList.removeAll(deadMoverList);
						//更新其他的相关的list
						updateList();
						updateMess = true;//需要刷新message
						
						//System.out.println("eventList: "+eventList.size());
						//System.out.println("moverList: "+moverList.size());
						//System.out.println("rollerList: "+rollerList.size());
						//System.out.println("crashableList: "+crashableList.size());

					}
					TimeUnit.MICROSECONDS.sleep(200);
				}
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private void addCrashable(Crashable newCrashable){
		for(Crashable crashable:crashableList){
			Event event = EventFactory.creatEvent(newCrashable, crashable);
			eventList.add(event);
		}
		crashableList.add(newCrashable);
	}
	private void addMovable(Movable movable){
		Event event = EventFactory.creatEvent(movable);
		eventList.add(event);
		moverList.add(movable);
	}
	private void updateList(){//moverList和eventList在执行更新，省去一次遍历
		List<Roller> deadRollerList = new ArrayList<Roller>();
		for(Roller roller:rollerList){
			if(!roller.alive()){
				deadRollerList.add(roller);
			}
		}
		rollerList.removeAll(deadRollerList);
		
		List<GameScene> deadSceneList = new ArrayList<GameScene>();
		for(GameScene scene:sceneList){
			if(!scene.alive()){
				deadSceneList.add(scene);
			}
		}
		sceneList.removeAll(deadSceneList);
		
		List<Crashable> deadCrashableList = new ArrayList<Crashable>();
		for(Crashable crashable:crashableList){
			if(!crashable.alive()){
				deadCrashableList.add(crashable);
			}
		}
		crashableList.removeAll(deadCrashableList);
		
	}
}

