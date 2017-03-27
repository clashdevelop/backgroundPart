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
public class GameSocket {//ÿһ���û�����һ������Ķ���
	private static CopyOnWriteArraySet<GameSocket> playerSet= new CopyOnWriteArraySet<GameSocket>();
	private static GameView gameView = new GameView();
	
	private ExecutorService taskmanager = Executors.newCachedThreadPool();
	private Session session;
	private Gamepad gamepad;
	
	
	@OnOpen//���û�����
	public void onOpen(Session session){
		this.session = session;
		playerSet.add(this);//�̰߳�ȫ
		
		gamepad = new Gamepad(gameView);
		System.out.println("��Ҽ�һ");
		gamepad.joinGame("Bob");
		taskmanager.execute(new sendMessageTask());//������ȡ��Ϣ�߳�
	}
	@OnClose//�û��Ͽ�����
	public void onClose(){
		playerSet.remove(this);
		
		System.out.println("��Ҽ�һ");
		gamepad.quitGame();
		taskmanager.shutdownNow();
	}
	@OnMessage//�յ��û��ĵ��÷���
	public void onMassage(String message,Session session){
		System.out.println("Accept message"+message);
		gamepad.acceptCommandMessage(message);
	}
	@OnError
	public void onError(Session session,Throwable error){
		System.out.println("Error");
		gamepad.quitGame();
		taskmanager.shutdownNow();
	}
	
	private void sendMessage(String message) throws IOException{
		session.getBasicRemote().sendText(message);
	}
	
	private void notifyAllPlayer(String message){
		for(GameSocket player:playerSet){
			try {
				player.sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error on onMessage");
				e.printStackTrace();
			}
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
					TimeUnit.MILLISECONDS.sleep(200);//200����
				}
			}catch(InterruptedException e){
				//System.out.println("exit from interruptedException");
			}catch(IOException e){
				System.out.println("erro when send message");
			}
		}
	}
}
