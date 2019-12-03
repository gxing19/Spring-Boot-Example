package com.springboot.demo.localdate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeConverter  extends JsonSerializer<LocalDateTime> {

    /**
     * 将LocalDateTime字段以时间戳的方式返回给前端 添加日期转化类
     *
     * 将LocalDateTime字段以指定格式化日期的方式返回给前端
     * 在LocalDateTime字段上添加 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss") 注解
     *
     * 对前端传入的日期进行格式化
     * 在LocalDateTime字段上添加 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")注解
     * @param localDateTime
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}
