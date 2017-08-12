package com.sy.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sy.model.biz.vo.CmsNewsCategoryVo;
import com.sy.service.cms.CmsNewsCategoryService;

public class CmsNewsCategoryServiceTest extends BaseSpringTestCase {
	
	@Autowired
	private CmsNewsCategoryService cncategoryservice;

	@Test
	public void findAllNews(){
		CmsNewsCategoryVo cncv=new CmsNewsCategoryVo();
		cncv.setNewsName("a");
		System.out.println(cncategoryservice.findAllNewsCategoriesByPage(cncv).getPageNum());
		//Assert.assertEquals(1, cncs.findAllNewsCategoriesByPage(cncv).size());
	}
}
