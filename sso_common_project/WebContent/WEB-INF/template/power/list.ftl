<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="renderer" content="webkit|chrome">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>信达账务管理中心</title>
		<link href="${base}/static/css/style.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="${base}/static/css/metroStyle/metroStyle.css" />
	</head>
<body>
	<div class="content">
		[#include "/include/header.ftl"/]
		[#include "/include/left.ftl"/]
		<div class="right-main">
			<form action="list.jhtml" method="post" id="queryForm">
				<div id="right-container">
						<div class="curent-path">
							<span class="root-path">按钮管理 <em>&gt;</em></span>								
							<span class="active-path">列表	</span>
						</div>
						<div class="search-field ">
								<div class="search-bot clearfix">
									<div class="field-div">
										<label for="name">名称：</label>
										<input type="text" class="text1" name="name" id="name" value="${power.name}" />
									</div>
									<div class="field-div">
										<label for="name">编码：</label>
										<input type="text" class="text1" name="code" id="code" value="${power.code}" />
									</div>
									<div class="field-div">
										<div>
											<label for="name">所属菜单：</label>
											<div class="text-cont" onMouseLeave="javascript:$(this).hide();">
									           <ul id="auth-tree" class="ztree"></ul>
									       	</div>
									       	<input type="hidden" name="menu.id" id="menuId" value="${power.menu.id}" />
									       	<input type="text" id="menuName" name="menu.name" value="${power.menu.name}"  class="text1" onclick="javascript:focusMenu()" readonly placeholder="请选择菜单">
								       	</div>
									</div>
									<div class="field-div">
										<input type="submit" class="btn search-btn" value="查询" />
									</div>
									<div class="field-div">
										<input type="button" onclick="clearAll()" class="btn search-btn" value="重置" />
									</div>
								</div>
						</div>
						<div class="field-div">
							[@shiro.hasPermission name = "power:button:add"]
			                	<a href="${base}/power/powerAddView.jhtml" name="fancyboxClose" id="userAdd" class="btn search-btn com-creat-btn" >
									<span > + </span> 新增
								</a> 
			                [/@shiro.hasPermission]
						</div>
						<table class="table" border="0" cellspacing="0" cellpadding="0">
							<thead>
								<tr>
								 	<th>序号</th>
			                      	<th>名称</th>
			                      	<th>编码</th>
			                      	<th>所属菜单</th>
			                      	<th width="200">操作</th>
								</tr>
							</thead>
							<tbody>
								[#list page.content as power]
									<tr class="">
				                      <td>${power_index+1}</td>
				                      <td>${power.name}</td>
				                      <td>${power.code}</td>
				                      <td> [#if power.menu.parent.parent.name?exists]${power.menu.parent.parent.name}--[/#if]
					                       [#if power.menu.parent.name?exists]${power.menu.parent.name}--[/#if]
					                       [#if power.menu.name?exists]${power.menu.name}--[/#if]
				                      </td>
				                      <td>
				                      [@shiro.hasPermission name = "power:button:edit"]
					                      <a href="${base}/power/edit.jhtml?id=${power.id}" name="fancyboxClose"><span>编辑</span></a> 
					                  [/@shiro.hasPermission]
					                  [@shiro.hasPermission name = "power:button:delete"]
					                      <a onclick="delPower('${power.id}')"><span>删除</span></a>
				                      [/@shiro.hasPermission]
				                      </td>
				                    </tr>
								[/#list]
							</tbody>
						</table>
					</div>
					<div id="pager" style="float: right;"></div>
				</form>
			</div>
	</div>
</body>
<script src="${base}/static/js/jquery.ztree.core-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.excheck-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.exedit-3.5.min.js"></script> 
<script type="text/javascript">
	$(function(){    
	   init(${page.number+1});
	});
	function init(pagenumber){
		$("#pager").pager({pagenumber:pagenumber, pagecount:${page.totalPages},totalcount:${page.totalElements}, buttonClickCallback: ''});
	}
	//删除
	function delPower(id){
		layer.confirm("确定要删除吗？", {
		    btn: ['确定','取消']  
		}, function(){
           $.ajax({
	    		url:'${base}/power/enable.jhtml',
	    		type:'post',
	    		data:'id='+id,
	    		dataType: "json",
	    		success: function(data) {
					if (data.message.type == "success") {
						window.location.reload();
					} else {
						layer.alert(data.message.content);
					}
				}
	    	});
        });
	}
	
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
            var ids="";
			ids = getChildNodes(treeNode);
			
        	$("#menuId").val(ids);
        	$("#menuName").val(treeNode.name);
        	$(".text-cont").hide();
        }
        
        function focusMenu(){
          $(".text-cont").show();
        }
        
        function getChildNodes(treeNode) {
		     var childNodes = zTreeObj.transformToArray(treeNode);
		     var nodes = new Array();
		     for(i = 0; i < childNodes.length; i++) {
		          nodes[i] = "'"+childNodes[i].id+"'";
		     }
		     return nodes.join(",");
		}
		//清空内容
		function clearAll(){
           $("#code").val("");
           $("#name").val("");
           $("#menuId").val("");
           $("#menuName").val("");
        }
		jQuery(document).ready(function() {
		    zTreeObj = $.fn.zTree.init($("#auth-tree"), setting, ${menuList});
		    zTreeObj.expandAll(true);//展开所有节点
		});
</script> 
</html>
