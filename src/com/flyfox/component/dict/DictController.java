package com.flyfox.component.dict;

import com.flyfox.component.util.StrUtils;
import com.jfinal.core.Controller;

public class DictController extends Controller {

	private static final String path = "/component/dict/";

	public void index() {
		setAttr("dictList", Dict.dao.find());
		render(path + "list_dict.jsp");
	}

	public void add() {
		render(path + "add_dict.jsp");
	}

	public void edit() {
		render(path + "edit_dict.jsp");
	}

	public void main() {
		setAttr("dictMainList", DictMain.dao.find());
		render(path + "list_dict_main.jsp");
	}

	public void add_main() {
		String dict_type = getPara("dict_type");
		if (StrUtils.isNotEmpty(dict_type)) {
			DictMain dictMain = DictMain.dao.findById(dict_type);
			setAttr("dictMain", dictMain);
		}
		render(path + "add_dict_main.jsp");
	}

	public void save_main() {
		DictMain dictMain = getModel(DictMain.class);
		dictMain.save();
		render(path + "edit_dict_main.jsp");
	}

	public void delete_main() {
		String dict_type = getPara(0);
		DictMain.dao.deleteById(dict_type);
		redirect("/dict/main");
	}
}
