package com.jflyfox.modules.pageview;

import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_pageview")
public class TbPageView extends BaseModel<TbPageView> {

	private static final long serialVersionUID = 1L;
	
	public static final TbPageView dao = new TbPageView();

}