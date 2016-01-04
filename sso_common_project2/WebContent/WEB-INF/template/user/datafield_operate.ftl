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

<body class="page-header-fixed">
<form method="post" action="${base}/user/authorizeDataFieldList.jhtml" id="searchForm">
   <input type="hidden" name="id" id="id" value="${user.id}" />
      <div class="row-fluid">
      <div id="dashboard">
        <div class="row-fluid form-horizontal"  style="margin-bottom: 20px;">

			<div class="span6">
	          <div class="control-group">
		           <label class="control-label">菜单</label>
				   <div class="controls text-cont-box">
				       <div class="text-cont" onMouseLeave="javascript:$(this).fadeOut(200);">
				           <span class="ztree" onclick="searchAll()"><a href="javascript:void(0)">查看全部</a></span>
				           <ul id="auth-tree" class="ztree"></ul>
				       </div>
				       <input type="hidden" name="menuIds" id="menuIds" value="${menuIds}" />
				       <input type="text" id="menuName" name="menuName" value="${menuName}"  class="m-wrap span12 text-tree-inp" readonly onclick="focusMenu()" placeholder="请选择菜单">
				   </div>
			 </div>
		  </div>          

        </div>
 </form>
 <form method="post" id="inputForm" style="max-height:450px;">     
  <table class="table table-striped table-hover table-bordered check-table" id="sample_editable_1">
     <thead>
        <tr>
           <th class="check"><input type="checkbox" id="selectAll" value="" name="checkPower" class="group-checkable" data-set=".check-table .checkboxes" /></th>
           <th>名称</th>
           <th>编码</th>
        </tr>
     </thead>
     <tbody>
        <#list pageInfo as dataField>
           <tr class="">
             <td>
           	 <#if dataField.isChecked==1><input type="checkbox" id="${dataField.id}" name="checkPower" class="checkboxes" checked value="${dataField.id}" />
             <#else>
               <input type="checkbox" id="${dataField.id}" name="checkPower" class="checkboxes" value="${dataField.id}" />
             </#if>
             </td>
             <td>${dataField.name}</td>
             <td>${dataField.code}</td>
             </tr>
             </#list>
     </tbody>
   </table>
   <table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" id="btnSubmit" class="button" value="提交" />
					<input type="button" class="button" id="cancelBtn" onclick="closePage()" value="取消" />
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
<script src="${base}/static/js/table-editable.js" type="text/javascript"></script>
<script type="text/javascript">
	
	$(function() {  
    	$('#selectAll').click(function(){
			$('[name=checkPower]:checkbox').attr("checked", this.checked);
		}) 
	    //得到选中的值，ajax操作使用  
	    $("#btnSubmit").click(function() {
	        var url  = "";
	        var dataFieldIds  = "";
	        var userId   = $("#id").val();
	        var menuIds   = $("#menuIds").val();
	        $(":checkbox:checked").each(function() {
	           dataFieldIds += ","+$(this).val();
	        });
	        
	        $.ajax({
				url : "${base}/user/authorizeDataFieldOperate.jhtml",
				type: "POST",
				data: "dataFieldIds="+dataFieldIds+"&userId="+userId+"&menuIds="+menuIds,
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
		    var numbers = ${pageInfo?size};
			var checks = $("input[name='checkPower']:checked").length;
			if(numbers==checks){
			    $('#selectAll').attr("checked",true);
			}
		});
</script> 
</body>
</html>