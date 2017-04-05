/*
作为基础物体的绘制的基类
 */
function drawBase(){
    this.position;
    this.core;
    this.geometry;
    this.meterial;
}
drawBase.prototype.setPosition = function(position) {
    this.position = position;
};
drawBase.prototype.getCore = function() {
    return this.core;
};
drawBase.prototype.getPosition = function(){
    return this.position;
}
drawBase.prototype.createMesh = function(geom, imageFile) {
    var loader = new THREE.DDSLoader();
    var texture = loader.load('../assets/textures/seafloor.dds');
    var mat = new THREE.MeshPhongMaterial();
    mat.map = texture;

    var mesh = new THREE.Mesh(geom, mat);
    return mesh;
};