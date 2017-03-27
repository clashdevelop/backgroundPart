package com.kbzgame.service.gamebase;

import net.sf.json.JSONObject;

import com.kbzgame.utils.Point;

public class JsonVisitor {
	private Message message = new Message();
	public JsonVisitor(Roller roller){
		roller.acceptJsonVisitor(message);
	}
	
	public String toString(){
		JSONObject aBall = new JSONObject();
		aBall.put("id", message.getId());
		aBall.put("x", message.getPosition().getX());
		aBall.put("y", message.getPosition().getY());
		
		return aBall.toString();
	}
	public static class Message{
		private int id ;
		private Point position;
		private String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Point getPosition() {
			return position;
		}
		public void setPosition(Point position) {
			this.position = position;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String toString(){
			return ""+id+position+name;
		}
	}
}
