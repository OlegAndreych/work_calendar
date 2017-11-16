package org.andreych.workcalendar.utils

import org.andreych.workcalendar.datasource.model.DaysString
import org.andreych.workcalendar.datasource.utils.convert
import org.andreych.workcalendar.domain.Day
import org.andreych.workcalendar.domain.HolidayType
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CommaSeparatedListConverterTest {

    /**
     * В отсутвтие строки возвращается пустой список.
     */
    @Test
    fun shouldReturnEmptyListOnNull() {
        assertEquals(emptyList<String>(), (null as DaysString?).convert())
    }

    /**
     * Строка корректно разделяется и преобразуется в список.
     */
    @Test
    fun shouldSplitString() {
        assertEquals(listOf(Day(1), Day(2, HolidayType.SHORT)),
                "1,2*".convert())
    }

    /**
     * Строка с единственным элементом обрабатывается корректно.
     */
    @Test
    fun shouldProcessSingleElementString() {
        assertEquals(listOf(Day(1)), "1".convert())
    }

    /**
     * Пустая строка обрабатывается корректно.
     */
    @Test
    fun shouldProcessEmptyString() {
        assertEquals(emptyList<String>(), "".convert())
    }

    /**
     * Пробельные символы обрабатываются корректно.
     */
    @Test
    fun shouldProcessWhitespaceString() {
        assertEquals(listOf(Day(1), Day(2)), ",1, ,,2,\t,".convert())
    }
}