//:GamePad is  used to create command by user message
package com.kbzgame.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.kbzgame.service.command.CommandFactory;
import com.kbzgame.service.command.Command;
import com.kbzgame.service.gamebase.Roller;

import net.sf.json.JSONObject;

public class Gamepad {
	private Roller roller;
	private GameView gameView;
	private BlockingQueue<Command> commandList = new LinkedBlockingQueue<Command>();
	private ExecutorService taskManager = Executors.newCachedThreadPool();

	public Gamepad(GameView gameView){
		this.gameView = gameView;
	}
	
	public void joinGame(String name){
		taskManager.execute(new ExecuteCommandTask());
		roller = new Roller(name);
		gameView.addRoller(roller);//线程安全
	}
	public void quitGame(){
		gameView.removeRoller(roller);
		taskManager.shutdownNow();
	}
	public String getGamePadId(){
		JSONObject sendMess = new JSONObject();
		sendMess.put("type", "localId");
		if(roller != null){
			sendMess.put("content",roller.getID());
		}else{
			sendMess.put("content", -1);
		}
		return sendMess.toString();
	}
	
	public void acceptCommandMessage(String commandMessage){
		System.out.println("Accept a command");
		Command newCommand = CommandFactory.creatCommand(commandMessage, roller);
		try {
			if(commandList.size()!=0){
				commandList.take();
			}
			commandList.put(newCommand);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Interruted when wait put newCommand");
			e.printStackTrace();
		}
	}
	private class ExecuteCommandTask implements Runnable{
		@Override
		public void run(){
			// TODO Auto-generated method stub
			try{
				//执行命令
				while(!Thread.interrupted()){
					Command exeCommand = commandList.take();//线程安全
					//1//exeCommand.execute();//对roller对象进行了操作
					gameView.exeCommand(exeCommand);//执行命令，更新gameView中的对象
				}
			}catch(InterruptedException e){
				System.out.println("Interrupted when wait for newCommand!");
				e.printStackTrace();
			}
		}
	}
}
///：~
