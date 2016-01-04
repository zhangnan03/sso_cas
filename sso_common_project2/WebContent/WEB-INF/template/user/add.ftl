<html>
<head>
<meta charset="utf-8" />
<title>信达债匹管理</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link rel="stylesheet" type="text/css" href="${base}/static/css/main.css">
<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.css">
<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.ext.css">
</head>
<body style="height: 300px;">
  	<form action="" id="inputForm">
  	<table class="com-table" border="0" cellspacing="0" cellpadding="0">
  		<input name="id" type="hidden" value="${id}"/>
			<tbody>
				<tr>
					<td class="p-col1">用户名：</td>
					<td class="p-col2">
						<input type="text" name="username" class="text" maxlength="200"maxlength="200"/>
					</td>
				</tr>
				<div id="passInfo">
				<tr>
					<td class="p-col1">真实姓名：</td>
					<td class="p-col2">
						<input type="text" name="realName" class="text" />
					</td>
				</tr>
				<tr>
					<td class="p-col1">邮箱：</td>
					<td class="p-col2">
						<input type="text" name="email" class="text" />
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="com-container pro-btns">
			  				<input id="btnSubmit"  type="submit"  class="btn" value="确定"/>
			  				<input type="button" class="btn" onclick="javacsript:parent.$.fancybox.close();" value="取消" />
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
  	</form>
  </div>
</body>
<script src="${base}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>   
<script type="text/javascript" src="${base}/static/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/static/layer/layer.js"></script>
<script>
	$(function(){     
	   	var $inputForm = $("#inputForm");
	   	// 表单验证
		$inputForm.validate({
			rules: {
				username: {
					required: true,
					rangelength:[3,20],
					remote: {
						url: "${base}/user/checkUsername.jhtml",
						type: "post",
						cache: false
					}
				},
				realName: {
					required: true,
					rangelength:[1,10]
				},
				email:{
				    email:true
				}
			},
			messages: {
				username: {  
				    required: "请输入用户名",  
				    rangelength : "用户名必须在3-20个字符之间",
				    remote: "用户名已经存在，请重新输入用户名"  
				},
				realName: {  
				    required: "请输入真实姓名",
				    rangelength : "用户名必须在1-10个字符之间",
				},
				email: {  
				    email: "邮箱格式不正确"
				}
			},
			submitHandler: function(form) {
				$.ajax({
					url: "${base}/user/add.jhtml",
					type: "POST",
					data: $inputForm.serialize(),
					dataType: "json",
					cache: false,
					success: function(data) {
						if (data.message.type == "success") {
							parent.$.fancybox.close(); 
						} else {
							layer.alert(data.message.content);
						}
					}
				});
			}
		});
	});
</script> 
</html>
