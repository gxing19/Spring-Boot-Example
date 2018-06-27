package com.springboot.jdbc.service.impl;

import com.springboot.jdbc.entity.Actor;
import com.springboot.jdbc.service.ActorService;
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
public class ActorServiceImpl implements ActorService {

	@Autowired
	private JdbcTemplate jdbcTemplate;


    /**
     * 增-insert
     * @param actor
     * @return
     */
	@Override
	public int addActor(Actor actor) {
		String sql = "insert into actor (actor_id,first_name,last_name,last_update) values (?,?,?,?)";
		return jdbcTemplate.update(sql, actor.getActorId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate());
	}

    /**
     * 改-update
     * @param firstName
     * @param actorId
     * @return
     */
    @Override
    public int updateActor(String firstName, Long actorId) {
        String sql = "update actor set first_name = ? where actor_id = ?";
        return jdbcTemplate.update(sql, firstName, actorId);
    }

    /**
     * 删除-delete
     * @param actorId
     * @return
     */
    @Override
    public int deleteActor(Long actorId) {
        String sql = "delete from actor where actor_id = ?";
        return jdbcTemplate.update(sql,actorId);
    }

    /**
     * rowCount
     * @return
     */
    @Override
    public int queryCount() {
        String sql = "select count(*) from actor";
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
    public List<Actor> queryActorList(){
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

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
