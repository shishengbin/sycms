package com.sy.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sy.model.biz.vo.CmsNewsCategoryVo;
import com.sy.model.core.dao.CmsNewsCategoryMapper;
import com.sy.model.core.domain.CmsNewsCategory;
import com.sy.service.cms.CmsNewsCategoryService;

public class CmsNewsCategoryMapperTest extends BaseSpringTestCase{
	
	@Autowired
	private CmsNewsCategoryMapper cncmapper;
	
	@Test
	public void findSingleNews(){
		CmsNewsCategory cnc= cncmapper.selectByPrimaryKey(1);
		System.out.println(cnc.getNewsName());
		Assert.assertEquals("abc", cnc.getNewsName());
	}
	
	@Test
	public void findAllNews(){
		CmsNewsCategoryVo cncv=new CmsNewsCategoryVo();
		cncv.setNewsName("ab");
		List<CmsNewsCategory> categorylist=cncmapper.selectByExampleByPage(cncv.toExample());
		for(CmsNewsCategory c :categorylist){
		 System.out.println(c.getNewsName());
		}
		//Assert.assertEquals(1, cncmapper.selectByExampleByPage(cncv.toExample()).size());
	}

}
