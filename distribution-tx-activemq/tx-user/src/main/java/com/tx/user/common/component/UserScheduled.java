package com.tx.user.common.component;

import com.tx.user.entity.event.Event;
import com.tx.user.entity.event.EventManager;
import com.tx.user.service.UserService;
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
public class UserScheduled {
    private static final Logger logger = LogManager.getLogger(UserScheduled.class);

    @Autowired
    private UserService userService;
    @Autowired
    private EventManager eventManager;

    @Scheduled(cron = "*/5 * * * * *")
    public void executeEvent() {
        List<Event> eventList = eventManager.getNewEventList();
        if (!CollectionUtils.isEmpty(eventList)) {
            System.out.println("新建用户的事件记录总数：" + eventList.size());
            eventManager.sendEventQueue("user-success-queue", eventList);
        } else {
            System.out.println("待处理的事件总数为：0");
        }
    }
}
