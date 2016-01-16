# 个人博客
------------------------
#### 博客地址：[http://www.jflyfox.com/blog](http://www.jflyfox.com/blog) ####

#### 本网站后台基于Jfinal开发，模板基于beetl，数据库为Mysql。 ####
#### 老版本地址如下  ####
#### 演示地址：[http://jflyfox.oschina.mopaas.com/](http://jflyfox.oschina.mopaas.com/) ####
#### 管理地址：[http://jflyfox.oschina.mopaas.com/admin](http://jflyfox.oschina.mopaas.com/admin)  ####
#### 测试账号: admin/admin123 或 test/123456  ####

# 首页、登陆、后台展示 #
![image](http://static.oschina.net/uploads/space/2015/0202/132448_RrZa_166354.jpg)
![image](http://static.oschina.net/uploads/space/2015/0202/132545_qlWx_166354.jpg)
![image](http://static.oschina.net/uploads/space/2015/0202/132559_bxcq_166354.jpg)

# 一、平台部署说明 #
------------------------
> 1. 数据库配置文件：/jflyfox/src/conf/db.properties。
> 2. 安装mysql数据库，创建用户和数据库。
> 3. 运行mysql.sql和mysql_data.sql文件。

# 鸣谢
 1. [JFinal](http://www.oschina.net/p/jfinal)
 2. [beetl](http://ibeetl.com/community/)
 3. [oschina](http://www.oschina.net/)

# 开源赞助
![OSC@GIT](http://static.oschina.net/uploads/space/2015/0213/104940_ZKNb_166354.png "开源赞助我(支付宝)")

# 更新说明
>## 2016-01-16
> 1. jfinal升级为2.1，升级为2.8.0
> 2. 代码全面重构，升级2.1遇到很多坑。

>## jflyfox 2.7.0 2016-01-14
> 1. jfinal升级为2.0，升级为2.7.0

>## jflyfox 2.6 2015-06-04
> 1. 升级为2.6

>## 2015-05-26
> 1. 完全去除模板，程序更易读

>## 2015-05-26
> 1. 加入一键分享功能

>## 2015-05-26
> 1. 修改列表评论数和访问量不更新bug，加入缓存处理

>## jflyfox 2.5 2015-05-21
> 1. 升级为2.5


>## 2015-05-19
> 1. 修改新用户没有部门bug。
> 2. 修改登陆页面不同分辨率展示有白边问题。
> 3. 加入回复防止XSS攻击，修改回复管理排序，最新的靠前。
> 4. 加入文章tags。

>## jflyfox2.4 2015-05-18
> 1. 升级为2.4
> 2. 修改因分辨率登陆页面边框空白问题

>## 2015-05-14
> 1. 加入文章title优化，seo。

>## 2015-05-10
> 1. 解决UE上传图片bug。
> 2. 解决UE上传确认按钮样式问题。

>## 2015-05-07
> 1. 加入title统一管理

>## 2015-05-06
> 1. 加入了回复管理模块，管理员可以肆无忌惮的删除回复了~！~

>## 2015-04-29
> 1. 后台完善了系统管理：组织机构管理、用户管理、角色管理、菜单管理、数据字典

>## jflyfox2.3 2015-03-25
> 1. 全面修改创建时间，加入时分秒。

>## 2015-03-24
> 1. 加入了前台分页，这回前台基本功能都有了。

>## jflyfox2.2 2015-03-22
> 1. 完善我的消息，展示、回复、删除。
> 2. 修改我的消息提示，进行循环请求。

>## 2015-03-18
> 1. 完善我的消息以及回复提示。
> 2. 对文章题目和作者展示进行了优化。
> 3. 加入了点击用户访问地址，以便后续做个人博客发布。
> 4. 完善评论删除功能，我的消息中回复功能未实现。

>## 2015-03-10
> 1. 评论可以回复了。
> 2. 前台controller进行了细化。
> 3. 加入了我的消息模块，功能有待完善。

>## 2015-03-03
> 1. 修改评论为可点击项。
> 2. 加入后台管理页面按钮展示限制，只有管理员能看到。

>## 2015-03-02
> 1. 加入页面访问量统计,后台统计以及前端展示。

>## 2015-02-28
> 1. 加入注册功能。
> 2. 加入验证码功能。

>## 2015-02-27
> 1. 完成评论功能，准确展示评论数量。
> 2. 加入后台访问权限控制。
> 3. 加入头像展示功能

>## 2015-02-26
> 1. 前台加入访问量和评论数展示。
> 2. 加入用户访问唯一标示，实现浏览量记录。
> 3. 文章加入是否可评论字段，加入评论功能，完成发布评论，删除评论，评论权限限制。

>## 2015-02-25
> 1. 修改密码将明文改为加密方式。
> 2. 加入Oauth2.0登陆，支持QQ、百度、新浪。
> 3. 修改首页展示，支持用户登陆，注册，待完善。
> 4. 完成个人信息修改功能。
> 5. 加入保存第三方用户。

>## 2015-02-13
> 1. 首页加入回到顶部功能

>## jflyfox2.1 2015-02-02
> 1. 发布jflyfox2.1。

>## 2015-02-02
> 1. 加入maven打包，方便war输出
> 2. 重新制作logo
> 3. 修改新增默认值。
> 4. 修改文章预览页面路径写死问题。

>## 2015-01-30
> 1. 去除ueditor改为umeditor
> 2. 删除原有的文章管理
> 3. 对初始化sql进行优化，对数据进行了重新梳理。
> 4. 去除ztree避免有树结构
> 5. 优化代码，解决ie、火狐、谷歌样式兼容性问题

>## jflyfox2.0
> 1. 模板全面更新为beetl，后台继承最新jflyfox_Jfinal.

>## jflyfox1.1
> 1. jsp最后版本

>## jflyfox1.0
> 1. 初始化版本

