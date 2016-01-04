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
  		<input type="hidden" name="id" id="id" value="${role.id}" />
			<tbody>
				<tr>
					<td class="p-col1">编码：</td>
					<td class="p-col2">
						<input type="text" name="code" class="text" maxlength="200"maxlength="200" value="${role.code}"/>
					</td>
				</tr>
				<tr>
					<td class="p-col1">名称：</td>
					<td class="p-col2">
						<input type="text" name="name" class="text" value="${role.name}"/>
					</td>
				</tr>
				<tr>
					<td class="p-col1">描述: </td>
					<td class="p-col2">
						<textarea name="description" rows="3">${role.description}</textarea>
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
				code: {
					required: true,
					remote: {
						url: "${base}/role/checkCode.jhtml",
						type: "post",
						data:{code:function(){
								return $("#code").val();
							   },
							   id:function(){
							     return $("#id").val();
							   }
						}
					}
				},
				name: {
					required: true
				}
			},
			messages: {
				code: {
					required: "角色编码不能为空",
					remote: "角色编码已经存在"
				},
				name: {
					required: "角色名称不能为空"
				}
			},
			submitHandler: function(form) {
				$.ajax({
					url: "${base}/role/update.jhtml",
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