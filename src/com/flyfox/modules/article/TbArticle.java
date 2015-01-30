package com.flyfox.modules.article;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_article")
public class TbArticle extends BaseModel<TbArticle> {

	private static final long serialVersionUID = 1L;
	public static final TbArticle dao = new TbArticle();

}