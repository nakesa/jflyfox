package com.flyfox.modules.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyfox.base.controller.BaseController;
import com.flyfox.component.route.ControllerBind;
import com.flyfox.modules.money.TbMoney;
import com.flyfox.modules.project.TbProject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

@ControllerBind(controllerKey = "/web")
public class Home extends BaseController {

	private static final String path = "/pages/web/";

	public void index() {

		// 总费用报表
		String sql = "select COALESCE(sum(case when type = '24' then amount else 0 end),0) a,"
				+ "COALESCE(sum(case when type = '23' then amount else 0 end),0) b,"
				+ "COALESCE(sum(case when type = '22' then amount else 0 end),0) c from tb_money";
		Record totalMoneyRecord = Db.findFirst(sql);
		JSONArray totalMoney = new JSONArray();
		// 预算
		totalMoney.add(totalMoneyRecord.getBigDecimal("a"));
		// 支出
		totalMoney.add(totalMoneyRecord.getBigDecimal("b"));
		// 收入
		totalMoney.add(totalMoneyRecord.getBigDecimal("c"));
		setAttr("totalMoney", totalMoney.toJSONString());

		// 项目总金额
		// 名称
		String formatSql = "select sum(%1$s) as a,project_id from tb_money group by project_id order by project_id";
		JSONObject projectMoney = new JSONObject();
		sql = "select id,name from tb_project where id in (select project_id from tb_money)  order by id";
		JSONArray array = new JSONArray();
		List<Record> listRecord = Db.find(sql);
		for (Record record : listRecord) {
			array.add(record.getStr("name"));
		}
		projectMoney.put("name", array);
		// 预算
		sql = String.format(formatSql, "case when type = '24' then amount else 0 end");
		projectMoney.put("ys", getArray(sql, "a"));
		// 支出
		sql = String.format(formatSql, "case when type = '23' then amount else 0 end");
		projectMoney.put("zc", getArray(sql, "a"));
		// 收入
		sql = String.format(formatSql, "case when type = '22' then amount else 0 end");
		projectMoney.put("sr", getArray(sql, "a"));
		setAttr("projectMoney", projectMoney.toJSONString());

		// 项目信息
		sql = "select * from tb_project";
		List<TbProject> listProject = TbProject.dao.find(sql);
		setAttr("listProject", listProject);

		// 费用明细
		sql = "select t.*,p.name as project_name from tb_money t,tb_project p where t.project_id = p.id order by pay_time desc";
		List<TbMoney> listMoney = TbMoney.dao.find(sql);
		setAttr("listMoney", listMoney);
		
		// 项目费用明细
		Map<Integer, List<TbMoney>> mapMoney = new HashMap<Integer, List<TbMoney>>();
		for (TbMoney money : listMoney) {
			List<TbMoney> listTmp = mapMoney.get(money.getInt("project_id"));
			if (listTmp == null) {
				listTmp = new ArrayList<TbMoney>();
			}
			listTmp.add(money);
			mapMoney.put(money.getInt("project_id"), listTmp);
		}
		setAttr("mapMoney", mapMoney);

		//项目列表
		setAttr("optionList", TbProject.selectProject(null));
		
		render(path + "home.jsp");
	}

	private JSONArray getArray(String sql, String name) {
		JSONArray array = new JSONArray();
		List<Record> list = Db.find(sql);
		for (Record record : list) {
			array.add(record.getBigDecimal(name));
		}
		return array;
	}
}
