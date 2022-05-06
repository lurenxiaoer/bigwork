// 手写简易轮播图swiper代码
// 图片全都加载完毕，动态更改css的方式
// js轮播图的动效都是通过控制obj.style来实现的，如display none切换，opacity渐变 z-index次序等
document.addEventListener("DOMContentLoaded", () => {
    let preBt = document.getElementById("pre");
    let nexBt = document.getElementById("nex");
    let imgList = document.getElementById("imgList");// 图片集ul
    let imgWidth = imgList.querySelector("li img").offsetWidth;//需要window.onload
    let imgListLi = imgList.querySelectorAll("li");
    let roundTime = 2000;//轮播一次的时间，单位ms
    //如果在html节点中自己手动加入首尾轮播图片的复制品的话，就不需要这小截了
    let cloneFirstLi = imgListLi[0].cloneNode(true);
    imgList.appendChild(cloneFirstLi);
    let cloneLastLi = imgListLi[imgListLi.length-1].cloneNode(true);
    imgList.prepend(cloneLastLi);
    // imgList.insertBefore(cloneLastLi,ali[0]);
    let imgNum = imgList.querySelectorAll("li").length;//重新计算插入新节点后，li标签的数量

    //按钮集,y因为设计的结构里，per和nex控制图标也包含在里头，所以要排除他们两，即去掉首尾
    // Array.from（类数组）返回的就是真数组
    let buttons = Array.from(document.getElementById("buttonList").getElementsByTagName("li")).slice(1,6);//共5个dom
    // console.log("preBt:"+preBt);
    // console.log("nexBt:"+nexBt);
    // console.log("imgList:"+imgList);
    // console.log("buttons:"+buttons);
    // console.log("图片应该宽为："+imgWidth,"轮播数量为："+imgNum);
    let cur = 1;//自动轮播默认位置从cur=1开始,首尾各加了一张图，所以从第二张图显示
    let startAnimationFlag  = false;//完成移动
    let offset;
    imgList.style.left = -imgWidth+"px";//集合图片的初始位置，已在css中初始化
    

    //下面一些集合主要是为了实现侧边文章内容和轮播图片契合
    let swiperArticle = document.getElementsByClassName("swiper-article");//它的切换index和button的一样的
    function animate(curIndex,nextIndex){

        if(startAnimationFlag) return;//状态判断，控制是否可以在运动状态下也能下发新的轮播任务
        console.log("animateStart");

        // 常规动画状态
        imgList.style.transitionTimingFunction = "ease-in-out";
        imgList.style.transitionDuration = ".8s";//这个动画时间一定要小于或等于轮播任务时间

        // 计算距离并移动，轮播图的各种效果也在此处执行
        offset = (nextIndex - curIndex)*imgWidth;//每张图片宽700，目标索引和当前索引图片相隔总跨度
        let curPosition = parseFloat(imgList.style.left);//总图当前的位置
        let nextPosition = curPosition - offset;//更新下一个次迭代器初始状态为当前状态加步长，下面马上把位置移过去
        imgList.style.left = nextPosition + "px";
        startAnimationFlag = true;//移动状态标记，在移动状态期间不能触发新的计时任务，不然会出现严重的抖动
        console.log("执行前cur："+ cur );

        // buttonsList当前类的切换
        let handleIndexPre,handleIndexNex;//curIndex取到0或6时的值跳转处理，避免出错(五张图轮播，加上首尾各加的缓冲图共七张)
        curIndex === 0 ? handleIndexPre=imgNum-2 : curIndex === imgNum-1 ? handleIndexPre=1 : handleIndexPre=curIndex;
        nextIndex === 0 ? handleIndexNex=imgNum-2 : nextIndex === imgNum-1 ? handleIndexNex=1 : handleIndexNex=nextIndex;
        buttons[handleIndexPre-1].classList.toggle("curIndex");//curIndex可能取到0-6，而buttons的index只有0-4分别对应curindex的1-5
        buttons[handleIndexNex-1].classList.toggle("curIndex");

        //侧边文章的序号和buttons的序号是一样的
        swiperArticle[handleIndexPre-1].style.transitionTimingFunction = "step-end";
        swiperArticle[handleIndexPre-1].style.transitionDuration = "0s";
        swiperArticle[handleIndexPre-1].style.transitionTimingFunction = "ease-in-out"
        swiperArticle[handleIndexNex-1].style.transitionDuration = "1.5s";
        swiperArticle[handleIndexPre-1].classList.toggle("hidden");//display对dom操作太多，而且不能和transition配合，visibility优一些
        swiperArticle[handleIndexNex-1].classList.toggle("hidden");//opacity控制显隐动画效果要比visibility要更好

        // 移动完毕，更新cur的值，注意是   先移后变，
        cur = nextIndex;//位置放在这一区块的最后面，因为前面部分代码需要用到cur的值
        // console.log("后cur："+ cur );
        // console.log("imgList.style.width 当前位置："+ imgList.style.left);
        // console.log("运行完毕，进行下一次轮播运动");

        // 移动任务完成后触发监听器，清除之前的任务，添加新的任务
        imgList.addEventListener("transitionend",() => {// 一定要在css总加上transition的至少一个属性，不然添加的transitionend事件不会起作用的
            clearInterval(rotationTimer);//每次transition完毕后都要清除之前所有的任务，不然会爆
            if(cur === imgNum-1){//末页跳转到首页平稳过渡，移动完成后判定cur
                imgList.style.transitionTimingFunction = "step-end";
                imgList.style.transitionDuration = "0s";//这个动画时间一定要小于轮播任务时间
                imgList.style.left = -imgWidth + "px";
                cur = 1;
            }
            if(cur === 0){//首页转到末页平稳过渡
                imgList.style.transitionTimingFunction = "step-end";
                imgList.style.transitionDuration = "0s";//这个动画时间一定要小于轮播任务时间
                imgList.style.left = -imgWidth * 5 + "px";
                cur = 5;
            }
            rotationTimer = setInterval(() => {
                // cur === imgNum ? imgList.left = 0 :
                animate(cur,(cur+1));//已经到想去的位置了，就从当前位置启动自动轮播任务
            },roundTime);//与初始轮播任务相同，要大于transition-duration
            startAnimationFlag = false;
        })
    };

    
    // 下一张
    
    nexBt.addEventListener("click",() => {
        let nextIndex;//nextIndex的值还是要根据当前的cur值来判断的
        cur === imgNum-1 ? nextIndex = 1 : nextIndex = cur + 1;
        animate(cur,nextIndex);
    },false)   
    
    // 上一张
    preBt.addEventListener("click",() => {
        let nextIndex;
        cur === 0 ? nextIndex = imgNum-2 : nextIndex = cur - 1;//前后启动触发按钮一定要考虑到cur为0 或者6两种特殊情况
        animate(cur,nextIndex);
    },false)    

    // 分页器
    for(let i=1;i<imgNum-1;i++){
        buttons[i-1].addEventListener("click",() => {
            animate(cur,i);
        })
    }

    // 页面加载完毕自动播放，默认触发一次animate任务
    rotationTimer = setInterval(() => {
        animate(cur,(cur+1));
    },roundTime);
})
