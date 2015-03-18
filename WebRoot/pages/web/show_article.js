var comm_reply_userid = 0;
var comm_reply_username = '';

$(function() {
	// 回复
	$('#comment-btn').click(function(){
		var comment = $('[name="comment"]').val();
		var article_id = $('[name="article_id"]').val();
		// 去除回复提示
		if(comm_reply_username!='' && comment.indexOf(comm_reply_username) == 0){
			comment = comment.substr(comm_reply_username.length);
		}
		
		if(comment==''){
			alert('发布内容不能为空！');
			return;
		} else if (comment.length > 400) {
			alert('发布内容长度不能大于400！');
			return;
		}
		
		jQuery.ajax({
			type:'POST',
			url:'web_comment/save',
			data:"model.content=" + comment + "&model.article_id=" + article_id + "&model.reply_userid=" + comm_reply_userid,
			success:function(data){
				if(data.status==1){
					var username = data.create_name;
					var createTime = data.create_time;
					var comment_id = data.comment_id;
					var title_url = data.title_url||'';
					var reply_username = data.reply_username || ''; 
					title_url = (title_url=='')?'static/images/user/user.png':title_url;
					
					var htmlText = '<div class="comment-item" id='+ comment_id + '_' + article_id + '>';
					htmlText += '<div class="item-top">';
					htmlText += '<a href="#"><img alt="" width="32" height="32" alt="头像" class="img_radius" src="'+title_url+'" /></a>';
					htmlText += '<a href="#" class="user-name">'+username+'</a>';
					// 回复
					if(reply_username != '' ) { 
						htmlText += '<span style="float: left;margin-right: 10px;">回复</span>';
						htmlText += '<a href="#" class="user-name">' + reply_username + '</a>';
						htmlText += '<br>';
					}
					
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
	
	// 回复内容为空时清除to_user
	$('[name="comment"]').change(function(){
		if($(this).val()=='') {
			comm_reply_userid = 0;
			comm_reply_username = '';
		}
	});
	
	
});

/**
 * 删除评论
 * 
 * @param comment_id
 */
function oper_del_comment(comment_id) {
	var article_id = $('[name="article_id"]').val();
	comment.oper_del(comment_id, article_id);
}


/**
 * 回复评论
 * @param comment_id
 * @param yo_userid
 */
function oper_reply_comment(userid,username){
	comm_reply_userid = userid;
	comm_reply_username = '回复 '+ username+ '：';
	$('[name="reply_userid"]').val(comm_reply_userid); // 设置回复人ID
	$('[name="comment"]').val(comm_reply_username);
	$('[name="comment"]').focus();
}
