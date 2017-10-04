package org.andreych.workcalendar.utils

import org.andreych.workcalendar.datasource.model.CommaSeparatedListConverter
import org.andreych.workcalendar.datasource.model.Day
import org.andreych.workcalendar.datasource.model.HolidayType
import org.andreych.workcalendar.datasource.model.HolidayType.DAY_OFF
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CommaSeparatedListConverterTest {

    private val converter: CommaSeparatedListConverter = CommaSeparatedListConverter.INSTANCE

    /**
     * В отсутвтие строки возвращается пустой список.
     */
    @Test
    fun shouldReturnEmptyListOnNull() {
        assertEquals(emptyList<String>(), converter.convert(null))
    }

    /**
     * Строка корректно разделяется и преобразуется в список.
     */
    @Test
    fun shouldSplitString() {
        assertEquals(listOf(Day(1, DAY_OFF), Day(2, HolidayType.SHORT)),
                converter.convert("1,2*"))
    }

    /**
     * Строка с единственным элементом обрабатывается корректно.
     */
    @Test
    fun shouldProcessSingleElementString() {
        assertEquals(listOf(Day(1)), converter.convert("1"))
    }

    /**
     * Пустая строка обрабатывается корректно.
     */
    @Test
    fun shouldProcessEmptyString() {
        assertEquals(emptyList<String>(), converter.convert(""))
    }

    /**
     * Пробельные символы обрабатываются корректно.
     */
    @Test
    fun shouldProcessWhitespaceString() {
        assertEquals(listOf(Day(1), Day(2)), converter.convert(",1, ,,2,\t,"))
    }
}