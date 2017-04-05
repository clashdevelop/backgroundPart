function position(x,y,z){
    this.x = x;
    this.y = y;
    this.z = z;
}
position.prototype.getX = function() {
    return this.x;
};
position.prototype.getY = function() {
    return this.y;
};
position.prototype.getZ = function() {
    return this.z;
};
position.prototype.setX = function(x) {
	this.x = x;
};
position.prototype.setY = function(y) {
	this.y = y;
};
position.prototype.setZ = function(z) {
	this.z = z
};
position.prototype.setAll = function(x,y,z) {
	this.x = x;
	this.y = y;
	this.z = z;
};