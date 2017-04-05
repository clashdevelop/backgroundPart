function camera(){
    this.height = 60;
    this.position = new position(0,0,60);
    this.lookAtPosition = new position(0,0,0);
    this.Camera ;//需要返回给renderer的变量
}
camera.prototype.setPosition = function(position) {
    this.position = position;
};
camera.prototype.setLookAt = function(position) {
    this.lookAtPosition = position;
};
camera.prototype.getCamera = function() {
    this.Camera = new THREE.PerspectiveCamera(45
                    ,window.innerWidth / window.innerHeight
                    ,0.1,1000);
    this.Camera.position.x = this.position.getX();
    this.Camera.position.y = this.position.getY();
    this.Camera.position.z = this.height;
    this.Camera.lookAt(this.lookAtPosition);
    return this.Camera;
};