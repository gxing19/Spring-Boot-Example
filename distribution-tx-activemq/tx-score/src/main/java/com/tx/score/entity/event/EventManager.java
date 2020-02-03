package com.tx.score.entity.event;

import com.alibaba.fastjson.JSON;
import com.tx.score.entity.Score;
import com.tx.score.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class EventManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送事件到消息队列
     *
     * @param queueName
     * @param event
     */
    public void sendEventQueue(String queueName, Event event) {
        jmsTemplate.convertAndSend(queueName, JSON.toJSONString(event.getContent()));
        this.updateEventProcessToProcessed(event);
    }

    /**
     * 发送事件列表到消息队列
     *
     * @param queueName
     * @param eventList
     */
    public void sendEventQueue(String queueName, List<Event> eventList) {
        if (!CollectionUtils.isEmpty(eventList)) {
            for (Event event : eventList) {
                this.sendEventQueue(queueName, event);
            }
        }
    }

    /**
     * 更新事件进度
     *
     * @param event
     */
    public void updateEventProcessToProcessed(Event event) {
        String sql = "update t_event set process = ?, update_time = ? where id = ?";
        jdbcTemplate.update(sql, EventProcess.PROCESSED.getValue(), new Date(), event.getId());
    }

    public void updateEventProcessToPublished(Event event) {
        String sql = "update t_event set process = ?, update_time = ? where id = ?";
        jdbcTemplate.update(sql, EventProcess.PUBLISHED.getValue(), new Date(), event.getId());
    }

    /**
     * 查询状态是 NEW 的事件
     *
     * @return
     */
    public List<Event> getNewEventList() {
        String sql = "SELECT * FROM t_event e WHERE e.process = ?";
        RowMapper<Event> rowMapper = (rs, rowNum) -> {
            Event event = new Event();
            event.setContent(rs.getString("content"));
            event.setId(rs.getLong("id"));
            event.setProcess(rs.getString("process"));
            event.setType(rs.getString("type"));
            event.setCreateTime(rs.getTimestamp("create_time"));
            event.setUpdateTime(rs.getTimestamp("update_time"));
            return event;
        };
        List<Event> eventList = jdbcTemplate.query(sql, new Object[]{EventProcess.NEW.getValue()}, rowMapper);
        return eventList;
    }

    public List<Event> getPublishedEventList() {
        String sql = "SELECT * FROM t_event e WHERE e.process = ?";
        RowMapper<Event> rowMapper = (rs, rowNum) -> {
            Event event = new Event();
            event.setContent(rs.getString("content"));
            event.setId(rs.getLong("id"));
            event.setProcess(rs.getString("process"));
            event.setType(rs.getString("type"));
            event.setCreateTime(rs.getTimestamp("create_time"));
            event.setUpdateTime(rs.getTimestamp("update_time"));
            return event;
        };
        List<Event> eventList = jdbcTemplate.query(sql, new Object[]{EventProcess.PUBLISHED.getValue()}, rowMapper);
        return eventList;
    }

    /**
     * 保存事件,并发送到消息队列
     *
     * @param event
     * @return
     */
    public Event saveEventAndSendMsg(Event event) {
        try {
            this.saveEvent(event);
            this.sendEventQueue("user-success-queue", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    public Event saveEvent(Event event) {
        event = eventRepository.save(event);
        return event;
    }

    /**
     * 积分事件处理
     *
     * @param id
     */
    public void eventHandle(Long id) {
        Score score = new Score(id, 10, new Date());
        Event event = new Event(EventType.CREATE.toString(), EventProcess.NEW.getValue(), JSON.toJSONString(score));
        event.setCreateTime(new Date());
        this.saveEvent(event);
    }
}
