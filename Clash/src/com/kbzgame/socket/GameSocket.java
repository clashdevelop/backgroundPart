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
public class GameSocket {//ÿһ���û�����һ������Ķ���
	private static Set<GameSocket> plyerSet= new HashSet<GameSocket>();
	
	
	private Session session;
	
	@OnOpen//���û�����
	public void onOpen(Session session){
		this.session = session;
	}
	@OnClose//�û��Ͽ�����
	public void onClose(){
		
	}
	@OnMessage//�յ��û��ĵ��÷���
	public void onMassage(String massage,Session session){
		
	}
	@OnError
	public void onError(Session session,Throwable error){
		
	}
}
