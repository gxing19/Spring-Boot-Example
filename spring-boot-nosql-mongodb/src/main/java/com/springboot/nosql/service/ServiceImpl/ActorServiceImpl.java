package com.springboot.nosql.service.ServiceImpl;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.springboot.nosql.entity.Actor;
import com.springboot.nosql.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    /**
     * 保存对象
     *
     * @param actor
     */
    @Override
    public void saveActor(Actor actor) {
        mongoTemplate.save(actor);
    }

    /**
     * 保存集合对象
     *
     * @param actorList
     */
    @Override
    public void saveActorList(List<Actor> actorList) {
        mongoTemplate.insert(actorList, Actor.class);
        mongoTemplate.insertAll(actorList);
    }


    /**
     * 根据ID查询
     *
     * @param actorId
     * @return
     */
    @Override
    public Actor queryByActorId(Long actorId) {
        return mongoTemplate.findById(actorId, Actor.class);
    }

    /**
     * 条件查询
     *
     * @param firstName
     * @return
     */
    @Override
    public List<Actor> queryByFirstName(String firstName) {
        Query query = new Query(Criteria.where("firstName").is(firstName));
        return mongoTemplate.find(query, Actor.class);
    }

    /**
     * 更新操作
     *
     * @param actor
     * @return
     */
    @Override
    public UpdateResult updateActor(Actor actor) {
        Query query = new Query(Criteria.where("actorId").is(actor.getActorId()));

        Update update = new Update().set("firstName", actor.getFirstName());
        //更新查询返回结果集的第一条
        return mongoTemplate.updateFirst(query, update, Actor.class);

        //更新查询返回结果集的所有
//        mongoTemplate.updateMulti(query, update, Actor.class).var
    }

    /**
     * 特殊更新,有则修改,无则添加
     *
     * @param actor
     * @return
     */
    @Override
    public UpdateResult addNXupdateEX(Actor actor) {
        Query query = new Query(Criteria.where("actorId").is(actor.getActorId()));
        Update update = Update.update("firstName", actor.getFirstName())
                .set("lastName", actor.getLastName());
        return mongoTemplate.upsert(query, update, Actor.class);
    }

    /**
     * 删除操作
     *
     * @param actorId
     * @return
     */
    @Override
    public DeleteResult deleteByActorId(Long actorId) {
        Query query = new Query(Criteria.where("actorId").is(actorId));
        return mongoTemplate.remove(query, Actor.class);
    }


    /**
     * 读取文件
     *
     * @throws IOException
     */
    public void findFilesInGridFs() throws IOException {
        Query query = new Query(Criteria.where("filename").is("logo.png"));
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
        InputStream inputStream = resource.getInputStream();

    }
}
