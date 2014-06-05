/** 
 * 新闻滚动    
 * 
 * @param id 容器选择器
 **/
function showNews(id){
    var $this = $(id);
    var scrollTimer;
    $this.hover(function() {
        clearInterval(scrollTimer);
    }, function() {
        scrollTimer = setInterval(function() {
            scrollNews($this);
        }, 2000);
    }).trigger("mouseleave");
}

function scrollNews(obj) {
    var $self = obj.find("table:first");
    var lineHeight = $self.find("tr:first").height(); //获取行高
    $self.animate({ "marginTop": -lineHeight + "px" }, 600, function() {
        $self.css({ marginTop: 0 }).find("tr:first").appendTo($self); //appendTo能直接移动元素
    });
}