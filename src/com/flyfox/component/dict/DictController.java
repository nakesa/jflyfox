package com.flyfox.component.dict;

import com.flyfox.component.util.StrUtils;
import com.jfinal.core.Controller;

public class DictController extends Controller {

	private static final String path = "/component/dict/";

	public void index() {
		String form = getPara(0);
		if ("detail".equals(form)) {
			setAttr("dictList", Dict.dao.find());
			render(path + "list_dict.jsp");
		}else {
			setAttr("dictMainList", DictMain.dao.find());
			render(path + "list_dict_main.jsp");
		}
	}

	public void add() {
		String dict_id = getPara("dict_id");
		if (StrUtils.isNotEmpty(dict_id)) {
			Dict dict = Dict.dao.findById(dict_id);
			setAttr("dict", dict);
		}
		render(path + "add_dict.jsp");
	}

	public void save() {
		Dict dict = getModel(Dict.class);
		if(dict.save()){
			render(path + "add_dict.jsp");
		}else {
			renderError(2);
		}
	}

	public void delete() {
		String dict_id = getPara(0);
		Dict.dao.deleteById(dict_id);
		redirect("/dict/detail");
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
		if(dictMain.save()){
			renderError(2);
		}else {
			render(path + "add_dict_main.jsp");
		}
	}

	public void delete_main() {
		String dict_type = getPara(0);
		DictMain.dao.deleteById(dict_type);
		redirect("/dict/main");
	}
}
