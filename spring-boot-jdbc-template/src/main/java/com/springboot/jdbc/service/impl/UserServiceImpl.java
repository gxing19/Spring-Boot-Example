package com.springboot.jdbc.service.impl;

import com.springboot.jdbc.entity.Actor;
import com.springboot.jdbc.entity.User;
import com.springboot.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 演示省略DAO层
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;


    /**
     * 增-insert
     * @param user
     * @return
     */
	@Override
	public int addUser(User user) {
		String sql = "insert into user (id,name,age,address) values (?,?,?,?)";
		return jdbcTemplate.update(sql, user.getId(),user.getName(), user.getAge(), user.getAddress());
	}

    /**
     * 改-update
     * @param name
     * @return
     */
    @Override
    public int updateUser(String name, Long id) {
        String sql = "update user set name = ? where id = ?";
        return jdbcTemplate.update(sql, name, id);
    }

    /**
     * 删除-delete
     * @param id
     * @return
     */
    @Override
    public int deleteUser(Long id) {
        String sql = "delete from user where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    /**
     * rowCount
     * @return
     */
    @Override
    public int queryCount() {
        String sql = "select count(*) from user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * count by
     * @return
     */
    @Override
    public int queryCountByLastName(String lastName) {
        String sql = "select count(*) from actor where last_name = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,lastName);
    }

    /**
     * 查询结果是字符串
     * @param actorId
     * @return
     */
    @Override
    public String queryLastName(Long actorId) {
        String sql = "select last_name from actor where actor_id = ?";
        Object[] objArr = new Object[1];
        objArr[0] = actorId;
        return jdbcTemplate.queryForObject(sql, objArr, String.class);
    }

    /**
     * 返回对象
     * @param actorId
     * @return
     */
    @Override
    public Actor queryByActorId(Long actorId) {
        String sql = "select * from actor where actor_id = ?";
        Object[] objArr = new Object[1];
        objArr[0] = actorId;

        RowMapper<Actor> actorRowMapper = new RowMapper<Actor>() {
            @Override
            public Actor mapRow(ResultSet resultSet, int i) throws SQLException {
                Actor actor = new Actor()
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setActorId(resultSet.getLong("actor_id"))
                        .setLastUpdate(resultSet.getDate("last_update"));
                return actor;
            }
        };
        return jdbcTemplate.queryForObject(sql, objArr, actorRowMapper);
    }

    /**
     * 查询对象集合
     * @return
     */
    @Override
    public List<Actor> queryActorList() {
        String sql = "select * from actor";
        RowMapper<Actor> actorRowMapper = new RowMapper<Actor>() {
            @Override
            public Actor mapRow(ResultSet resultSet, int i) throws SQLException {
                Actor actor = new Actor()
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setActorId(resultSet.getLong("actor_id"))
                        .setLastUpdate(resultSet.getDate("last_update"));
                return actor;
            }
        };
        return jdbcTemplate.query(sql,actorRowMapper);
    }


}
