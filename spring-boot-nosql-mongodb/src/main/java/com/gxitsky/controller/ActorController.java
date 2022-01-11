package com.gxitsky.controller;

import com.gxitsky.repository.ActorRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.gxitsky.entity.Actor;
import com.gxitsky.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/actor")
public class ActorController {
    private final static Logger logger = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;

    /**
     * 保存
     *
     * @param actor
     */
    @RequestMapping("/saveActor")
    public void saveActor(Actor actor) {
        actor.setLastUpdate(new Date());
        actorService.saveActor(actor);
    }

    /**
     * 根据主键id查
     *
     * @param actorId
     * @return
     */
    @RequestMapping("/queryByActorId")
    public Actor queryByActorId(Long actorId) {
        return actorService.queryByActorId(actorId);
    }

    /**
     * 根据用户名查
     *
     * @param firstName
     * @return
     */
    @RequestMapping("/queryByFirstName")
    public List<Actor> queryByFirstName(String firstName) {
        return actorService.queryByFirstName(firstName);
    }

    /**
     * 更新
     *
     * @param Actor
     * @return
     */
    @RequestMapping("/updateActor")
    public UpdateResult updateActor(Actor Actor) {
        return actorService.updateActor(Actor);
    }

    /**
     * 删除
     *
     * @param actorId
     * @return
     */
    @RequestMapping("/deleteByActorId")
    public DeleteResult deleteByActorId(Long actorId) {
        return actorService.deleteByActorId(actorId);
    }

}
