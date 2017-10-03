package org.andreych.russiancalendar.datasource.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

internal class CalendarDataMappingTest {

    /**
     * Тестовый json корректно преобразуется в объектное представление.
     */
    @Test
    fun shouldMap() {
        val mapper = jacksonObjectMapper()
        val file = javaClass.classLoader.getResourceAsStream("response_example.json")
        val readValue = mapper.readValue(file, Array<YearData>::class.java).asList()
        assertEquals(21, readValue.size)
        for (y in 1999..2019) {
            assertNotNull(readValue.find { it.year == y })
        }
    }
}