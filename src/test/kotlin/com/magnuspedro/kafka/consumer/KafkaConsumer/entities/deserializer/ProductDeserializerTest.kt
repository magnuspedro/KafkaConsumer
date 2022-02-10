package com.magnuspedro.kafka.consumer.KafkaConsumer.entities.deserializer

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import com.magnuspedro.kafka.consumer.KafkaConsumer.entities.Product
import org.apache.kafka.common.errors.SerializationException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals


class ProductDeserializerTest {

    private val mapper: ObjectMapper = ObjectMapper()
    private lateinit var productDeserializer: ProductDeserializer

    @BeforeEach
    fun setup() {
        productDeserializer = ProductDeserializer()
    }

    @Test
    fun `Should test with a valid product`() {
        val product = Product(name = "Churrasqueira controle remoto", sku = "542662543")

        val deserializaedProduct: Product? = productDeserializer.deserialize(
            "product",
            mapper.writeValueAsBytes(product)
        )

        assertEquals(product, deserializaedProduct)
    }

    @Test
    fun `Should test with a not valid product`() {
        val byteArray: ByteArray = "Teste".toByteArray()

        assertThrows<JsonParseException> {
            productDeserializer.deserialize(
                "product",
                byteArray
            )
        }
    }

    @Test
    fun `Should test with a null product`() {
        var exception = assertThrows<SerializationException> {
            productDeserializer.deserialize(
                "product",
                null
            )
        }

        assertEquals(exception.message, "Error when deserializing byte[] to Product")
    }
}