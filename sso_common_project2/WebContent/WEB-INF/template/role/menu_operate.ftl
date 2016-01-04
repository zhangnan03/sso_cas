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
<link href="${base}/static/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/static/css/metroStyle/metroStyle.css" />
</head>

<body class="page-header-fixed">
<form method="post" id="inputForm">
<input type="hidden" name="roleId" id="roleId" value="${roleId}" />
<div class="row-fluid">
        <div class="span10">
          <div class="portlet box grey">
            <div class="portlet-title">
              <div class="caption"><i class="icon-link"></i>菜单列表</div>
            </div>
            <div class="portlet-body fuelux">
              <ul id="auth-tree" class="ztree" style="height:300px; overflow-y:scroll;"></ul>
            </div>
          </div>
</div>
<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" id="btnSubmit" class="btn" value="提交" />
					<input type="button" class="btn" onclick="javacsript:parent.$.fancybox.close();" value="取消" />
				</td>
			</tr>
	</table>
</form>
<script src="${base}/static/js/jquery.js" type="text/javascript"></script>
<script src="${base}/static/js/jquery.ztree.core-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.excheck-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.exedit-3.5.min.js"></script> 
<script type="text/javascript" src="${base}/static/js/common.js"></script>
<script type="text/javascript" src="${base}/static/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript">
	var zTree, rMenu;
		var setting = {
			view: {
				dblClickExpand: false
			},check: {
                enable: true
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
			}
		};
		
	$(document).ready(function(){
		    zTree = $.fn.zTree.init($("#auth-tree"), setting, ${menuAll});
		    zTree.expandAll(true);//展开所有节点
		    var menuSelect = '${menuSelect}';
		    menuSelect = eval('(' + menuSelect + ')');
		    var nodes =  zTree.transformToArray(zTree.getNodes());
		    $(menuSelect).each(function(i){
		       for(var j=0;j<nodes.length;j++){
		           if(nodes[j].id==menuSelect[i].id){
		                  nodes[j].checked=true;
		                  zTree.updateNode(nodes[j],false);
		                  break;
		           }
		       }
		    });
		    
		    //得到选中的值，ajax操作使用  
	    $("#btnSubmit").click(function() {
			var menuIds = "";
			var roleId  = ","+$("#roleId").val();
			var treeNodes = zTree.getCheckedNodes(true);
			$.each(treeNodes, function(i){
				menuIds +=","+treeNodes[i].id;
			 }); 
			
			 $.ajax({
				url: "${base}/role/authorizeMenuPowerOperator.jhtml",
				type: "POST",
				data: "menuIds="+menuIds+"&roleId="+roleId,
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