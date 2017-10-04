package org.andreych.workcalendar.storage.mapper

import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import net.fortuna.ical4j.util.UidGenerator
import org.andreych.workcalendar.datasource.model.Day
import org.andreych.workcalendar.datasource.model.HolidayType
import org.andreych.workcalendar.datasource.model.HolidayType.DAY_OFF
import org.andreych.workcalendar.utils.PidProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.ObjectProvider
import java.util.*
import java.util.Calendar.JANUARY

class EventMapperTest {

    @Test
    fun shouldMapDayOff() {
        val holidayType = DAY_OFF
        shouldMapEvent(holidayType)
    }

    @Test
    fun shouldMapShort() {
        val holidayType = HolidayType.SHORT
        shouldMapEvent(holidayType)
    }

    private fun shouldMapEvent(holidayType: HolidayType) {
        val generatorProvider: ObjectProvider<UidGenerator> = mock {
            on { `object` } doAnswer { UidGenerator(PidProvider.pid) }
        }

        val eventMapper = EventMapper(generatorProvider)
        val day = Day(1, holidayType)
        val vEvent = eventMapper.map(2010, JANUARY, day)

        val expectedDate = GregorianCalendar(2010, JANUARY, 1).timeInMillis
        assertEquals("Unexpected event date.", expectedDate, vEvent.startDate.date.time)
        assertEquals("Unexpected summary.", holidayType.description, vEvent.summary.value)
    }
}