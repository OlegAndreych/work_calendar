package org.andreych.russiancalendar.storage.mapper

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import net.fortuna.ical4j.model.Date
import net.fortuna.ical4j.model.component.VEvent
import org.andreych.russiancalendar.datasource.model.Day
import org.andreych.russiancalendar.datasource.model.HolidayType.DAY_OFF
import org.andreych.russiancalendar.datasource.model.YearData
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CalendarMapperTest {

    @Test
    fun shouldCreateCalendar() {
        val eventMapper: EventMapper = mock {
            on { map(any(), any(), any()) } doReturn VEvent(Date(100500), "lol")
        }

        val yearData = YearData(2017,
                listOf(Day(1, DAY_OFF)),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList())

        val data = listOf(yearData)

        val mapper = CalendarMapper(eventMapper)
        val calendar = mapper.map(data)

        assertEquals("Unexpected amount of events", 1, calendar.components.size)
    }
}