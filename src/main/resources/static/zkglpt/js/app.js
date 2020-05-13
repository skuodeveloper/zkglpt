var IMAGE_SERVER_URL = 'http://60.190.149.52:8096';

//px转换为rem
(function(doc, win) {
	var docEl = doc.documentElement,
		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
		recalc = function() {
			var clientWidth = docEl.clientWidth;
			if(!clientWidth) return;
			if(clientWidth >= 640) {
				docEl.style.fontSize = '100px';
			} else {
				docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
			}
		};

	if(!doc.addEventListener) return;
	win.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);

function IdCodeValid(code) {
	//身份证号合法性验证
	//支持15位和18位身份证号
	//支持地址编码、出生日期、校验位验证
	var city = {
		11: "北京",
		12: "天津",
		13: "河北",
		14: "山西",
		15: "内蒙古",
		21: "辽宁",
		22: "吉林",
		23: "黑龙江 ",
		31: "上海",
		32: "江苏",
		33: "浙江",
		34: "安徽",
		35: "福建",
		36: "江西",
		37: "山东",
		41: "河南",
		42: "湖北 ",
		43: "湖南",
		44: "广东",
		45: "广西",
		46: "海南",
		50: "重庆",
		51: "四川",
		52: "贵州",
		53: "云南",
		54: "西藏 ",
		61: "陕西",
		62: "甘肃",
		63: "青海",
		64: "宁夏",
		65: "新疆",
		71: "台湾",
		81: "香港",
		82: "澳门",
		91: "国外 "
	};
	var row = {
		'pass': true,
		'msg': '验证成功'
	};
	if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|[xX])$/.test(code)) {
		row = {
			'pass': false,
			'msg': '身份证号格式错误'
		};
	} else if(!city[code.substr(0, 2)]) {
		row = {
			'pass': false,
			'msg': '身份证号地址编码错误'
		};
	} else {
		//18位身份证需要验证最后一位校验位
		if(code.length == 18) {
			code = code.split('');
			//∑(ai×Wi)(mod 11)
			//加权因子
			var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
			//校验位
			var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
			var sum = 0;
			var ai = 0;
			var wi = 0;
			for(var i = 0; i < 17; i++) {
				ai = code[i];
				wi = factor[i];
				sum += ai * wi;
			}
			if(parity[sum % 11] != code[17].toUpperCase()) {
				row = {
					'pass': false,
					'msg': '身份证号校验位错误'
				};
			}
		}
	}
	return row;
}

function imgChange(obj1, obj2, idx) {
	//				$('#div1 .z_addImg').filter(function(){ return $(this).css('display')!='none';}).length;
	switch(idx) {
		case 0:
			var imgCnt = mui('#div1 .z_addImg.show img').length;
			//						mui('#div1 .z_addImg.show img')[0].getAttribute('src');

			if(imgCnt > 5) {
				mui.alert('物品信息最多只能上传6张图片！');
				return;
			}
			break;
		case 1:
			var imgCnt = mui('#div2 .z_addImg.show').length;
			if(imgCnt > 0) {
				mui.alert('最多只能上传一张人员照片');
				return;
			}
			break;
	}

	//获取点击的文本框
	var file = document.getElementById(obj2);
	//存放图片的父级元素
	var imgContainer = document.getElementsByClassName(obj1)[idx];
	//获取的图片文件
	var fileList = file.files;
	//遍历获取到得图片文件
	for(var i = 0; i < fileList.length; i++) {
		imgBase64Get(file.files[i], imgContainer, imgUrlGet);
	};
};

/*
 * 获取上传图片的Base64编码
 */
function imgBase64Get(file, imgContainer, callback) {
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		callback(imgContainer, this.result);
	}
}

/*
 * 上传图片并获取URL
 */
function imgUrlGet(imgContainer, base64) {
	dealImage(base64, 500, imgContainer, useImg);
}

function imgRemove() {
	var imgList = mui('.z_addImg');
	for(var j = 0; j < imgList.length; j++) {
		imgList[j].index = j;
		imgList[j].onclick = function() {
			var t = this;
			mui.confirm("确定要删除这张图片吗?", function(e) {
				if(e.index == 1) {
					//点确定了
					t.style.display = "none";
					t.classList.remove('show');
				}
			});
		}
	};
};

//然后一压 再打个桩看下长度 方法名随便起怎么舒服怎么来
function useImg(imgContainer, base64) {
	var url = 'http://60.190.149.52:8096/zkglxt/api/file/upload';
	var fileExtension = base64.split(';')[0].split('/')[1];
	console.log(fileExtension);

	$.ajax({
		type: 'post',
		url: url, // ajax请求路径
		data: {
			"base64": base64,
			"fileExtension": fileExtension
		},
		success: function(data) {
			var imgUrl = 'http://60.190.149.52:8096' + data.data;
			var img = document.createElement("img");
			img.setAttribute("src", imgUrl);
			var imgAdd = document.createElement("div");
			imgAdd.setAttribute("class", "z_addImg");
			imgAdd.classList.add('show');
			imgAdd.appendChild(img);
			imgContainer.appendChild(imgAdd);
			imgRemove();
		}
	});
}

//压缩方法
function dealImage(base64, w, imgContainer, callback) {
	var newImage = new Image();
	var quality = 0.6; //压缩系数0-1之间
	newImage.src = base64;
	newImage.setAttribute("crossOrigin", 'Anonymous'); //url为外域时需要
	var imgWidth, imgHeight;
	newImage.onload = function() {
		imgWidth = this.width;
		imgHeight = this.height;
		var canvas = document.createElement("canvas");
		var ctx = canvas.getContext("2d");
		if(Math.max(imgWidth, imgHeight) > w) {
			if(imgWidth > imgHeight) {
				canvas.width = w;
				canvas.height = w * imgHeight / imgWidth;
			} else {
				canvas.height = w;
				canvas.width = w * imgWidth / imgHeight;
			}
		} else {
			canvas.width = imgWidth;
			canvas.height = imgHeight;
			quality = 0.6;
		}
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		ctx.drawImage(this, 0, 0, canvas.width, canvas.height);
		var base64 = canvas.toDataURL("image/jpeg", quality); //压缩语句
		// 如想确保图片压缩到自己想要的尺寸,如要求在50-150kb之间，请加以下语句，quality初始值根据情况自定
		// while (base64.length / 1024 > 150) {
		// 	quality -= 0.01;
		// 	base64 = canvas.toDataURL("image/jpeg", quality);
		// }
		// 防止最后一次压缩低于最低尺寸，只要quality递减合理，无需考虑
		// while (base64.length / 1024 < 50) {
		// 	quality += 0.001;
		// 	base64 = canvas.toDataURL("image/jpeg", quality);
		// }
		callback(imgContainer, base64); //必须通过回调函数返回，否则无法及时拿到该值
	}
}