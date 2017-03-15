package com.kbzgame.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.kbzgame.service.command.Command;
import com.kbzgame.service.event.Event;
import com.kbzgame.service.gamebase.Roller;
import com.kbzgame.utils.Vector;

public abstract class GameView {
	private List<Command> commandList = new ArrayList<Command>();//命令
	private List<Roller> rollerList = new ArrayList<Roller>();//球
	private List<Event> eventList = new ArrayList<Event>();//事件
	private ExecutorService manager = Executors.newCachedThreadPool();//线程池
	private static volatile boolean startTask = false;
	
	public GameView(){
		Roller roller = new Roller("Bob");
		roller.addF(new Vector(0,0,1,1));
		addRoller(roller);
		startTask = true;
		manager.execute(new UpdateTask());
	}
	public void addCommand(Command command){
		synchronized (commandList) {
			commandList.add(command);
		}
	}
	public void addEvent(Event event){
		synchronized (eventList) {
			eventList.add(event);
		}
	}
	public void addRoller(Roller roller){
		synchronized (rollerList){
			rollerList.add(roller);
		}
	}
	public void dispose(){//��Ϸ���������ս��������߳�
		startTask = false;
	}		

	
	protected class UpdateTask implements Runnable{
	
		@Override
		public void run(){
			while(startTask){
				//ִ������
				System.out.println("execute commands!");
				synchronized (commandList) {
					for(Command exeCommand:commandList){
						exeCommand.execute();
						commandList.remove(exeCommand);
					}
				}
				//�����λ�ø���
				System.out.println("update roller!");
				Iterator<Roller> rollerItor = null;
				synchronized (rollerList){
					rollerItor = rollerList.iterator();
					while(rollerItor.hasNext()){
						Roller movingRoller = rollerItor.next();
						movingRoller.move();
						System.out.println(""+movingRoller);
					}
				}
				//�¼��ļ��
				System.out.println("test events!");
				synchronized (eventList) {
					for(Event testEvent:eventList){
						testEvent.test();
						if(testEvent.happen());
							testEvent.action();
					}
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("UpdateThread was Interrupted!");	
					//e.printStackTrace();
				}
				
			}
		}
	}
}

