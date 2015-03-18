/**
 * 评论
 */
comment = {
	/**
	 * 删除评论
	 * 
	 * @param comment_id
	 */
	oper_del:function(comment_id,article_id){
		if(window.confirm('你确定要删除该评论吗？')){
			jQuery.ajax({
				type:'POST',
				url:'web_comment/del',
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
		
};
