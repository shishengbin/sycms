package com.sy.dao;

import java.util.List;
import java.util.Map;

import com.sy.entity.Organization;

/**
 * <p>Organization: Shi Shengbin
 * <p>Date: 16-12-30
 * <p>Version: 1.0
 */
public interface OrganizationDao {

	public Organization createOrganization(Organization organization);

	public Organization updateOrganization(Organization organization);

	public void deleteOrganization(Long organizationId);

	Organization findOne(Long organizationId);

	List<Organization> findAll();

	List<Organization> findAllWithExclude(Organization excludeOraganization);

	void move(Organization source, Organization target);
	
	List<Organization> findAllOrgByPage(Organization organization,Map<String,Object> map);
	
	Long totalCount(Organization organization,Map<String,Object> map);
	
}
