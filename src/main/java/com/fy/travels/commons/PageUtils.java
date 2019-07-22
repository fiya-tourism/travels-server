package com.fy.travels.commons;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页工具类
 * @ClassName:  Page
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: peng
 * @date:   2018年11月14日 下午3:02:04
 * @Copyright: 2018
 */
public class PageUtils<T> {

	//每页条数
	private  Integer pageSize;
	//起始下标
	private Integer pageNum = 1;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
}
