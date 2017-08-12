package com.sy.dao;

import com.sy.entity.Resource;

import java.util.List;

/**
 * <p>Resource: Shi Shengbin
 * <p>Date: 16-12-30
 * <p>Version: 1.0
 */
public interface ResourceDao {

    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();

}
