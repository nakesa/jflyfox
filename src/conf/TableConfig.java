package conf;

import java.util.HashMap;
import java.util.Map;

import com.flyfox.jfinal.template.CRUD;
import com.flyfox.jfinal.template.FormType;
import com.flyfox.jfinal.template.InputType;
import com.flyfox.jfinal.template.ModelAttr;
import com.flyfox.jfinal.template.TemplateUtils;

import conf.auto.AutoCreate;

/**
 * 自动生成代码
 * 
 * 2015年1月8日 下午4:54:14 flyfox 330627517@qq.com
 */
public class TableConfig {

	public final static Map<String, CRUD> crudMap = new HashMap<String, CRUD>();

	/**
	 * 生成代码
	 * 
	 * 2015年1月8日 下午4:48:09 flyfox 330627517@qq.com
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		init();
		AutoCreate.crudMap = crudMap;
		AutoCreate.createCode();
	}

	public static void init() {
		// 目录管理
		CRUD folder = new CRUD();
		folder.setPrimaryKey("id");
		folder.setUrlKey("folder");
		folder.setName("目录");
		folder.setAttr(new ModelAttr().setKey("name").setName("名称").addSearch().setFormTypeVaild("required='required'"));
		// 不用
		folder.setAttr(new ModelAttr().setKey("path").setName("路径").setOperate((byte) 0));
		folder.setAttr(new ModelAttr().setKey("sort").setName("排序").setInputType(InputType.NUMBER));
		String status = TemplateUtils.radioJson("1", "显示", "2", "隐藏");
		folder.setAttr(new ModelAttr().setKey("status").setName("状态") //
				.setInputType(InputType.RADIO).setFormTypeData(status));

		folder.setAttr(new ModelAttr().setKey("type").setName("类型") //
				.setFormType(FormType.DICT).setFormTypeData("folderType"));
		folder.setAttr(new ModelAttr().setKey("jump_url").setName("地址"));
		folder.setAttr(new ModelAttr().setKey("content").setName("说明").setFormType(FormType.TEXTAREA));
		add(folder);

		// 文章管理
		CRUD article = new CRUD();
		article.setPrimaryKey("id");
		article.setUrlKey("article");
		article.setName("文章");
		article.setAttr(new ModelAttr().setKey("folder_id").setName("目录名称") //
				.setFormType(FormType.SELECT).setFormTypeData("folder_json").addSearch());
		article.setAttr(new ModelAttr().setKey("title").setName("名称").addSearch()
				.setFormTypeVaild("required='required'"));
		// 不处理，富文本单独处理
		article.setAttr(new ModelAttr().setKey("content").setName("内容") //
				.setFormType(FormType.TEXTAREA).setOperate((byte) 0));
		article.setAttr(new ModelAttr().setKey("sort").setName("排序").setInputType(InputType.NUMBER));
		String articleStatus = TemplateUtils.radioJson("1", "显示", "2", "隐藏");
		folder.setAttr(new ModelAttr().setKey("status").setName("状态") //
				.setInputType(InputType.RADIO).setFormTypeData(articleStatus).addSearch());

		article.setAttr(new ModelAttr().setKey("publish_time").setName("发布时间").setInputType(InputType.DATE));
		article.setAttr(new ModelAttr().setKey("publish_user").setName("发布者"));
		article.setAttr(new ModelAttr().setKey("start_time").setName("开始时间").setInputType(InputType.DATE));
		article.setAttr(new ModelAttr().setKey("end_time").setName("结束时间").setInputType(InputType.DATE));

		add(article);

		// 联系人管理
		CRUD contact = new CRUD();
		contact.setPrimaryKey("id");
		contact.setUrlKey("contact");
		contact.setName("联系人");
		contact.setAttr(new ModelAttr().setKey("name").setName("姓名").addSearch()
				.setFormTypeVaild("required='required'"));
		contact.setAttr(new ModelAttr().setKey("phone").setName("手机号"));
		contact.setAttr(new ModelAttr().setKey("email").setName("Email").setInputType(InputType.EMAIL));
		contact.setAttr(new ModelAttr().setKey("addr").setName("地址").removeList());
		contact.setAttr(new ModelAttr().setKey("birthday").setName("生日").removeList().setInputType(InputType.DATE));
		contact.setAttr(new ModelAttr().setKey("remark").setName("说明").setFormType(FormType.TEXTAREA));
		add(contact);

		// 用户管理
		CRUD user = new CRUD();
		user.setPrimaryKey("userid");
		user.setUrlKey("user");
		user.setName("用户");
		user.setAttr(new ModelAttr().setKey("username").setName("登陆名").addSearch()
				.setFormTypeVaild("required='required'"));
		user.setAttr(new ModelAttr().setKey("realname").setName("真实姓名").addSearch());
		user.setAttr(new ModelAttr().setKey("password").setName("密码") //
				.setInputType(InputType.PASSWORD).setFormTypeVaild("required='required'"));
		add(user);
	}

	protected static void add(CRUD contact) {
		crudMap.put(contact.getUrlKey(), contact);
	}

}
