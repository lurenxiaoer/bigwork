// 一个很大的问题是，canvas的优化问题没有做，动画占用太多的内存，导致卡顿

let width = window.innerWidth;//返回窗口的文档显示区的宽高。
let height = window.innerHeight;// outerWidth 和 outerHeight 属性获取加上工具条与滚动条窗口的宽度与高度

let canvas = document.getElementById("canvas");
let ctx = canvas.getContext('2d');
canvas.width = width;//canvas的宽高一定要放在html中或者js中定义，css中定义的话，拿到的永远是它的默认宽高 300*150
canvas.height = height;


ctx.translate (canvas.width/2, canvas.height/2);//用w3c坐标系，移动坐标原点到网页中心

//设置另外一个canvas1用来实现渐变层的效果，这个canvas1并没有移动坐标原点
let canvas1 = document.getElementById("canvas1");
let ctx1 = canvas1.getContext('2d');
canvas1.width = width;//canvas的宽高一定要放在html中或者js中定义，css中定义的话，拿到的永远是它的默认宽高 300*150
canvas1.height = height;
let gnt1 = ctx1.createLinearGradient(0,0,width,height);//开始设置页面背景
gnt1.addColorStop(0,"rgb(232,233,224)");
gnt1.addColorStop(1,"rgba(147,144,206,.6)");
ctx1.fillStyle = gnt1;
ctx1.fillRect(0,0,width,height);//canvas的width和height也不能用百分比表示。canvas会将百分值当成数值显示

//依然对最开始的canvas做效果
let colors = ['114, 203, 219', '238, 63, 77','139, 38, 113' , '254, 195, 67', '239, 115, 81', '142, 220, 157', '97, 154, 195', '176, 213, 223'];// '160, 89, 107'

let layers = [];//结构组成为[层级，[对应层级上的点集合]]*10个
let points = [];//集成所有的位置

let toggleMouse = false;//初始化鼠标事件

for(let i = 1;i <= 10;i++){
	let objs = [];
	for(let j = 0;j < 20;j++){//随机各小球位置，颜色
		let x = rand(-width, width);
		let y = rand(-height, height)
		let color = colors[rand(0, colors.length)];
		
		objs.push(new obj(new circle(new point(x, y), i * 3, 
		'rgba(' + color +', '+ (i)/2 +')'), drawCircle));//小球x，y，半径i * 3，透明度 (i)/2，drawCircle声明画圆圈
		points.push(new point(x, y));//points集成了所有的点
	}
	layers.push(new layer(i/5, objs));//层级集成，i/x，x越大，层级越密集，总共十个层级，每个层级带一个obj，每个obj经上面的循环带20个点
}


let mouse = new point(0, 0);
let myParallax = new parallax(new point(1, 1), layers);//定义一个新的原点，附带层级和点的信息
let param = 0;
function animate(){
	clear();//清除原画布
	
    let fx = Math.sin(param * 4) * 200 + 150;//最好的三角函数表示方法应该是Math.sin(x*Math.PI/180)函数参数是弧度制
    let easing = 0.02;//缓动系数
    if(toggleMouse){//鼠标进入
        // myParallax.regPoint.x = mouse.x;
        // myParallax.regPoint.y = mouse.y;
        let vx = (mouse.x -  myParallax.regPoint.x) * easing;
        let vy = (mouse.y - myParallax.regPoint.y) * easing;
        myParallax.regPoint.x += vx;//鼠标进入后，各层级的点都跟随鼠标位置/2/4是为了制造一种偏差感，视觉效果更好
        myParallax.regPoint.y += vy;
    }else{//鼠标移出
        let vx = (Math.cos(param) * fx -  myParallax.regPoint.x) * easing;
        let vy = (Math.sin(param) * fx - myParallax.regPoint.y) * easing;
        myParallax.regPoint.x += vx;
        myParallax.regPoint.y += vy;
		param += 0.01;
    }
	
	drawParallax(myParallax);
	requestAnimationFrame(animate);//重复调用animate
}
animate();//首次调用

function drawParallax(myParallax){//参数来源new parallax(new point(1, 1), layers);带着原点和层级
	for(let i = 0;i < myParallax.layers.length;i++){
		let layerZindex = myParallax.layers[i].zindex;
		let thisObjs = myParallax.layers[i].objs;
		// let copyObjs = [];
		
		for(let j = 0;j < thisObjs.length;j++){
			let objCopy = copy(thisObjs[j]);
			objCopy.struct.pos = translate(objCopy.struct.pos, myParallax.regPoint);//移动
			objCopy.struct.pos = mult(objCopy.struct.pos, layerZindex);//放大
			thisObjs[j].drawFunc(objCopy.struct);
		}
	}
}
// 取整性能好一些的方法
// rounded = (0.5 + somenum) | 0;
// rounded = ~~ (0.5 + somenum);
// rounded = (0.5 + somenum) << 0;

/////////////

function point(x, y){//定x，y
	this.x = x;
	this.y = y;
}

function translate(pointA, pointB){//两点，输出新的位置
	return new point(pointA.x + pointB.x, pointA.y + pointB.y+10);
}

function mult(pointA, n){//放大点的x，y
	return new point(pointA.x * n, pointA.y * n);
}

function layer(zindex, objs){//层级
	this.zindex = zindex;
	this.objs = objs;
}

function parallax(regPoint, layers){//集成层级和奇点，这里的regPoint由new point(x,y)定义
	this.regPoint = regPoint;
	this.layers = layers;
}


function copy(src){//得到信息并批量处理
	// Deep copy hack
	var str = JSON.stringify(src);
	eval("var dst = " + str);
	return dst;
}

function rect(pos, width, height, color){//方块
	this.pos = pos;
	this.width = width;
	this.height = height;
	this.color = color;
}

function circle(pos, rad, color){//圆圈
	this.pos = pos;
	this.rad = rad;
	this.color = color;
}

function obj(struct, drawFunc){//？
	this.struct = struct;
	this.drawFunc = drawFunc;
}

function complexObj(refPoint, objs){//？
	this.refPoint = refPoint;
    this.objs = objs;
}

function clear(){//一种是重新用方块覆盖原图，一种是单纯直接清除画面
	ctx.fillStyle = "rgba(255, 255, 255, 0.6)";//用第一种方法可以设置背景颜色
	//ctx.clearRect(-canvas.width/2, -canvas.height/2, canvas.width, canvas.height);
	ctx.fillRect(-canvas.width/2, -canvas.height/2, canvas.width, canvas.height);
}

function iterate(objs, func){//迭代，首参参数对象，二参执行函数
	for(let i = 0;i < objs.length;i++){
		func(objs[i]);
	}
}

function dir(pointA, pointB){//atan，atan2是有区别的，前者对应两种可能的角度，后者只有一种角度，这个函数常用来做鼠标跟随效果。求两点间夹角计算
	return -Math.atan2(pointB.x - pointA.x, pointB.y - pointA.y) + Math.PI/2;//atan2的入参很特殊，应该是atan2(dy,dx),这里用反了就要用角度修正
}

function dist(pointA, pointB){//求两点间的距离
	return Math.sqrt((pointA.x - pointB.x) * (pointA.x - pointB.x) + (pointA.y - pointB.y) * (pointA.y - pointB.y));
}

function rand(min, max){//两值间的随机数
	let dist = max - min;
	return Math.floor(Math.random() * dist) + min;
}

function drawCircle(circle){//画圆
	ctx.beginPath();
    
    ctx.arc(circle.pos.x, circle.pos.y, circle.rad, 0, 2 * Math.PI, false);//这句一定放在stroke或者fill之前，（x，y，半径，开始角度，结束角度（均为弧度），是否顺时针（true为逆，默认false顺时针））
    // ctx.strokeStyle = circle.color || "#ff0000";
    // ctx.stroke();//描边搭配strokeStyle
    ctx.fillStyle = circle.color || "#ff0000";
    ctx.fill();//填充
    
	ctx.closePath();
}

function drawRect(rect){//画方
	ctx.fillStyle = rect.color || "#ff0000";
	ctx.fillRect(rect.pos.x, rect.pos.y, rect.width, -rect.height);
}

function drawLine(pointA, pointB, size, color){//画线
	ctx.strokeStyle = color;
	ctx.lineWidth = size;
	ctx.beginPath();
	ctx.moveTo(pointA.x, pointA.y);
	ctx.lineTo(pointB.x, pointB.y);
	ctx.stroke();
	ctx.closePath();
}

canvas.addEventListener('mousemove', function(e){//一个是到左右两端的距离，一个是w3c坐标系，目前原点在页面中心
	mouse.x = e.clientX - canvas.width/2;//clientX点击位置距离当前body可视区域的x，y坐标
	mouse.y = e.clientY - canvas.height/2;
});

canvas.addEventListener('mouseover', function(e){
	toggleMouse = true;
});

canvas.addEventListener('mouseout', function(e){
	toggleMouse = false;
});


// Curve曲线运动

function mkCurve(curveForm){
	var localTimeStep = 5;
	var curveRes = [];
	
	for(var localTime = 0;localTime <= 100;localTime += localTimeStep){
		var myPointArr = curveForm;
		while(myPointArr.length > 1){
			var updateArr = [];
			for(var i = 1;i < myPointArr.length;i++){
				updateArr.push(getCurvePoint(myPointArr[i - 1], myPointArr[i], localTime));
				
				var color = "#00ffff";
				if(Math.abs(iAnim - i) < 5){
					color = "#FF0080";
				}
				if(curveRes.length == 2){
					drawLine(myPointArr[i - 1], myPointArr[i], 0.3, color);
				}
			}
			myPointArr = updateArr;
		}
		curveRes.push(myPointArr[0]);
	}
	iAnim = (iAnim + 1) % 50;
	
	return curveRes;
}

function drawLines(curveData, color, size){
	for(var i = 1;i < curveData.length;i++){
		drawLine(curveData[i - 1], curveData[i], size, color);
	}
}