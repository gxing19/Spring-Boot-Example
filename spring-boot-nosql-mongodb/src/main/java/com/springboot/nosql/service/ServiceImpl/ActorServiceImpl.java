package com.springboot.nosql.service.ServiceImpl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.springboot.nosql.dao.ActorDao;
import com.springboot.nosql.entity.Actor;
import com.springboot.nosql.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorDao actorDao;

    @Override
    public void saveActor(Actor actor) {
        actorDao.saveActor(actor);
    }

    @Override
    public Actor queryByActorId(Long actorId) {
        return actorDao.queryByActorId(actorId);
    }

    @Override
    public List<Actor> queryByFirstName(String firstName) {
        return actorDao.queryByFirstName(firstName);
    }

    @Override
    public UpdateResult updateActor(Actor actor) {
        return actorDao.updateActor(actor);
    }

    @Override
    public DeleteResult deleteByActorId(Long actorId) {
        return actorDao.deleteByActorId(actorId);
    }
}
