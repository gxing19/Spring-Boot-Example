package com.gxitsky.service.impl;

import com.alibaba.fastjson.JSON;
import com.gxitsky.entity.Score;
import com.gxitsky.entity.event.Event;
import com.gxitsky.entity.event.EventManager;
import com.gxitsky.entity.event.EventProcess;
import com.gxitsky.repository.ScoreRepository;
import com.gxitsky.service.ScoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreServiceImpl implements ScoreService {
    private static final Logger logger = LogManager.getLogger(ScoreServiceImpl.class);

    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private EventManager eventManager;

    @Transactional(rollbackFor = Exception.class)
    @JmsListener(destination = "user-success-queue")
    public void handleUserSuccess(String message) {
        System.out.println("队列监听到的文本：" + message);
        Event event = null;
        try {
            event = JSON.parseObject(message, Event.class);
            String content = event.getContent();
            Score score = JSON.parseObject(content, Score.class);
            //事件
            event.setProcess(EventProcess.PUBLISHED.getValue());
            event.setSourceId(event.getId()).setId(null);
            eventManager.saveEvent(event);
            //保存积分
            scoreRepository.save(score);
            //更新事件阶段
            eventManager.updateEventProcessToProcessed(event);
//            throw new RuntimeException();
        } catch (Exception e) {
            /*这里处理有些问题
             * 抛出异常,默认消息会重发6次再送到死信队列
             * 下面执行发送消息到失败队列并不起作用
             * 估计要自定义重发机制和死信队列
             * 待完善.....*/
            eventManager.sendEventQueue("score-failure-queue", event);
            throw e;
        }
    }

    @Override
    public void saveScore(Score score) {
        scoreRepository.save(score);
    }
}
