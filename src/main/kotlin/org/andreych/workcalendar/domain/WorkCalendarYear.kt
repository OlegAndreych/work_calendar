package org.andreych.workcalendar.domain

import java.time.Month

/**
 * Представляет годовые данные производственного календаря.
 */
data class WorkCalendarYear(val year: Int, val months: EnumListMultivaluedMap<Month, Day>)

/**
 * Представляет данные производственного календаря о дне.
 */
data class Day(val date: Int, val holidayType: HolidayType = HolidayType.DAY_OFF)

/**
 * Типы дней производственного каленаря.
 */
enum class HolidayType(val description: String) {
    SHORT("Short day"),
    DAY_OFF("Day off");
}