package com.kbzgame.service.command;

import com.kbzgame.service.gamebase.Roller;

import net.sf.json.JSONObject;

public class CommandFactory {
	public static Command creatCommand(String commandMessage,Roller reciver){
		JSONObject json = new JSONObject(commandMessage);
		String type = json.getString("type");
		if("mouse".equals(type)){
			JSONObject message = JSONObject.fromString(json.getString("content"));
			double x = message.getDouble("x");
			double y = message.getDouble("y");
			MouseCommand command = new MouseCommand(reciver,x,y);
			System.out.println("mouseCommand"+command);
			return command;
		}
		return null;
	}
}
