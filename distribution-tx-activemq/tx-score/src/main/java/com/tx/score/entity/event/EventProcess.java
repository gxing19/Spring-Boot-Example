package com.tx.score.entity.event;

public enum EventProcess {
    NEW("NEW","新建"),
    PUBLISHED("PUBLISHED","已发布"),
    PROCESSED("PROCESSED","已处理");

    private String value;
    private String desc;

    EventProcess(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public EventProcess setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public EventProcess setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
