<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<head>
<meta charset="utf-8" />
<title>权限管理系统</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link href="${base}/static/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/style-metro.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/light.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${base}/static/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/static/css/metroStyle/metroStyle.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/page/Pager.css"/>	
<link href="${base}/static/fancybox/jquery.fancybox.css" rel="stylesheet">
</head>
<body>
<form  method="post" id="inputForm" style="min-height:400px;max-height:450px;">
	<input type="hidden" name="id" id="id" value="${systemDict.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>字典类别：
				</th>
				<td>
					<input type="text" class="text2" name="dictType" id="dictType" value="${systemDict.dictType}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>类别名称：
				</th>
				<td>
					<input type="text" class="text2" name="dictTypeName" id="dictTypeName" value="${systemDict.dictTypeName}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>字典类型：
				</th>
				<td>
					<input type="text" class="text2" name="dictCode" id="dictCode" value="${systemDict.dictCode}" />
				</td>
			</tr>
			
			<tr>
				<th>
					<span class="requiredField">*</span>类型名称：
				</th>
				<td>
					<input type="text" class="text2" name="dictName" id="dictName" value="${systemDict.dictName}" />
				</td>
			</tr>
			
			<tr>
				<th>
					<span class="requiredField">*</span>排序：
				</th>
				<td>
					<input type="text" class="text2" name="orderId" id="orderId" value="${systemDict.orderId}" />
				</td>
			</tr>
			
			<tr>
			<th>描述</th>
			<td>
				<textarea id="remark" name="remark" class="text">${systemDict.remark}</textarea>
			</td>
			</tr>
			
			<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="submit" class="button" value="提交" />
				<input type="button" class="button" value="取消" onclick="javascript:parent.$.fancybox.close();" />
			</td>
		</tr>
		</table>
</form>
<script src="${base}/static/js/jquery.min.js" type="text/javascript"></script> 
<script src="${base}/static/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script> 
<script src="${base}/static/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="${base}/static/js/jquery.uniform.min.js" type="text/javascript" ></script> 
<script src="${base}/static/js/jquery.ztree.core-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.excheck-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.exedit-3.5.min.js"></script> 
<script src="${base}/static/js/app.js" type="text/javascript"></script> 
<script src="${base}/static/js/jquery.validate.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="${base}/static/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/static/js/common.js"></script>
<script type="text/javascript" src="${base}/static/js/menu/menu.js"></script>
<script>
	$(function(){     
	   	var $inputForm = $("#inputForm");
	   	// 表单验证
		$inputForm.validate({
			rules: {
				dictType: {
					required: true,
					rangelength:[1,30]
				},
				dictTypeName: {
					required: true,
					rangelength:[1,10]
				},
				dictCode:{
				    required:true
				},
				dictName:{
				    required:true
				},
				orderId:{
				    digits:true
				}
			},
			messages: {
				dictType: {  
				    required: "字典类别不能为空",  
				    rangelength : "用户名必须在1-30个字符之间"
				},
				dictTypeName: {  
				    required: "请输入用户名",
				    rangelength : "用户名必须在1-10个字符之间"
				},
				dictCode: {  
				    required: "不能为空"
				},
				dictName:{
				    required:"不能为空"
				},
				orderId:{
				    digits:"只能为整数"
				}
			},
			submitHandler: function(form) {
				$.ajax({
					url: "${base}/dict/edit.jhtml",
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
</body>
</html>