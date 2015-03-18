$(function() {
	// 回顶部
	$(window).scroll( function() { 
		if($(this).scrollTop() >= 100 ){
			$("#scrollTop").show();
		} else {
			$("#scrollTop").hide();
		}
	} );
	
	if($(window).scrollTop() >= 100 ){
		$("#scrollTop").show();
	} else {
		$("#scrollTop").hide();
	}
	
	$("#scrollTop").click(function(){$(window).scrollTop(0);});
	
	// 评论数获取
	if ($("#web_message").length > 0){  
		jQuery.ajax({
			type:'POST',
			url:'web_comment/count',
			success:function(data){
				if(data.status==1){
					if(data.count > 0 ){
						$('#web_message').text('我的消息（'+data.count+'）');
						$('#web_message').css('font-weight','bold');
						$('#web_message').css('color','green');
					}
				} else {
					console_log('获取评论失败：'+data.msg);
				}
			},
			error:function(html){
				var flag = (typeof console != 'undefined');
				if(flag) console.log("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
				alert("服务器忙，提交数据失败，请联系管理员！");
			}
		});
	}
});
