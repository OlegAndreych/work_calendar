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
import java.util.Calendar.*

/**
 * Преобразует данные о производственном календаре из портала открытых данных в iCalendar.
 */
@Service
class CalendarMapper(private val eventMapper: EventMapper) {

    /**
     * Преобразует данные о производственном календаре из портала открытых данных в iCalendar.
     *
     * @param calendarData данные календаря с портала.
     */
    fun map(calendarData: List<YearData>): Calendar {
        val calendar = Calendar()

        val properties = calendar.properties
        properties.add(ProdId("-//Oleg Andreych//Russian Calendar 1.0//RU"))
        properties.add(Version.VERSION_2_0)
        properties.add(CalScale.GREGORIAN)

        val components = calendar.components

        for (datum in calendarData) {
            val year = datum.year

            datum.jan.addToCalendar(year, JANUARY, components)
            datum.feb.addToCalendar(year, FEBRUARY, components)
            datum.mar.addToCalendar(year, MARCH, components)
            datum.apr.addToCalendar(year, APRIL, components)
            datum.may.addToCalendar(year, MAY, components)
            datum.jun.addToCalendar(year, JUNE, components)
            datum.jul.addToCalendar(year, JULY, components)
            datum.aug.addToCalendar(year, AUGUST, components)
            datum.sep.addToCalendar(year, SEPTEMBER, components)
            datum.oct.addToCalendar(year, OCTOBER, components)
            datum.nov.addToCalendar(year, NOVEMBER, components)
            datum.dec.addToCalendar(year, DECEMBER, components)
        }

        return calendar
    }

    private fun List<Day>.addToCalendar(year: Int, month: Int, components: ComponentList<CalendarComponent>) {
        this
                .map { eventMapper.map(year, month, it) }
                .forEach { components.add(it) }
    }
}