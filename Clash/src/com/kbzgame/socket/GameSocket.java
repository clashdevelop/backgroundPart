package com.kbzgame.socket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.kbzgame.controller.GameView;
import com.kbzgame.controller.Gamepad;

@ServerEndpoint("/clash")
public class GameSocket {//每一个用户都有一个此类的对象
	private static CopyOnWriteArraySet<GameSocket> playerSet= new CopyOnWriteArraySet<GameSocket>();
	private static GameView gameView = new GameView();
	
	private ExecutorService taskmanager = Executors.newCachedThreadPool();
	private Session session;
	private Gamepad gamepad;
	
	
	@OnOpen//新用户加入
	public void onOpen(Session session){
		this.session = session;
		playerSet.add(this);//线程安全
		
		gamepad = new Gamepad(gameView);
		System.out.println("玩家加一");
		gamepad.joinGame("Bob");
		sendMessage(gamepad.getGamePadId());//发送玩家的id
		taskmanager.execute(new sendMessageTask());//启动读取信息线程
	}
	@OnClose//用户断开连接
	public void onClose(){
		playerSet.remove(this);
		
		System.out.println("玩家减一");
		gamepad.quitGame();
		taskmanager.shutdownNow();
	}
	@OnMessage//收到用户的调用方法
	public void onMassage(String message,Session session){
		System.out.println("Accept message"+message);
		gamepad.acceptCommandMessage(message);
	}
	@OnError
	public void onError(Session session,Throwable error){
		System.out.println("Error");
		
		System.out.println("玩家减一");
		gamepad.quitGame();
		taskmanager.shutdownNow();
	}
	
	private void sendMessage(String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("****Error when send message****");
			e.printStackTrace();
		}
	}
	
	private void notifyAllPlayer(String message){
		for(GameSocket player:playerSet){
			player.sendMessage(message);
		}
	
	}
	private class sendMessageTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				while(!Thread.interrupted()){
					String message = gameView.getMessage();
					sendMessage(message);
					TimeUnit.MILLISECONDS.sleep(200);//200毫秒
				}
			}catch(InterruptedException e){
				//System.out.println("exit from interruptedException");
			}
		}
	}
}
