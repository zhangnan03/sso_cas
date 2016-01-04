<html>
<head>
<meta charset="utf-8" />
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
		<div id="right-container">
			<div class="curent-path">
				<span class="root-path">菜单管理 <em>&gt;</em></span>								
				<span class="active-path">列表	</span>
			</div>
			
	      	<div class="field-div">
	      		<!--
					[@shiro.hasPermission name = "power:menu:add:system"]
						<button type="button" id="addBtn" onclick="addSystem()"  class="btn search-btn com-creat-btn" style="margin-bottom:20px"> 添加系统 </button>
	                [/@shiro.hasPermission]
                -->
			</div>
			
			<div class="clearfix"></div>	
		    <div style="width:600px;float:left;">
        		<div class="span4">
		          	<div class="portlet box grey">
			            <div class="portlet-title">
			              <div class="caption"><span>菜单列表</span></div>
			            </div>
			            <div class="portlet-body fuelux">
			              <ul id="auth-tree" class="ztree"></ul>
			            </div>
		          	</div>
	          	</div>
    		</div>
		    <div style="width:600px;float:left;padding-left:10px;">
		           <form action="" method="post" id="inputForm">
						<input type="hidden" name="id" id="id" />
				        <input type="hidden" name="parent.id" id="parentId" />
				        <span id="menuSpan" style="display:none;">
				        	<div class="span6 ">
						    	<div class="row-fluid">
							  		<table class="com-table" border="0" cellspacing="0" cellpadding="0">
								  		<input type="hidden" id="menuId" name="menu.id" /> 
								  		<input type="hidden" id="systemMark" name="systemMark" /> 
										<tbody>
											<tr>
												<td class="p-col1">名称：</td>
												<td class="p-col2_more">
													<input type="text" name="name" id="name" class="text2_more" maxlength="200"maxlength="200" placeholder="不能为空且不能超过10个字符"/>
												</td>
											</tr>
											<tr>
												<td class="p-col1">编码：</td>
												<td class="p-col2_more">
													<input type="text" name="code" id="code" class="text2_more" placeholder="不能为空且只能输入英文"/>
												</td>
											</tr>
											<tr>
												<td class="p-col1">路径：</td>
												<td class="p-col2_more">
													<input type="text" name="url" id="url" class="text2_more"/>
												</td>
											</tr>
											<tr>
												<td class="p-col1">菜单标识：</td>
												<td class="p-col2_more">
													<input type="text" name="menuFlag" id="menuFlag" class="text2_more"/>
												</td>
											</tr>
											<tr>
												<td class="p-col1">排序：</td>
												<td class="p-col2_more">
													<input type="text" name="menuSort" id="menuSort" class="text2_more"/>
												</td>
											</tr>
											<tr>
												<td class="p-col1">描述：</td>
												<td class="p-col2_more">
													<textarea name="description" rows="3" cols="61"></textarea>
												</td>
											</tr>
										</tbody>
											<tfoot>
											<tr>
												<td colspan="2">
													<div class="com-container pro-btns">
										  				<input id="menuSubmit"  type="submit"  class="btn" value="确定"/>
										  				<input type="button" class="btn" onclick="javacsript:parent.$.fancybox.close();" value="取消" />
													</div>
												</td>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</span>	
				</form>	
			</div>
		</div>
</div>
<div id="rMenu">
	<ul>
	[@shiro.hasPermission name = "power:menu:add"]
		<li id="m_add" onclick="addTreeNode()">增加</li>
	[/@shiro.hasPermission]
	[@shiro.hasPermission name = "power:menu:edit"]
		<li id="m_edit" onclick="editTreeNode()">修改</li>
	[/@shiro.hasPermission]
	[@shiro.hasPermission name = "power:menu:delete"]
		<li id="m_del" onclick="removeTreeNode()">删除</li>
	[/@shiro.hasPermission]
	</ul>
</div>
	
</body>
<script src="${base}/static/js/jquery.ztree.core-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.excheck-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.exedit-3.5.min.js"></script> 
<script type="text/javascript" src="${base}/static/js/menu.js"></script>
<script>
		var zTree, rMenu;
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
				onRightClick: OnRightClick
			}
		};

		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}

		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
			} else {
				$("#m_del").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		
		function clearAll(){
		   $("#code").val("");
		   $("#name").val("");
		   $("#url").val("");
		   $("#menuFlag").val("");
		   $("#url").val("");
		   $("#description").val("");
		}
		
		$(document).ready(function(){
		    zTree = $.fn.zTree.init($("#auth-tree"), setting, ${page});
		   	zTree.expandAll(true);//展开所有节点
			rMenu = $("#rMenu");
		});
    </script>
</html>
