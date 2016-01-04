		//编辑表单显示
		function editTreeNode(){
			var treeNode = zTree.getSelectedNodes()[0];
			if(treeNode==null||treeNode==undefined){
				layer.alert("请右键选中菜单");
			}else{
				showMenuSpan();
				$("#parentId").val(treeNode.parentId);
				$("#code").val(treeNode.code);
				$("#name").val(treeNode.name);
				$("#systemMark").val(treeNode.systemMark);
				$("#url").val(treeNode.url);
				$("#id").val(treeNode.id);
				$("#description").val(treeNode.description);
				$("#menuFlag").val(treeNode.menuFlag);
				$("#systemMark").attr("readOnly",true);
				$("#menuSort").val(treeNode.menuSort);
				console.log(treeNode);
			}
			return false;
		}
		
		//添加菜单
        function addTreeNode() {
        	var treeNode = zTree.getSelectedNodes()[0];
        	if(treeNode==null||treeNode==undefined){
        		layer.alert("请右键选中菜单");
			}else{
				showMenuSpan();
	        	clearData();
	        	$("#parentId").val(treeNode.id);
	        	$("#systemMark").val(treeNode.systemMark);
	        	$("#systemMark").attr("readOnly",true);
			}
        };
        //删除
        function removeTreeNode() {
        	var treeNode = zTree.getSelectedNodes()[0];
        	if(treeNode==null||treeNode==undefined){
				layer.alert("请右键选中菜单");
			}else{
				hideRMenu();
				if(treeNode.isParent){
					layer.alert("不能删除父节点");
				}else{
					layer.confirm("是否删除“"+treeNode.name+"”节点", {
					    btn: ['确定','取消']  
					}, function(){
						zTreeOnRemove(treeNode);
					});
				}
			}
		}
		
		//删除后回调
		function zTreeOnRemove(treeNode){
			$.ajax({
				url  : "enable.jhtml",
				type : "post",
				data : "id="+treeNode.id,
				dataType : "json",
				async : false,
				success : function(data){
					var msg = "";
					if(data.message.type == "success"){
						zTree.removeNode(treeNode);
						msg = "删除成功!";
					}else{
						layer.alert(data.message.content);
					}
				},		
				error : function(){
					layer.alert("系统错误！");
				}
			});
		}
        
        //显示修改、添加表单
		function showMenuSpan(){
			$("#menuSpan").css("display","block");
		}
		
		//添加系统
		function addSystem(){
			$("#menuSpan").css("display","block");
			$("#systemMark").attr("readOnly",false);
			clearData();
		}
		
		//清空数据
		function clearData(){
			$("#code").val("");
			$("#name").val("");
			$("#systemMark").val("");
			$("#url").val("");
			$("#id").val("");
			$("#description").val("");
			$("#menuFlag").val("");
			$("#menuSort").val("");
			$("#parentId").val("");
		}
		
		//修改表单提交
		function formSubmit(inputForm,url){
			// 表单验证
				inputForm.validate({
					rules: {
						code: {
							required: true,
							remote: {
								url: "checkCode.jhtml",
								cache: false,
								data:{
									id:function(){
										return $("#id").val();
									},
									code:function(){
										return $("#code").val();
									}
								}
							}
						},
						name: {
							required: true
						},
						systemMark: {
							required: true
						},
						menuSort:{
							digits:true
						}
					},
					messages: {
						code: {
						    required: "请输入菜单中文名字",  
							remote: "菜单编码不能重复"
						},
						name: {
						    required: "请输入菜单中文名字"
						},
						systemMark: {
							required: "系统标识不能为空"
						},
						menuSort:{
							digits:"只能为整数"
						}
					},
					submitHandler: function(form) {
						$.ajax({
							url: url,
							type: "POST",
							data: inputForm.serialize(),
							dataType: "json",
							cache: false,
							success: function(data) {
								if (data.message.type == "success") {
									window.location.reload();
								} else {
									layer.alert(data.message.content);
								}
							}
						});
					}
				});	
		}
		
		$(function(){
			var inputForm = $("#inputForm");
			$("#menuSubmit").click(function(){
				var id = $("#id").val();
				var url = "";
				var menuSort = $("#menuSort").val();
				if(menuSort==null||menuSort.length==0||menuSort=='undefined'){
					 $("#menuSort").val("0");
				}
				if(id!=null&&id.length>0&&id!='undefined'){//修改
					url="update.jhtml";
					formSubmit(inputForm,url);
				}else{//添加
					url="add.jhtml";
					formSubmit(inputForm,url);
				}
			});
		});