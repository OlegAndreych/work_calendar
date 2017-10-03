package org.andreych.russiancalendar.datasource.model

import com.fasterxml.jackson.databind.util.StdConverter

/**
 * Конвертирует строковые значения с портала открытых данных во внутреннее представление для
 * праздничного или предпраздничного укороченного дня.
 */
class CommaSeparatedListConverter private constructor() : StdConverter<String, List<Day>>() {

    companion object {
        val INSTANCE = CommaSeparatedListConverter()
    }

    override fun convert(value: String?): List<Day> {
        if (value == null) return emptyList()
        return value
                .split(',')
                .filter { it.isNotBlank() }
                .map { it.trim() }
                .map { toDate(it) }
    }

    private fun toDate(token: String): Day {
        return if (token.endsWith('*')) {
            val date = token.removeSuffix("*").toInt()
            Day(date, HolidayType.SHORT)
        } else {
            Day(token.toInt(), HolidayType.HOLIDAY)
        }
    }
}