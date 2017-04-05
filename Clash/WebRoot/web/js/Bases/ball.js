function ball(){
    drawBase.call(this);

    this.id = "";
    this.name = "";
    this.color = "";
    this.position = new position(0,0,0);//当前position
    this.nextPosition ;
    this.lastPosition = new position(0,0,0);
    this.radius = 4;
    //加的力
    this.power = 0;

}
(function(){
    var Super = function(){};
    Super.prototype = drawBase.prototype;
    ball.prototype = new Super();
})();
//小球位置更新，参数为目标位置和规定时间
ball.prototype.doUpdate = function(position) {
    if(this.position != position){
        console.log("do move ");
        this.position.x = position.x;
        this.position.y = position.y;
        this.position.z = position.z;
        this.core.position.x = this.position.x;
        this.core.position.y = this.position.y;
        this.core.position.z = this.position.z;
        var perRotation = 0.3;
        //网上找出的 矩阵旋转方案：http://stackoverflow.com/questions/11060734/how-to-rotate-a-3d-object-on-axis-three-js
        var xAxis = new THREE.Vector3(-1,1,0);
        rotateAroundWorldAxis(this.core,xAxis,Math.PI / 360);
    }

};
//get / set
ball.prototype.getPosition = function() {
    return this.position;
};
ball.prototype.setPosition = function(position) {
    this.position = position;
};
ball.prototype.getPower = function() {
    return this.power;
};
ball.prototype.setPower = function(power) {
    this.power = power;
};
ball.prototype.setRadius = function(radius) {
    this.radius = radius;
};
//更新服务器接收位置
ball.prototype.setNextPosition = function(position) {
    this.nextPosition = position;
};

//添加到scene
ball.prototype.draw = function(scene) {
    this.geometry = new THREE.SphereGeometry(this.radius, 40, 40);
    this.material = new THREE.MeshLambertMaterial({color: 0xd9d9d9});
    //初始小球，没有纹理
    // this.core = new THREE.Mesh(this.geometry,this.material);
    // this.core.position.x = this.position.getX();
    // this.core.position.y = this.position.getY();
    // this.core.position.z = this.position.getZ();
    // this.core.castShadow = true;
    //添加纹理的小球
    this.core = createMesh(new THREE.SphereGeometry(this.radius, 40, 40), "floor-wood.jpg");
    scene.add(this.core);
};

var rotObjectMatrix;
function rotateAroundObjectAxis(object, axis, radians) {
    rotObjectMatrix = new THREE.Matrix4();
    rotObjectMatrix.makeRotationAxis(axis.normalize(), radians);

    // old code for Three.JS pre r54:
    // object.matrix.multiplySelf(rotObjectMatrix);      // post-multiply
    // new code for Three.JS r55+:
    object.matrix.multiply(rotObjectMatrix);

    // old code for Three.js pre r49:
    // object.rotation.getRotationFromMatrix(object.matrix, object.scale);
    // old code for Three.js r50-r58:
    // object.rotation.setEulerFromRotationMatrix(object.matrix);
    // new code for Three.js r59+:
    object.rotation.setFromRotationMatrix(object.matrix);
}
var rotWorldMatrix;
// Rotate an object around an arbitrary axis in world space       
function rotateAroundWorldAxis(object, axis, radians) {
    rotWorldMatrix = new THREE.Matrix4();
    rotWorldMatrix.makeRotationAxis(axis.normalize(), radians);

    // old code for Three.JS pre r54:
    //  rotWorldMatrix.multiply(object.matrix);
    // new code for Three.JS r55+:
    rotWorldMatrix.multiply(object.matrix);                // pre-multiply

    object.matrix = rotWorldMatrix;

    // old code for Three.js pre r49:
    // object.rotation.getRotationFromMatrix(object.matrix, object.scale);
    // old code for Three.js pre r59:
    // object.rotation.setEulerFromRotationMatrix(object.matrix);
    // code for r59+:
    object.rotation.setFromRotationMatrix(object.matrix);
}