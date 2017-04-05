var scene = new THREE.Scene();
var renderer = new THREE.WebGLRenderer({antialias:true});//生成渲染器对象（属性：抗锯齿效果为设置有效）
renderer.setClearColor(new THREE.Color(0xEEEEEE, 1.0));
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.shadowMapEnabled = true;

//添加球体
var aBall = new ball();
aBall.draw(scene);

// var floorTex = THREE.ImageUtils.loadTexture("../assets/textures/general/floor-wood.jpg");
// var plane = new THREE.Mesh(new THREE.BoxGeometry(200, 100, -1, -0.9), new THREE.MeshPhongMaterial({
//     color: 0x3c3c3c,
//     map: floorTex
// }));
// scene.add(plane);

//添加鼠标的直线
var myMouse = new mouse();
// myMouse.initPosition();
myMouse.draw(scene);

//添加坐标轴
var axes = new THREE.AxisHelper(20);
scene.add(axes);

//添加参考中心
/*var center = new ball();
center.setPosition(new position(0,0,0));
center.setRadius(2);
center.draw(scene);*/

//添加灯光
var spotLight = new THREE.DirectionalLight(0xffffff);
spotLight.position.set(-40, 60, 100);
// spotLight.castShadow = true;
scene.add(spotLight);

// add the output of the renderer to the html element
document.getElementById("WebGL-output").appendChild(renderer.domElement);
var Camera = new camera();
Camera.setLookAt(aBall.getPosition());

//定义计时器
var time = 0;
//规定每次接收位置消息
var receiveTime = 1;
function renderScene() {
	//引入系统更新方法
    time = systemUpdate(time,receiveTime);//Control/updateControl.js
    Camera.setPosition(aBall.getPosition());
    // render using requestAnimationFrame
    requestAnimationFrame(renderScene);
    renderer.render(scene,Camera.getCamera());
}
renderScene();
