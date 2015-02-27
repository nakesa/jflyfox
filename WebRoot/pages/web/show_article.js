$(function() {
	$('#comment-btn').click(function(){
		var comment = $('[name="comment"]').val();
		var article_id = $('[name="article_id"]').val();
		if(comment==''){
			alert('发布内容不能为空！');
			return;
		} else if (comment.length > 400) {
			alert('发布内容长度不能大于400！');
			return;
		}
		
		jQuery.ajax({
			type:'POST',
			url:'web/comment_save',
			data:"model.content=" + comment + "&model.article_id=" + article_id,
			success:function(data){
				if(data.status==1){
					var username = data.create_name;
					var createTime = data.create_time;
					var comment_id = data.comment_id;
					
					var htmlText = '<div class="comment-item" id='+ comment_id + '_' + article_id + '>';
					htmlText += '<div class="item-top">';
					htmlText += '<a href="#"><img alt="" src="static/images/user/user.png" /></a>';
					htmlText += '<a href="#" class="user-name">'+username+'</a>';
					htmlText += '<div style="float:none;line-height: 24px;overflow: visible;">';
					htmlText += '<div style="float:none;line-height: 24px;overflow: visible;">';
					htmlText += '<span>'+comment+'</span>';
					htmlText += '</div></div>';
					htmlText += '<div class="item-bottom">';
					htmlText += '<span>'+createTime+'</span>';
					htmlText += '<a href="javascript:oper_del_comment(' + comment_id + ',' + article_id +');" style="float: right;">删除</a>';
					htmlText += '</div></div>';		
					$('.comment-list').prepend(htmlText);
					
					var count = parseInt($('[name="count_comment"]').val());
					$('[name="count_comment"]').val(count + 1);
					$('#count_comment_show').html("评论(" + (count + 1) + ")");
					
				} else {
					alert('保存失败：'+data.msg);
				}
				$('[name="comment"]').val('');
			},
			error:function(html){
				var flag = (typeof console != 'undefined');
				if(flag) console.log("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
				alert("服务器忙，提交数据失败，请联系管理员！");
			}
		});
		
	});
	
	
});

function oper_del_comment(comment_id,article_id) {
	if(window.confirm('你确定要删除该评论吗？')){
		jQuery.ajax({
			type:'POST',
			url:'web/comment_del',
			data:"model.id=" + comment_id + "&model.article_id=" + article_id,
			success:function(data){
				if(data.status==1){
					$('#'+comment_id+'_'+article_id).remove();
					
					var count = parseInt($('[name="count_comment"]').val());
					$('[name="count_comment"]').val(count - 1);
					$('#count_comment_show').html("评论(" + (count - 1) + ")");
				} else {
					alert('删除失败：'+data.msg);
				}
				$('[name="comment"]').val('');
			},
			error:function(html){
				var flag = (typeof console != 'undefined');
				if(flag) console.log("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
				alert("服务器忙，提交数据失败，请联系管理员！");
			}
		});
	}
}
