package com.magnuspedro.kafka.consumer.KafkaConsumer.infra.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.springframework.kafka.support.Acknowledgment


class ConsumerTest {

    private lateinit var consumer: Consumer
    private val ack: Acknowledgment = mock();

    @BeforeEach
    fun setup() {
        consumer = Consumer()
    }

    @Test
    fun `Should test kafka listener`() {
        assertDoesNotThrow {
            consumer.listenGroupFoo(
                ConsumerRecord("product", 1, 12L, "key", "value"), ack
            )
        }

        verify(ack, atLeastOnce()).acknowledge()
    }
}