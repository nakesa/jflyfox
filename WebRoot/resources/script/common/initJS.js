$(document).ready(function(){
		//编辑页面
		$("table[class='tableEdit'] tr").each(function(){
			$(this).find("td:odd:not(.unTableClass)").addClass("td-edit-odd");
			$(this).find("td:even:not(.unTableClass)").addClass("td-edit-even");
		});
		//列表页面，但双行背景色
		$("table[class='tableList'] ").each(function(){
			$(this).find("tr:odd").addClass("tr-odd");
			$(this).find("tr:even").addClass("tr-even");
		});
		//列表页面，鼠标背景色
		$("table[class='tableList'] tr").hover(
	  			function() {$(this).addClass("tr-hover");},
	  			function() {$(this).removeClass("tr-hover");}
		);
		//列表页面，鼠标点选
		$("table[class='tableList'] tr").bind("click",function(e){
			var $target = $(e.target);
			var $that = $(this).find("td input[name='mid'][type='checkbox']:visible");//选择符限制
			if($that.length!=1) return;//如果不存在或者多余一个的话,直接退出
			if($target.parents().is("a,button")||$target.is("a,button")) return;//如果是上面或者本身的相关a连接,不操作,如果是相关button
			//如果时间源对象就是checbox,mid本身,就单独处理
			if($target.val()==$that.val()&&$target.attr("name")==$that.attr("name")&&$target.attr("name")=="mid"){
				if($that.attr("checked")=="checked"){
					$(this).addClass("tr-selected");
				}else{
					$(this).removeClass("tr-selected");
				}
			 	return;
			}
			//如果是选中的话,就处理成为取消选中
			if($that.attr("checked")=="checked"){
				$(this).find("td input[name='mid']").removeAttr("checked");
				$(this).removeClass("tr-selected");
			}else{
				$(this).find("td input[name='mid']").attr("checked","checked");
				$(this).addClass("tr-selected");
			}
		});
		//查询条件
		$("table[class='tableSearch']  tr").each(function(){
			$(this).find("td:odd").addClass("td-search-odd");
			$(this).find("td:even").addClass("td-search-even");
		});
		//查询条件
		$("fieldset[class='tableSearch']  tr").each(function(){
			$(this).find("td:odd").addClass("td-search-odd");
			$(this).find("td:even").addClass("td-search-even");
		});
		//这里写这个，用于默认选中的方法input中的内容,动态绑定在鼠标点击释放时；
		$(":text").each(function(i){  
			$(this).live("focus",function(){
				$(this).select();
			});
		});
		//这里写这个，用于默认选中的方法textArea中的内容，动态绑定在鼠标点击释放时；
		$("textarea").each(function(i){  
			$(this).live("focus",function(){
				$(this).select();
			});
		});
		//在需要验证的地方加上必填图标
		$("[valid^='vtext'],[valid^='vdate'],[valid^='vselect'],[valid*='_y']").each(function(){
			$(this).after("<img class='validvip' src='../../images/importinf.jpg' />");
		});
		
			/* //按钮样式加载优化
		$("input[class^='button']").each(function(i){
			var name = $(this).attr('class'); 
			$(this).mouseover(function(){
				$(this).attr('class',name+'_hover');
			});
			$(this).mouseout(function(){
				$(this).attr('class',name);
			}); 
		});	
		*/
		$(":button").each(function(i){  
			var name = $(this).attr('name'); 
			$(this).attr('class','button_'+name); 
			$(this).mouseover(function(){
				$(this).attr('class','button_'+name+'_hover');
			});
			$(this).mouseout(function(){
				$(this).attr('class','button_'+name);
			}); 
		});
		//用于绑定查询回车搜索，现有的不用更改，可直接使用，所有的操作限定在tableSearch
		$(".tableSearch input[type='text']").keypress(function(e){
			e = e || window.event;
			var keynum = e.which ? e.which : e.keyCode;
			//以上两句兼容浏览器事件，获取按键号，当为回车键时执行查询
			if ( keynum == 13){
		     	$(".tableSearch input:button[name='search']").click();
			}
		});
		//这个是用于页面初始化加载时间的,统一处理可以单独定义一个class类型
		/*var inputdates = document.getElementsByName("v7");
		for(var i=0;i<inputdates.length;i++){
			if(inputdates[i].value){}
			else{inputdates[i].value=getDateWithIN(new Date());}
		}*/
		//避免ie下面input.text垂直方向对不齐的问题
		if($.browser.msie) $("input[type='text']").css("padding-top","2px").css("padding-bottom","2px").css("border","1px solid #ccc");
});