package com.springboot.nosql.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.springboot.nosql.dao.ActorDao;
import com.springboot.nosql.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActorDaoImpl implements ActorDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveActor(Actor actor) {
        mongoTemplate.save(actor);
    }

    @Override
    public Actor queryByActorId(Long actorId) {
        return mongoTemplate.findById(actorId, Actor.class);
    }

    @Override
    public List<Actor> queryByFirstName(String firstName) {
        Query query = new Query(Criteria.where("firstName").is(firstName));
        return mongoTemplate.find(query, Actor.class);
    }

    @Override
    public UpdateResult updateActor(Actor actor) {
        Query query = new Query(Criteria.where("actorId").is(actor.getActorId()));

        Update update = new Update().set("firstName", actor.getFirstName());
        //更新查询返回结果集的第一条
        return mongoTemplate.updateFirst(query, update, Actor.class);

        //更新查询返回结果集的所有
//        return mongoTemplate.updateMulti(query, update, Actor.class);
    }

    @Override
    public DeleteResult deleteByActorId(Long actorId) {
        Query query = new Query(Criteria.where("actorId").is(actorId));
        return mongoTemplate.remove(query, Actor.class);
    }
}
