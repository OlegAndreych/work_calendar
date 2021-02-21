package org.andreych.workcalendar.updater.mapper

import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.ComponentList
import net.fortuna.ical4j.model.component.CalendarComponent
import net.fortuna.ical4j.model.property.CalScale
import net.fortuna.ical4j.model.property.ProdId
import net.fortuna.ical4j.model.property.Version
import org.andreych.workcalendar.domain.Day
import org.andreych.workcalendar.domain.WorkCalendarYear

/**
 * Преобразует данные о производственном календаре из портала открытых данных в iCalendar.
 */
class CalendarMapper(private val eventMapper: EventMapper) {

    /**
     * Преобразует данные о производственном календаре из портала открытых данных в iCalendar.
     *
     * @param calendarData данные календаря с портала.
     */
    fun map(calendarData: WorkCalendarYear): Calendar {
        val calendar = Calendar()

        val properties = calendar.properties
        properties.add(ProdId("-//Oleg Andreych//Russian Calendar 1.0//RU"))
        properties.add(Version.VERSION_2_0)
        properties.add(CalScale.GREGORIAN)

        val components = calendar.components

        calendarData.months.entries()
            .forEach { (k, v) -> addToCalendar(calendarData.year, k.ordinal, v, components) }


        return calendar
    }

    private fun addToCalendar(year: Int, month: Int, day: Day, components: ComponentList<CalendarComponent>) {
        val event = eventMapper.map(year, month, day)
        components.add(event)
    }
}