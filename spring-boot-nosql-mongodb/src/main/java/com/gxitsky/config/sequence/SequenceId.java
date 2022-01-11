package com.gxitsky.config.sequence;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

/**
 * @name: SequenceId
 * @desc: TODO
 * @author: gxing
 * @date: 2019-06-13 09:45
 **/
@Document(collection = "sequence")
public class SequenceId {

    @Id
    private String id;

    /**
     * 自增ID
     */
    @Field("seq_id")
    private long seqId;

    /**
     * 集合名称
     */
    @Field("coll_name")
    private String collName;

    public String getId() {
        return id;
    }

    public SequenceId setId(String id) {
        this.id = id;
        return this;
    }

    public long getSeqId() {
        return seqId;
    }

    public SequenceId setSeqId(long seqId) {
        this.seqId = seqId;
        return this;
    }

    public String getCollName() {
        return collName;
    }

    public SequenceId setCollName(String collName) {
        this.collName = collName;
        return this;
    }
}