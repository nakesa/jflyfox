package com.jflyfox.modules.folder;

import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_folder")
public class TbFolder extends BaseModel<TbFolder> {

	private static final long serialVersionUID = 1L;
	/**
	 * 根结点，首页
	 */
	public static final int ROOT = 1;
	
	public static final TbFolder dao = new TbFolder();

}