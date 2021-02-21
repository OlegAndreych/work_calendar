package org.andreych.workcalendar.datasource.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CalendarDataMappingTest {

    /**
     * Тестовый json корректно преобразуется в объектное представление.
     */
    @Test
    fun shouldMap() {
        val mapper = jacksonObjectMapper()
        val file = javaClass.classLoader.getResourceAsStream("response_example.json")
        val readValue = mapper.readValue(file, YearData::class.java)

        assertEquals("Unexpected year value has been parsed.", 2021, readValue.year)
        val months = readValue.months
        assertEquals("Unexpected number of months has been parsed.", 12, months.size)

        (0..11).forEach { assertEquals("Unexpected month number value has been parsed.", it + 1, months[it].month) }

        assertEquals("1,2,3,4,5,6,7,8,9,10,16,17,23,24,30,31", months[0].days)
        assertEquals("6,7,13,14,20*,21,22,23,27,28", months[1].days)
        assertEquals("6,7,8,13,14,20,21,27,28", months[2].days)
        assertEquals("3,4,10,11,17,18,24,25,30*", months[3].days)
        assertEquals("1,2,3,8,9,10,15,16,22,23,29,30", months[4].days)
        assertEquals("5,6,11*,12,13,14,19,20,26,27", months[5].days)
        assertEquals("3,4,10,11,17,18,24,25,31", months[6].days)
        assertEquals("1,7,8,14,15,21,22,28,29", months[7].days)
        assertEquals("4,5,11,12,18,19,25,26", months[8].days)
        assertEquals("2,3,9,10,16,17,23,24,30,31", months[9].days)
        assertEquals("3*,4,5,6,7,13,14,20,21,27,28", months[10].days)
        assertEquals("4,5,11,12,18,19,25,26,31", months[11].days)
    }
}