package com.sy.dao;

import com.sy.entity.User;
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

/**
 * <p>User: Shi Sheng bin
 * <p>Date: 16-12-30
 * <p>Version: 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User createUser(final User user) {
		final String sql = "insert into sys_user(organization_id, username, password, salt, role_ids, locked,create_time) values(?,?,?,?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql,new String[] { "id" });
				int count = 1;
				psst.setLong(count++, user.getOrganizationId());
				psst.setString(count++, user.getUsername());
				psst.setString(count++, user.getPassword());
				psst.setString(count++, user.getSalt());
				psst.setString(count++, user.getRoleIdsStr());
				psst.setBoolean(count++, user.getLocked());
				//psst.setDate(count++, new Date(user.getCreateTime().getTime()));
				psst.setTimestamp(count++, new Timestamp(user.getCreateTime().getTime()));
				return psst;
			}
		}, keyHolder);

		user.setId(keyHolder.getKey().longValue());
		return user;
	}

	public User updateUser(User user) {
		String sql = "update sys_user set organization_id=?,username=?, password=?, salt=?, role_ids=?, locked=? where id=?";
		jdbcTemplate.update(sql, user.getOrganizationId(), user.getUsername(),
				user.getPassword(), user.getSalt(), user.getRoleIdsStr(),
				user.getLocked(), user.getId());
		return user;
	}

	public void deleteUser(Long userId) {
		String sql = "delete from sys_user where id=?";
		jdbcTemplate.update(sql, userId);
	}

	@Override
	public User findOne(Long userId) {
		String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked ,create_time,update_time from sys_user where id=?";
		List<User> userList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(User.class), userId);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public List<User> findAll() {
		String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked ,create_time,update_time from sys_user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
	}

	@Override
	public User findByUsername(String username) {
		String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked ,create_time,update_time   from sys_user where username=?";
		List<User> userList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(User.class), username);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
}
