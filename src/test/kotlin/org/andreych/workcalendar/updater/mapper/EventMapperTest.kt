package org.andreych.workcalendar.updater.mapper

import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import net.fortuna.ical4j.util.RandomUidGenerator
import net.fortuna.ical4j.util.UidGenerator
import org.andreych.workcalendar.domain.Day
import org.andreych.workcalendar.domain.HolidayType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.ObjectProvider
import java.util.*
import java.util.Calendar.JANUARY

class EventMapperTest {

    @Test
    fun shouldMapDayOff() {
        val holidayType = HolidayType.DAY_OFF
        shouldMapEvent(holidayType)
    }

    @Test
    fun shouldMapShort() {
        val holidayType = HolidayType.SHORT
        shouldMapEvent(holidayType)
    }

    private fun shouldMapEvent(holidayType: HolidayType) {
        val generatorProvider: ObjectProvider<UidGenerator> = mock {
            on { `object` } doAnswer { RandomUidGenerator() }
        }

        val eventMapper = EventMapper(generatorProvider)
        val day = Day(1, holidayType)
        val vEvent = eventMapper.map(2010, JANUARY, day)

        val expectedDate = GregorianCalendar(2010, JANUARY, 1).timeInMillis
        assertEquals("Unexpected event date.", expectedDate, vEvent.startDate.date.time)
        assertEquals("Unexpected summary.", holidayType.description, vEvent.summary.value)
    }
}