package com.springboot.kafka.configure;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

/**
 * @name: KafkaConfig
 * @desc: 消息组件
 * @author: gxing
 * @date: 2018-05-08 14:47
 **/
@Configuration
@EnableKafkaStreams
public class KafkaConfig {

    @Bean
    public NewTopic newTopic() {
        return new NewTopic("NBA", 1, (short) 1);
    }

    @Bean
    public KStream<Integer, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<Integer, String> stream = streamsBuilder.stream("ks1In");
        stream.map((k, v) -> new KeyValue<>(k, v.toUpperCase())).to("ks1Out",
                Produced.with(Serdes.Integer(), new JsonSerde<>()));
        return stream;
    }

}