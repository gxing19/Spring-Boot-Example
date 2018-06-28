package com.springboot.jpa.controller;

import com.springboot.jpa.entity.Actor;
import com.springboot.jpa.service.ActorService;
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
    private static final Logger logger = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;

    /**
     * 添加
     * @param actor
     * @return
     */
    @RequestMapping(value = "/addActor")
    public Actor addActor(Actor actor){
        actor.setLastUpdate(new Date());
        return actorService.addActor(actor);
    }

    /**
     * 删除
     * @param actorId
     * @return
     */
    @RequestMapping(value = "/deleteByActorId")
    public void deleteByActorId(Long actorId){
        actorService.deleteByActorId(actorId);
    }

    /**
     * 改
     * @param actorId
     * @param firstName
     * @return
     */
    @RequestMapping(value = "/updateFirstName")
    public int updateFirstName(Long actorId, String firstName){
        return actorService.updateFirstName(actorId, firstName);
    }

    /**
     * 查所有
     * @return
     */
    @RequestMapping("/queryAll")
    public List<Actor> queryAll(){
        return actorService.queryAll();
    }

    /**
     * 主键条件查询
     * @param actorId
     * @return
     */
    @RequestMapping("/queryByActorId")
    public Actor queryByActorId(Long actorId){
        return actorService.queryByActorId(actorId);
    }

    /**
     * 非主键条件查询
     * @param firstName
     * @return
     */
    @RequestMapping("queryByFirstName")
    public List<Actor> queryByFirstName(String firstName){
        return actorService.queryByFirstName(firstName);
    }

    /**
     * 统计
     * @return
     */
    @RequestMapping("/count")
    public Long countActor(){
        return actorService.countActor();
    }

    /**
     * 条件统计
     * @param actor
     * @return
     */
    @RequestMapping("/countBy")
    public Long countBy(Actor actor){
        return actorService.countBy(actor);
    }




}
