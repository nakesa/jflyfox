package com.flyfox.component.dao.model;

import java.util.ArrayList;
import java.util.List;

import com.flyfox.util.Constants;

public class Result {

	private List<Model> list = null;
	protected int counter;
	protected int startPosition;
	protected int pageSize;
	protected int pageno;

	public Result() {
		list = null;
		counter = 0;
		startPosition = 0;
		pageSize = Constants.DEFAULT_PAGE_SIZE;
	}

	public Result(int start, int pageSize) {
		list = null;
		counter = 0;
		startPosition = 0;
		this.pageSize = Constants.DEFAULT_PAGE_SIZE;
		startPosition = start;
		this.pageSize = pageSize;
	}

	public int getCounter() {
		return counter;
	}

	public int getCurrentPageNo() {
		if (pageSize <= 0)
			return 1;
		else
			return 1 + ((startPosition + pageSize) - 1) / pageSize;
	}

	public int getMaxPageNo() {
		if (pageSize <= 0)
			return 1;
		else
			return ((counter + pageSize) - 1) / pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public int getStartPosition(int pageNo) {
		if (pageNo < 1) {
			return 0;
		} else {
			int start = pageSize * (pageNo - 1);
			startPosition = start;
			return start;
		}
	}

	public static int getStartPosition(int pageNo, int pageSize) {
		return pageSize * (pageNo - 1);
	}

	public void setCounter(int newCounter) {
		counter = newCounter;
	}

	public void setPageSize(int newpageSize) {
		pageSize = newpageSize;
	}

	public void setStartPosition(int newStartPosition) {
		startPosition = newStartPosition;
	}

	public int getResultSize() {
		if (list == null)
			return 0;
		else
			return list.size();
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public boolean exist(Model model) {
		if (list == null || model == null)
			return false;
		return list.contains(model);
	}

	public List<Model> getList() {
		if (list == null) {
			list = new ArrayList<Model>();
		}
		return list;
	}

	public void setList(List<Model> list) {
		this.list = list;
	}

}
