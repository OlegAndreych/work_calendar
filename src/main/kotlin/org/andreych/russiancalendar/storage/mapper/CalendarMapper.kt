package org.andreych.russiancalendar.storage.mapper

import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.ComponentList
import net.fortuna.ical4j.model.component.CalendarComponent
import net.fortuna.ical4j.model.property.CalScale
import net.fortuna.ical4j.model.property.ProdId
import net.fortuna.ical4j.model.property.Version
import org.andreych.russiancalendar.datasource.model.Day
import org.andreych.russiancalendar.datasource.model.YearData
import org.springframework.stereotype.Service

/**
 * Преобразует данные о производственном календаре из портала открытых данных в iCalendar.
 */
@Service
class CalendarMapper(private val eventMapper: EventMapper) {

    fun map(calendarData: List<YearData>): Calendar {
        val calendar = Calendar()

        val properties = calendar.properties
        properties.add(ProdId("-//Oleg Andreych//Russian Calendar 1.0//RU"))
        properties.add(Version.VERSION_2_0)
        properties.add(CalScale.GREGORIAN)

        val components = calendar.components

        for (datum in calendarData) {
            val year = datum.year

            datum.jan.addToCalendar(year, java.util.Calendar.JANUARY, components)
            datum.feb.addToCalendar(year, java.util.Calendar.FEBRUARY, components)
            datum.mar.addToCalendar(year, java.util.Calendar.MARCH, components)
            datum.apr.addToCalendar(year, java.util.Calendar.APRIL, components)
            datum.may.addToCalendar(year, java.util.Calendar.MAY, components)
            datum.jun.addToCalendar(year, java.util.Calendar.JUNE, components)
            datum.jul.addToCalendar(year, java.util.Calendar.JULY, components)
            datum.aug.addToCalendar(year, java.util.Calendar.AUGUST, components)
            datum.sep.addToCalendar(year, java.util.Calendar.SEPTEMBER, components)
            datum.oct.addToCalendar(year, java.util.Calendar.OCTOBER, components)
            datum.nov.addToCalendar(year, java.util.Calendar.NOVEMBER, components)
            datum.dec.addToCalendar(year, java.util.Calendar.DECEMBER, components)
        }

        return calendar
    }

    private fun List<Day>.addToCalendar(year: Int, month: Int, components: ComponentList<CalendarComponent>) {
        this
                .map { eventMapper.map(year, month, it) }
                .forEach { components.add(it) }
    }
}