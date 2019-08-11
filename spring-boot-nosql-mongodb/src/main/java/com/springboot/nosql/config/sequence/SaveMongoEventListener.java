package com.springboot.nosql.config.sequence;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * @name: SaveMongoEventListener
 * @desc: 保存事件监听
 * @author: gxing
 * @date: 2019-06-13 09:51
 **/
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 域对象转持久化之前引发的事件
     *
     * @param event
     */
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {

        Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                ReflectionUtils.makeAccessible(field);
                if (field.isAnnotationPresent(GeneratedValue.class)) {
                    field.set(source, getNextId(source.getClass().getSimpleName()));
                }
            }
        });

    }

    /**
     * 获取最新的自增序列ID
     *
     * @param collName
     * @return
     */
    private Long getNextId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("secId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        //如果不存在则插入并返回最新的值
        options.upsert(true).returnNew(true);
        //查找并更新
        SequenceId sequenceId = mongoTemplate.findAndModify(query, update, options, SequenceId.class);
        return sequenceId.getSeqId();

    }


}