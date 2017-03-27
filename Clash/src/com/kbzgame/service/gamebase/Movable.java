package com.kbzgame.service.gamebase;

import com.kbzgame.utils.Shape;
import com.kbzgame.utils.Vector;

public interface Movable {
	void move();
	Shape getShape();
	void outAction(Vector backVector);
	boolean alive();
}
