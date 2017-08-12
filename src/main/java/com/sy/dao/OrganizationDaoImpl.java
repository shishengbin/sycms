package com.sy.dao;

import com.sy.entity.Organization;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 *<p>Organization: Shi Shengbin
 *<p>Date: 16-12-30
 *<p>Version: 1.0
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Organization createOrganization(final Organization organization) {
        final String sql = "insert into sys_organization(code, name, parent_id, parent_ids, available,create_time) values(?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, organization.getCode());
                psst.setString(count++, organization.getName());
                psst.setLong(count++, organization.getParentId());
                psst.setString(count++, organization.getParentIds());
                psst.setBoolean(count++, organization.getAvailable());
               // psst.setDate(count++, new Date(organization.getCreateTime().getTime()));
                psst.setTimestamp(count++, new Timestamp(organization.getCreateTime().getTime()));
                return psst;
            }
        }, keyHolder);
        organization.setId(keyHolder.getKey().longValue());
        return organization;
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        final String sql = "update sys_organization set code=?,name=?, parent_id=?, parent_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                organization.getCode(),organization.getName(), organization.getParentId(), organization.getParentIds(), organization.getAvailable(), organization.getId());
        return organization;
    }

    public void deleteOrganization(Long organizationId) {
        Organization organization = findOne(organizationId);
        final String deleteSelfSql = "delete from sys_organization where id=?";
        jdbcTemplate.update(deleteSelfSql, organizationId);
        final String deleteDescendantsSql = "delete from sys_organization where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, organization.makeSelfAsParentIds() + "%");
    }


    @Override
    public Organization findOne(Long organizationId) {
        final String sql = "select id, code,name, parent_id, parent_ids, available ,create_time,update_time  from sys_organization where id=?";
        List<Organization> organizationList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class), organizationId);
        if(organizationList.size() == 0) {
            return null;
        }
        return organizationList.get(0);
    }

    @Override
    public List<Organization> findAll() {
        final String sql = "select id, code,name, parent_id, parent_ids, available,create_time,update_time from sys_organization";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class));
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        // 改成not exists 利用索引
        final String sql = "select id, name, parent_id, parent_ids, available ,create_time,update_time from sys_organization where id!=? and parent_ids not like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class), excludeOraganization.getId(), excludeOraganization.makeSelfAsParentIds() + "%");
    }

    @Override
    public void move(Organization source, Organization target) {
        String moveSourceSql = "update sys_organization set parent_id=?,parent_ids=? where id=?";
        jdbcTemplate.update(moveSourceSql, target.getId(), target.getParentIds(), source.getId());
        String moveSourceDescendantsSql = "update sys_organization set parent_ids=concat(?, substring(parent_ids, length(?))) where parent_ids like ?";
        jdbcTemplate.update(moveSourceDescendantsSql, target.makeSelfAsParentIds(), source.makeSelfAsParentIds(), source.makeSelfAsParentIds() + "%");
    }

	@Override
	public List<Organization> findAllOrgByPage(Organization organization,Map<String,Object> map) {
		final String sql="select id, code,name, parent_id, parent_ids, available,create_time,update_time  from sys_organization where 1=1 ";
		final StringBuilder sbsql=new StringBuilder(sql+getConditionSql(organization));
        setLimit(sbsql,map);
		return jdbcTemplate.query(sbsql.toString(), new BeanPropertyRowMapper(Organization.class));
		
	}

	@Override
	public Long totalCount(Organization organization,Map<String, Object> map) {
		final String baseSql="select count(*) from sys_organization where 1=1 ";
	    final StringBuilder sbsql=new StringBuilder(baseSql+getConditionSql(organization));
		return  jdbcTemplate.queryForObject(sbsql.toString(), Long.class);
	}
	
	private String getConditionSql(Organization organization){
		String condSql="";
		if(null!=organization && StringUtils.isNotBlank(organization.getCode())){
			condSql+=(" and code like '%"+organization.getCode()+"%'");
		}
		if(null!=organization && StringUtils.isNotBlank(organization.getName())){
			condSql+=(" and name like '%"+organization.getName()+"%'");
		}
		return condSql;
	}
	
	
	private void setLimit(StringBuilder sbsql,Map<String,Object> map){
		sbsql.append(" limit ").append(map.get("lowerLimit")).append(" , ").append(map.get("upperLimit"));
	}
	
}
