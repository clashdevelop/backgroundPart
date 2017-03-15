package com.kbzgame.socket;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/clash")
public class GameSocket {//每一个用户都有一个此类的对象
	private static Set<GameSocket> plyerSet= new HashSet<GameSocket>();
	
	
	private Session session;
	
	@OnOpen//新用户加入
	public void onOpen(Session session){
		this.session = session;
	}
	@OnClose//用户断开连接
	public void onClose(){
		
	}
	@OnMessage//收到用户的调用方法
	public void onMassage(String massage,Session session){
		
	}
	@OnError
	public void onError(Session session,Throwable error){
		
	}
}
