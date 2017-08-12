package com.sy.model.biz.vo;

import com.github.pagehelper.PageHelper;
import com.sy.model.core.domain.CmsNewsCategoryExample;
import com.sy.model.core.domain.CmsNewsCategoryExample.Criteria;

public class CmsNewsCategoryVo extends BaseSearchObject{

	private String newsName;

	public String getNewsName() {
		return newsName;
	}

	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}

	@Override
	public CmsNewsCategoryExample toExample() {
		
		CmsNewsCategoryExample filter = new CmsNewsCategoryExample();
		Criteria criteria = filter.createCriteria();
		
		PageHelper.startPage(this.getPageNum(), this.getNumPerPage());
		
		criteria.andNewsStatusEqualTo(RecordState.normal.getByteIndex());
		if (this.getNewsName() != null) {
			criteria.andNewsNameLike("%"+this.getNewsName()+"%");
		}
		return filter;
	}

}
