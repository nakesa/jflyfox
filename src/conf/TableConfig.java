package conf;

import java.util.HashMap;
import java.util.Map;

import com.flyfox.jfinal.template.CRUD;
import com.flyfox.jfinal.template.FormType;
import com.flyfox.jfinal.template.InputType;
import com.flyfox.jfinal.template.ModelAttr;

import conf.auto.AutoCreate;

/**
 * 自动生成代码
 * 
 * 2015年1月8日 下午4:54:14
 * flyfox 330627517@qq.com
 */
public class TableConfig {

	public final static Map<String, CRUD> crudMap = new HashMap<String, CRUD>();

	/**
	 * 生成代码
	 * 
	 * 2015年1月8日 下午4:48:09
	 * flyfox 330627517@qq.com
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		init();
		AutoCreate.crudMap = crudMap;
		AutoCreate.createCode();
	}
	
	public static void init() {
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
		add(user);
	}

	protected static void add(CRUD contact) {
		crudMap.put(contact.getUrlKey(), contact);
	}

}
