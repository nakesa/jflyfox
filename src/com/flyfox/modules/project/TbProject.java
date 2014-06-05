package com.flyfox.modules.project;

import java.util.ArrayList;
import java.util.List;

import com.flyfox.base.model.BaseModel;
import com.flyfox.component.model.ModelBind;

@ModelBind(table = "tb_project")
public class TbProject extends BaseModel<TbProject> {

	private static final long serialVersionUID = 1L;
	public static final TbProject dao = new TbProject();

	public static String selectProject(Integer selected) {
		List<TbProject> list = new ArrayList<TbProject>();
		list = TbProject.dao.find("select * from tb_project");
		StringBuffer sb = new StringBuffer();
		for (TbProject project : list) {
			sb.append("<option value=\"");
			sb.append(project.getInt("id"));
			sb.append("\" ");
			sb.append(project.getInt("id").equals(selected) ? "selected" : "");
			sb.append(">");
			sb.append(project.getStr("name"));
			sb.append("</option>");
		}
		return sb.toString();
	}
}
