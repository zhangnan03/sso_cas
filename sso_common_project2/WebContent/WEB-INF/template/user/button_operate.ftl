<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="renderer" content="webkit|chrome">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>信达账务管理中心</title>
		<link rel="stylesheet" type="text/css" href="${base}/static/css/main.css">
		<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.css">
		<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.ext.css">
		<link href="${base}/static/css/style.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="${base}/static/css/metroStyle/metroStyle.css" />
	</head>
<body>
	<form method="post" action="${base}/user/authorizePowerList.jhtml" id="searchForm">
		<input type="hidden" name="id" id="id" value="${user.id}" />
			<div class="search-field ">
					<div class="search-bot clearfix">
						<div class="field-div">
						   <label for="name">所属菜单：</label>
					       <div class="text-cont" onMouseLeave="javascript:$(this).fadeOut(200);">
					           <span class="ztree" onclick="searchAll()"><a href="javascript:void(0)">查看全部</a></span>
					           <ul id="auth-tree" class="ztree"></ul>
					       </div>
					       <input type="hidden" name="menuIds" id="menuIds" value="${menuIds}" />
					       <input type="text" id="menuName" name="menuName" value="${menuName}"  class="text" readonly onclick="focusMenu()" placeholder="请选择菜单">
						</div>
					</div>
			</div>
	</form>
	<form action="" method="post" id="inputForm">
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
					[#list pageInfo as power]
						<tr class="">
			             <td>
			           	 [#if power.isChecked==1]<input type="checkbox" id="${power.id}" name="checkPower" class="checkboxes" checked value="${power.id}" />
			             [#else]
			               <input type="checkbox" id="${power.id}" name="checkPower" class="checkboxes" value="${power.id}" />
			             [/#if]
			             </td>
			             <td>${power.name}</td>
			             <td>${power.code}</td>
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
<script src="${base}/static/js/jquery.ztree.core-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.excheck-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.exedit-3.5.min.js"></script> 
<script type="text/javascript" src="${base}/static/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="${base}/static/layer/layer.js"></script>
<script type="text/javascript">
	$(function() {  
    	$('#selectAll').click(function(){
			$('[name=checkPower]:checkbox').attr("checked", this.checked);
		});
		
	    //得到选中的值，ajax操作使用  
	    $("#btnSubmit").click(function() {
	        var url  = "";
	        var ids  = "";
	        var userId   = $("#id").val();
	        var menuIds   = $("#menuIds").val();
	        $(":checkbox:checked").each(function() {
	           ids += ","+$(this).val();
	        });
	        
	        $.ajax({
				url : "${base}/user/authorizePowerOperate.jhtml",
				type: "POST",
				data: "powerIds="+ids+"&userId="+userId+"&menuIds="+menuIds,
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
            var ids=getChildNodes(treeNode);
        	$("#menuIds").val(ids);
        	$("#menuName").val(treeNode.name);
        	$('#searchForm').submit();
        }
        
        function focusMenu(){
           $(".text-cont").fadeIn(200);
        }
        
        function getChildNodes(treeNode) {
		     var childNodes = zTreeObj.transformToArray(treeNode);
		     var nodes = new Array();
		     for(i = 0; i < childNodes.length; i++) {
		          nodes[i] = "'"+childNodes[i].id+"'";
		     }
		     return nodes.join(",");
		}
		
		function searchAll(){
		    $("#menuIds").val("");
        	$("#menuName").val("");
        	$('#searchForm').submit();
		}
		
		jQuery(document).ready(function() {
		    zTreeObj = $.fn.zTree.init($("#auth-tree"), setting, ${menuList});
		    zTreeObj.expandAll(true);//展开所有节点
		    var numbers = ${pageInfo?size};
			var checks = $("input[name='checkPower']:checked").length;
			if(numbers==checks){
			    $('#selectAll').attr("checked",true);
			}
		});
</script> 
</body>
</html>