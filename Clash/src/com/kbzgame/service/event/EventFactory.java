package com.kbzgame.service.event;

import com.kbzgame.service.gamebase.Crashable;
import com.kbzgame.service.gamebase.Movable;

public class EventFactory {
	public static Event creatEvent(Crashable crasherA,Crashable crasherB){
		return new CrashEvent(crasherA,crasherB);
	}
	public static Event creatEvent(Movable movable){
		return new OutsideEvent(movable);
	}
}
