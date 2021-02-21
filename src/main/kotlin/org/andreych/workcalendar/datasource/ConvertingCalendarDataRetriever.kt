package org.andreych.workcalendar.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.andreych.workcalendar.datasource.api.CalendarDatasource
import org.andreych.workcalendar.datasource.model.YearData
import org.andreych.workcalendar.datasource.utils.convert
import org.andreych.workcalendar.domain.Day
import org.andreych.workcalendar.domain.EnumListMultivaluedMap
import org.andreych.workcalendar.domain.WorkCalendarYear
import java.time.Month

class ConvertingCalendarDataRetriever(private val dataRetriever: CalendarDataRetriever) : CalendarDatasource {

    override suspend fun getData(): Pair<String?, WorkCalendarYear> {
        return withContext(Dispatchers.Default) {
            val data = dataRetriever.getData()
            val mappedCalendar = convert(data.second)
            Pair(data.first, mappedCalendar)
        }
    }

    private fun convert(yearData: YearData): WorkCalendarYear {
        val months: EnumListMultivaluedMap<Month, Day> = EnumListMultivaluedMap.newInstance()

        yearData.months.forEach { month ->
            months.putAll(Month.of(month.month), month.days.convert())
        }

        return WorkCalendarYear(yearData.year, months)
    }
}