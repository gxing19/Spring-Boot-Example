package com.springboot.cache.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.msgpack.core.annotations.Nullable;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.cache.support.NullValue;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @name: MsgpackRedisSerializer
 * @desc: TODO
 * @author: gxing
 * @date: 2018-11-05 14:57
 **/
public class MsgpackRedisSerializer<T> implements RedisSerializer<Object> {

    static final byte[] EMPTY_ARRAY = new byte[0];
    private final ObjectMapper mapper;


    public MsgpackRedisSerializer() {
        this.mapper = new ObjectMapper(new MessagePackFactory());
        this.mapper.registerModule((new SimpleModule()).addSerializer(new NullValueSerializer(null)));
        this.mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL.NON_FINAL, JsonTypeInfo.As.PROPERTY.PROPERTY);
    }

    @Override
    public byte[] serialize(@Nullable Object source) throws SerializationException {
        if (source == null) {
            return EMPTY_ARRAY;
        } else {
            try {
                return this.mapper.writeValueAsBytes(source);
            } catch (JsonProcessingException var3) {
                throw new SerializationException("Could not write JSON: " + var3.getMessage(), var3);
            }
        }
    }

    @Override
    public Object deserialize(@Nullable byte[] source) throws SerializationException {
        return this.deserialize(source, Object.class);
    }

    @Nullable
    public <T> T deserialize(@Nullable byte[] source, Class<T> type) throws SerializationException {
        Assert.notNull(type, "Deserialization type must not be null! Pleaes provide Object.class to make use of Jackson2 default typing.");
        if (source == null || source.length == 0) {
            return null;
        } else {
            try {
                return this.mapper.readValue(source, type);
            } catch (Exception var4) {
                throw new SerializationException("Could not read JSON: " + var4.getMessage(), var4);
            }
        }
    }

    private class NullValueSerializer extends StdSerializer<NullValue> {
        private static final long serialVersionUID = 2199052150128658111L;
        private final String classIdentifier;

        NullValueSerializer(@Nullable String classIdentifier) {
            super(NullValue.class);
            this.classIdentifier = StringUtils.hasText(classIdentifier) ? classIdentifier : "@class";
        }

        @Override
        public void serialize(NullValue value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeStringField(this.classIdentifier, NullValue.class.getName());
            jgen.writeEndObject();
        }
    }
}