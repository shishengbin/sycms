package com.sy.service.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.sy.model.biz.vo.CmsNewsCategoryVo;
import com.sy.model.core.dao.CmsNewsCategoryMapper;
import com.sy.model.core.domain.CmsNewsCategory;

@Service
public class CmsNewsCategoryService {

	@Resource
	private CmsNewsCategoryMapper cncmapper;

	public PageInfo<CmsNewsCategory> findAllNewsCategoriesByPage(CmsNewsCategoryVo cmsNewsCategoryVo) {
		List<CmsNewsCategory> list = new ArrayList<CmsNewsCategory>(0);
		if (null != cmsNewsCategoryVo) {
			list = cncmapper.selectByExampleByPage(cmsNewsCategoryVo.toExample());
		}
		return 	new PageInfo<CmsNewsCategory>(list);
	}
	
	
	public boolean createNewsCategory(CmsNewsCategory ncategory){
		ncategory.setCreateTime(new Date());
		boolean flag=false;
		int num=cncmapper.insertSelective(ncategory);
		if(num>0){
			flag=true;
		}
		return flag;
	}
	
	public CmsNewsCategory findNewsCategoryById(Integer nid){
		return cncmapper.selectByPrimaryKey(nid);
	}
	
	public boolean updateNewsCategory(CmsNewsCategory ncategory){
		boolean flag=false;
		int num=cncmapper.updateByPrimaryKeySelective(ncategory);
		if(num>0){
			flag=true;
		}
		return flag;
	}
	
}