package com.gxitsky.component;

import com.alibaba.fastjson.JSON;
import com.gxitsky.entity.Score;
import com.gxitsky.entity.event.Event;
import com.gxitsky.entity.event.EventManager;
import com.gxitsky.service.ScoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@EnableScheduling
public class ScoreScheduled {
    private static final Logger logger = LogManager.getLogger(ScoreScheduled.class);

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private EventManager eventManager;

    @Scheduled(cron = "*/5 * * * * *")
    public void executeEvent() {
        List<Event> eventList = eventManager.getPublishedEventList();
        if (!CollectionUtils.isEmpty(eventList)) {
            System.out.println("待处理的事件记录总数：" + eventList.size());
            for (Event event : eventList) {
                Score score = JSON.parseObject(event.getContent(), Score.class);
                scoreService.saveScore(score);
                eventManager.updateEventProcessToProcessed(event);
            }
        } else {
            System.out.println("待处理的事件总数为：0");
        }
    }
}
