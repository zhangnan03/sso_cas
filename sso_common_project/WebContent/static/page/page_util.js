/**
 * 描述：主要提供给分页组件使用；提供诸如GET或POST两种数据提交方式；
 * 		 供分页组件，回调时使用；
 * 作者：Walter.luo
 * 时间：2012-6-14
 * 依赖：JQuery 1.8 （later）
*/

var PageClick = function (pageclickednumber){
	init(pageclickednumber);
	if($("#queryForm").attr('method').toUpperCase() == 'GET' ){
		PageGetClick(pageclickednumber);
	}else{
		PagePostClick(pageclickednumber);
	}
}

//回调函数支持GET方式传参、分页；
PageGetClick = function(pageclickednumber) {
	$("#result").html("Clicked Page " + pageclickednumber);
	if(location.toString().indexOf('?') > 0){
			if(location.toString().indexOf('page=') > 0){
				location = location.toString().replace(/(page=)\w*/g , "page=" + pageclickednumber)  ; 
			}else{
				location = location.toString()  +  "&page=" + pageclickednumber;
			}
	}else{ 
		location = location.toString()+ "?page=" +pageclickednumber;	
	}
}


/** 
 * 描述 ：回调函数支持POST方式传参、分页； 
 * 需要默认查询表单ID为“queryForm”
 * 
 */
PagePostClick = function(pageclickednumber) { 
	$("#result").html("Clicked Page " + pageclickednumber);
	$("<input type='hidden' name='page' value='"+pageclickednumber+"'>").appendTo($("#queryForm"));
	$("#queryForm").submit();
}