<html>
<head>
<meta charset="utf-8" />
<title>信达债匹管理</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link rel="stylesheet" type="text/css" href="${base}/static/css/main.css">
<link href="${base}/static/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/static/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/static/css/metroStyle/metroStyle.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.css">
<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.ext.css">
</head>
<body style="height: 300px;">
  	<form action="${base}/power/save.jhtml" method="post" id="inputForm">
  	<table class="com-table" border="0" cellspacing="0" cellpadding="0">
  		<input type="hidden" name="id" id="id" value="${power.id}" />
		<input type="hidden" name="menu.id" id="menuId" value="${power.menu.id}" />
			<tbody>
				<tr>
					<td class="p-col1">名称：</td>
					<td class="p-col2">
						<input type="text" name="name" class="text" maxlength="200"maxlength="200"  value="${power.name}"/>
					</td>
				</tr>
				<div id="passInfo">
				<tr>
					<td class="p-col1">编码：</td>
					<td class="p-col2">
						<input type="text" name="code" class="text"  value="${power.code}"/>
					</td>
				</tr>
				<tr>
				   <td class="p-col1">菜单</td>
				   <td class="p-col2"> 
		              <div>
		              	<div class="text-cont" onMouseLeave="javascript:$(this).hide();">
		              		<ul id="auth-tree" class="ztree"></ul>
		              	</div>
		                <input type="text" id="menuName" name="menuName" class="text" readonly onclick="focusMenu()" value="${power.menu.name}" placeholder="请选择菜单">
		              </div>
				   </td>
				</tr>
				<tr>
					<td class="p-col1">描述：</td>
					<td class="p-col2">
						<textarea name="description" rows="3">${power.description}</textarea>
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
<script src="${base}/static/js/jquery.ztree.core-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.excheck-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.exedit-3.5.min.js"></script> 
<script>
	$(function(){     
	   	var $inputForm = $("#inputForm");
	   	// 表单验证
		$inputForm.validate({
			rules: {
				code: {
					required: true,
					remote: {
						url: "checkCode.jhtml",
						cache: false
					}
				},
				name: {
					required: true
				},
				menuName:{
				    required: true
				}
			},
			messages: {
				code: {
				    required: "按钮编码不能为空",
					remote: "按钮编码已经存在"
				},
				name: {
					required: "按钮名称不能为空"
				},
				menuName:{
				    required: "请选择菜单"
				}
			},
			submitHandler: function(form) {
				$.ajax({
					url: "save.jhtml",
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
	
	var zTreeObj;
		var setting = {
			view: {
				dblClickExpand: false
			},data : {
				key : {
				    name : 'name',
				    url:''
				},
				simpleData : {
					enable : true,
					idKey : 'id',
					pIdKey : 'parentId',
					rootPId : '0'
				}
			},callback: {
				onClick: onClick
			}
        };
        
        //触发菜单操作
        function onClick(event, treeId, treeNode){
        	$("#menuId").val(treeNode.id);
        	$("#menuName").val(treeNode.name);
        	$(".text-cont").hide();
        }
        
        function focusMenu(){
           $(".text-cont").show();
        }
		jQuery(document).ready(function() {
		    zTreeObj = $.fn.zTree.init($("#auth-tree"), setting, ${page});
		    zTreeObj.expandAll(true);//展开所有节点
		});
</script> 
</html>