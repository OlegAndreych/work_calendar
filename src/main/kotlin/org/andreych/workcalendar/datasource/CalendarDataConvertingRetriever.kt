package org.andreych.workcalendar.datasource

import org.andreych.workcalendar.datasource.api.CalendarDatasource
import org.andreych.workcalendar.datasource.model.DaysString
import org.andreych.workcalendar.datasource.model.YearData
import org.andreych.workcalendar.datasource.utils.convert
import org.andreych.workcalendar.domain.Day
import org.andreych.workcalendar.domain.EnumListMultivaluedMap
import org.andreych.workcalendar.domain.WorkCalendarYear
import org.springframework.stereotype.Service
import java.time.Month
import java.time.Month.*
import kotlin.reflect.KFunction

private typealias DaysStringProducer = KFunction<DaysString>

@Service
class CalendarDataConvertingRetriever(private val dataRetriever: CalendarDataRetriever) : CalendarDatasource {

    override fun getData(): List<WorkCalendarYear> {
        val yearDataList = dataRetriever.getData()
        return yearDataList.map { convert(it) }
    }

    private fun convert(yearData: YearData): WorkCalendarYear {
        val months: EnumListMultivaluedMap<Month, Day> = EnumListMultivaluedMap.newInstance()

        months.putAll(JANUARY, yearData.jan.convert())
        months.putAll(FEBRUARY, yearData.feb.convert())
        months.putAll(MARCH, yearData.mar.convert())
        months.putAll(APRIL, yearData.apr.convert())
        months.putAll(MAY, yearData.may.convert())
        months.putAll(JUNE, yearData.jun.convert())
        months.putAll(JULY, yearData.jul.convert())
        months.putAll(AUGUST, yearData.aug.convert())
        months.putAll(SEPTEMBER, yearData.sep.convert())
        months.putAll(OCTOBER, yearData.oct.convert())
        months.putAll(NOVEMBER, yearData.nov.convert())
        months.putAll(DECEMBER, yearData.dec.convert())

        return WorkCalendarYear(yearData.year, months)
    }
}