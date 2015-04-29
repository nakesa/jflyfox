package com.flyfox.modules.web.service;

import java.util.List;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.base.BaseService;
import com.flyfox.modules.folder.FolderService;
import com.flyfox.modules.folder.TbFolder;

public class HomeService extends BaseService {

	/**
	 * 展示菜单
	 * 
	 * 2015年3月10日 下午4:44:54 flyfox 330627517@qq.com
	 * 
	 * @param folders_selected
	 *            选择项
	 */
	public void showDirectory(BaseController controller, Object folders_selected) {
		// 目录列表
		List<TbFolder> folders = new FolderService().getFolderList();
		controller.setAttr("folders", folders);
		controller.setAttr("folders_selected", folders_selected);
	}

}
