/*
	
*/
function systemUpdate(time,receiveTime){

	//小球运动,参数为目标位置和规定时间
	var perLength = 0.02;
	var radox = Math.random() * perLength;
	var radoy = Math.random() * perLength;
	var ballPosi = aBall.position;
	ballPosi.x += perLength;
	ballPosi.y += perLength;
	aBall.doUpdate(new position(ballPosi.x,ballPosi.y,0));
    //及时更改鼠标中心，将小球的位置传入
    myMouse.doUpdate(aBall.position);

    return time>=receiveTime?0:++time;
}