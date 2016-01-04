var setPwdTpl='<div class="set-pwd">'+
				'<div class="set-p-tit">设置新密码</div>'+
					'<div class="set-main">'+
				'<div class="content1" style="display:block;">'+
					'<form id="inputForm" >'+
						'<div class="row-item"><label for="old-pwd">初始密码：</label><input type="password" name="oldPassword" id="old-pwd" value="" /></div>'+
						'<div class="row-item"><label for="new-pwd">新密码：</label><input type="password" name="newPassword" id="new-pwd" value="" /></div>'+
						'<div class="row-item"><label for="confirm-pwd">确认新密码：</label><input type="password" name="rePassword" id="confirm-pwd" value="" /></div>'+
						'<input type="button" name="" class="btn" id="submit-btn" value="保存" />'+
					'</form></div>'+
				'<div class="content2" style="display: none;"><p>密码修改成功</p><input type="button" class="btn" id="set-btn-close" value="关 闭" /></div>'+
			'</div></div>';
$(function(){
	$('input,textarea').placeholder(); 
	//左侧菜单事件
	$(".fir-menu").hover(function() {
		if(!$(this).hasClass("select-a")){
			$(this).addClass("active-a").find(".sen-menu").show().siblings(".tri-icon").show();
		}else{
			$(this).find(".sen-menu").show().siblings(".tri-icon").show();
		}
	},function() {
		if(!$(this).hasClass("select-a")){
			$(this).removeClass("active-a").find(".sen-menu").hide().siblings(".tri-icon").hide();
		}else{
			$(this).find(".sen-menu").hide().siblings(".tri-icon").hide();
		}
	}).click(function() {
		if($(this).hasClass("home-menu")){
			window.location.href=$(this).attr("data-url");
		}else{			
			$(this).addClass("active-a select-a").siblings().removeClass('active-a select-a').find(".sen-menu").hide();
		}
	});

	creatEdit($(".com-edit"));
	$(".product-tvalue .com-radio2").change(function(){
		var _index=$(".product-tvalue").index($(this).parent());
		var _tpl=$(".tpl-con .tpl-html");
		var _html=_tpl.eq(_index).find("tr").clone();
		var _table=$(".com-table");
		_table.find(".tpl-add").remove();
		_table.find(".last-tr").after(_html);
		var _uid=_html.find(".com-edit")
		_uid.each(function(index) {
			var _id=$(this).attr("id").slice(0,5);
			$(this).attr("id",_id);
			creatEdit(_uid);
		});
		
	});
   //错误提示
	function showTips(obj){
		if(!obj){
			return;
		}
		var tpl='<div class="error_tips">'+obj+'</div>';
		$("body").append(tpl);
		clearTimeout(window.timer);
		window.timer=setTimeout(function () {
			$(".error_tips").remove();
		},1500);
	}
	$("#phoneNum").keyup(function(){
		this.value=this.value.replace(/\D/g,'');
	}).blur(function(){
		if($.trim(this.value)!=""){
			var reg=/^1[3-9]\d{9}$/;
			if(!reg.test(this.value)){
				showTips('请输入有效的手机号码！');
				return false;
			}
		}
	});

	$("#userCard").blur(function(){
		if($.trim(this.value)!=""){
			var reg=/(^\d{15}$)|(^\d{17}(\d|X|x)$)/;
			if(!reg.test(this.value)){
				showTips('请输入有效的身份证号码！');
				return false;
			}
		}
	});
	$("#toPageNum").keyup(function(){
		this.value=this.value.replace(/\D/g,'');
	});
    
    
    $(".login-btn").click(function(){
		window.location.href='index.html';
	});
	$(".logout-btn").click(function(){
		layer.confirm('确定要退出吗？', {
		    btn: ['确定','取消'] 
		}, function(){
		   window.location.href='login.html';
		});
	});
	
	$(".com-blue-text").bind("click",function(){
		if($(this).hasClass("frozen")){
			layer.confirm('是否确认对此用户进行冻结操作？？', {
			    btn: ['确定','取消'] 
			}, function(index){
			   alert("冻结！");
			   layer.close(index);
			});
		}else if($(this).hasClass("off-frozen")){
			layer.confirm('是否确认对此用户进行解冻操作？？', {
			    btn: ['确定','取消'] 
			}, function(index){
			   alert("解冻！");
			   layer.close(index);
			});
		}
		
	});
	$(".com-blue-text").bind("click",function(){
		if($(this).hasClass("frozen")){
			layer.confirm('是否确认对此用户进行冻结操作？？', {
			    btn: ['确定','取消'] 
			}, function(index){
			   alert("冻结！");
			   layer.close(index);
			});
		}else if($(this).hasClass("off-frozen")){
			layer.confirm('是否确认对此用户进行解冻操作？？', {
			    btn: ['确定','取消'] 
			}, function(index){
			   alert("解冻！");
			   layer.close(index);
			});
		}else if($(this).hasClass("shelf-view")){
			window.location.href="productDingQiInfo.html";
		}else if($(this).hasClass("shelf-edit")){
			window.location.href="productDingQiEdit.html";
		}else if($(this).hasClass("shelf-on")){
			layer.confirm('上架成功', {
			    btn: ['关闭'] 
			}, function(index){
			   layer.close(index);
			});
		}else if($(this).hasClass("shelf-off")){
			layer.confirm('下架成功', {
			    btn: ['关闭'] 
			}, function(index){
			   layer.close(index);
			});
		}
	});
	
	
});

function creatEdit($obj){
	$obj.each(function(index){
		var _id=$(this).attr("id");
		UE.getEditor(_id, {
			autoClearinitialContent: false, //focus时自动清空初始化时的内容
			wordCount: false, //关闭字数统计
			elementPathEnabled: false //关闭elementPath
		});
	});
}

function openCalendar(){
	  J.calendar.Show();
}

//设置密码框弹出层
function showSetPwd(){
	var oBody=$("body");
	$("<div class='layer-bg'></div>").appendTo(oBody).show(200);
	$(setPwdTpl).appendTo(oBody).animate({top:'50%'},1000);
}
function showTips(obj){
    if(!obj){
        return;
    }
    var tpl='<div class="error_tips">'+obj+'</div>';
    $("body").append(tpl);
    clearTimeout(window.timer);
    window.timer=setTimeout(function () {
        $(".error_tips").remove();
    },1500);
}