package org.andreych.workcalendar.datasource.utils

import org.andreych.workcalendar.datasource.model.DaysString
import org.andreych.workcalendar.domain.Day
import org.andreych.workcalendar.domain.HolidayType

/**
 * Конвертирует строковые значения с портала открытых данных во внутреннее представление для
 * праздничного или предпраздничного укороченного дня.
 */

fun DaysString?.convert(): List<Day> {
    if (this == null) return emptyList()
    return this
            .split(',')
            .filter { it.isNotBlank() }
            .map { it.trim() }
            .map { toDate(it) }
}

private fun toDate(token: DaysString): Day {
    return if (token.endsWith('*')) {
        val date = token.removeSuffix("*").toInt()
        Day(date, HolidayType.SHORT)
    } else {
        Day(token.trim('+').toInt(), HolidayType.DAY_OFF)
    }
}
