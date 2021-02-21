package org.andreych.workcalendar.updater.mapper

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import net.fortuna.ical4j.model.Date
import net.fortuna.ical4j.model.component.VEvent
import org.andreych.workcalendar.domain.Day
import org.andreych.workcalendar.domain.EnumListMultivaluedMap
import org.andreych.workcalendar.domain.WorkCalendarYear
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Month

internal class CalendarMapperTest {

    @Test
    fun shouldCreateCalendar() {
        val eventMapper: EventMapper = mock {
            on { map(any(), any(), any()) } doReturn VEvent(Date(100500), "lol")
        }

        val months = EnumListMultivaluedMap.newInstance<Month, Day>()
        months.putAll(Month.JANUARY, listOf(Day(1)))

        val yearData = WorkCalendarYear(2100, months)

        val mapper = CalendarMapper(eventMapper)
        val calendar = mapper.map(yearData)

        assertEquals("Unexpected amount of events", 1, calendar.components.size)
    }
}