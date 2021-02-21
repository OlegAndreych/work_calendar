package org.andreych.workcalendar.datasource.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class YearData(
    val year: Int,
    val months: List<MonthData>
)
