package com.sy.service;

import com.sy.entity.Organization;

import java.util.List;
import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface OrganizationService {


    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
    
    //sss
    List<Organization> findAllOrgByPageManage(Organization organization,Map<String,Object> map);
    Long totalCount(Organization organization,Map<String,Object> map);
}
