<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="renderer" content="webkit|chrome">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<script type="text/javascript" src="${base}/static/js/jquery-1.8.3.min.js"></script>
		<title>信达后台管理中心</title>
		<script type="text/javascript">
			$(function(){ 
				var isUpdatePwd = '${admin.isUpdatePwd}';
				if(isUpdatePwd=='0'){
					showSetPwd();
				}  
				$("#submit-btn").bind("click",function(){
					var $inputForm = $("#inputForm");
					var $oldPassword = $("input[name=oldPassword]");
					var $newPassword = $("input[name=newPassword]");
					var $rePassword = $("input[name=rePassword]");
					
					if($oldPassword.val()==''){
						showTips('请输入旧密码');
						return false;
					}
					if($newPassword.val()==''){
						showTips('请输入新密码！');
						return false;
					}
					if($newPassword.val().length<6||$newPassword.val().length>15){
						showTips('请输入6-16位长度的密码！');
						return false;
					}
					if($newPassword.val()!=$rePassword.val()){
						showTips('确认密码和新密码不一致！');
						return false;
					}
					$.ajax({
						url: "${base}/user/updatePassword.jhtml",
						type: "POST",
						data: $inputForm.serialize(),
						dataType: "json",
						cache: false,
						success: function(data) {
							if (data.message.type == "success") {
								$(".content2").show(500).siblings(".content1").remove();
							} else {
								showTips(data.message.content);
							}
						}
					});
				});
				$("#set-btn-close").bind("click",function(){
					$(".set-pwd").animate({top:'-50%'},600,function(){
						$(".layer-bg").slideUp(500);
						setTimeout(function() {
							$(".layer-bg").remove();
						},600);
						$(this).remove()
					});
				});
				
				
				function checkPass(pass){
					if(pass.length < 6){  
						return 0; 
					}
					var ls = 0;
					if(pass.match(/([a-z])+/)){  
						ls++; 
					} 
					if(pass.match(/([0-9])+/)){  
						ls++; 
					} 
					if(pass.match(/([A-Z])+/)){   
						ls++; 
					} 
					if(pass.match(/[^a-zA-Z0-9]+/)){ 
						ls++;
					} 
					return ls;
				}  
			});
		</script>
	</head>
	<body>
		<div class="content">
			[#include "/include/header.ftl"/]
			[#include "/include/left.ftl"/]
			<div class="right-main">
				<div id="right-container">
					<div class="welcom-container">

					</div>
				</div>

			</div>
		</div>
	</body>
</html>