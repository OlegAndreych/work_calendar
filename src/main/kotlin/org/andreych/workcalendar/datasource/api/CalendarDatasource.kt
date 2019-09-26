package org.andreych.workcalendar.datasource.api

import org.andreych.workcalendar.domain.WorkCalendarYear

/**
 * Источник данных для производственного календаря.
 */
interface CalendarDatasource {
    /**
     * Получение данных для производственного календаря.
     */
    suspend fun getData(): Pair<String?, List<WorkCalendarYear>>
}