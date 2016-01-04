<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="renderer" content="webkit|chrome">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>信达账务管理中心</title>
		<link rel="stylesheet" type="text/css" href="${base}/static/css/main.css">
		<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.css">
		<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.ext.css">
	</head>
<body>
	<form action="" method="post" id="queryForm">
		<input type="hidden" name="id" id="id" value="${user.id}" />
			<div>
				<table class="table" border="0" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<th class="check"><input type="checkbox" id="selectAll" value="" name="checkPower" class="group-checkable" data-set=".check-table .checkboxes" /></th>
				           	<th>名称</th>
				           	<th>编码</th>
						</tr>
					</thead>
					<tbody>
						[#list page as role]
							<tr class="">
					             <td>
					           	 [#if role.isChecked==1]<input type="checkbox" id="${role.id}" name="checkPower" class="checkboxes" checked value="${role.id}" />
					             [#else]
					               <input type="checkbox" id="${role.id}" name="checkPower" class="checkboxes" value="${role.id}" />
					             [/#if]
					             </td>
					             <td>${role.name}</td>
					             <td>${role.code}</td>
				             </tr>
						[/#list]
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="com-container pro-btns">
					  				<input type="button" id="btnSubmit" class="btn" value="提交" />
					  				<input type="button" class="btn" onclick="javacsript:parent.$.fancybox.close();" value="取消" />
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</form>
</body>
<script src="${base}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>   
<script type="text/javascript" src="${base}/static/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="${base}/static/layer/layer.js"></script>
<script type="text/javascript">
	$(function() {  
    	$('#selectAll').click(function(){
			$('[name=checkPower]:checkbox').attr("checked", this.checked);
		}) 
	    //得到选中的值，ajax操作使用  
	    $("#btnSubmit").click(function() {
	        var url  = "";
	        var ids  = "";
	        var flag = "${flag}";
	        var id   = $("#id").val();
	        $(":checkbox:checked").each(function() {
	           ids += ","+$(this).val();
	        });
	        
	        $.ajax({
				url : "${base}/user/authorizeRoleOperate.jhtml",
				type: "POST",
				data: "ids="+ids+"&id="+id,
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
	        
	    });  
	});
</script> 
</body>
</html>