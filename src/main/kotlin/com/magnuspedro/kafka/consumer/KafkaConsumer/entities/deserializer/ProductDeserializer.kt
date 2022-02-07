package com.magnuspedro.kafka.consumer.KafkaConsumer.entities.deserializer

import com.fasterxml.jackson.databind.ObjectMapper
import com.magnuspedro.kafka.consumer.KafkaConsumer.entities.Product
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory
import kotlin.text.Charsets.UTF_8


class ProductDeserializer : Deserializer<Product> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): Product? {
        log.info("Deserializing...")
        return objectMapper.readValue(
            String(
                data ?: throw SerializationException("Error when deserializing byte[] to Product"), UTF_8
            ), Product::class.java
        )
    }

    override fun close() {}

}