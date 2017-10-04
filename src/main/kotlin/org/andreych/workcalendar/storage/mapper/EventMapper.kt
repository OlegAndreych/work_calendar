package org.andreych.workcalendar.storage.mapper

import net.fortuna.ical4j.model.Date
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.util.UidGenerator
import org.andreych.workcalendar.datasource.model.Day
import org.andreych.workcalendar.datasource.model.HolidayType
import org.springframework.beans.factory.ObjectFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class EventMapper(private val uidGeneratorFactory: ObjectFactory<UidGenerator>) {

    fun map(year: Int, month: Int, date: Day): VEvent {
        val localDate = GregorianCalendar(year, month, date.date)
        return map(localDate, date.holidayType)
    }

    private fun map(date: Calendar, holidayType: HolidayType): VEvent {
        val uidGenerator = uidGeneratorFactory.`object`
        val event = VEvent(Date(date.time), holidayType.description)
        event.properties.add(uidGenerator.generateUid())
        return event
    }
}