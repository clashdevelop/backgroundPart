package com.kbzgame.service.gamebase;


import com.kbzgame.utils.Vector;

public interface Crashable extends Movable {
	Vector getV();
	int getM();
	void crashAction(Vector F);
}
