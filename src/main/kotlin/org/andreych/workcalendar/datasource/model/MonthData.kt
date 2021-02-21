package org.andreych.workcalendar.datasource.model

typealias DaysString = String

data class MonthData(
    val month: Int,
    val days: DaysString
)
