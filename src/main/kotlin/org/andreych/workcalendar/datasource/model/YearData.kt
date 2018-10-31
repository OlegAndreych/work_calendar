package org.andreych.workcalendar.datasource.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

typealias DaysString = String

@JsonIgnoreProperties(ignoreUnknown = true)
data class YearData(
    @JsonProperty("Год/Месяц") val year: Int,
    @JsonProperty("Январь") val jan: DaysString,
    @JsonProperty("Февраль") val feb: DaysString,
    @JsonProperty("Март") val mar: DaysString,
    @JsonProperty("Апрель") val apr: DaysString,
    @JsonProperty("Май") val may: DaysString,
    @JsonProperty("Июнь") val jun: DaysString,
    @JsonProperty("Июль") val jul: DaysString,
    @JsonProperty("Август") val aug: DaysString,
    @JsonProperty("Сентябрь") val sep: DaysString,
    @JsonProperty("Октябрь") val oct: DaysString,
    @JsonProperty("Ноябрь") val nov: DaysString,
    @JsonProperty("Декабрь") val dec: DaysString
)
