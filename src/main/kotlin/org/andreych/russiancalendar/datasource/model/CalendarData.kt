package org.andreych.russiancalendar.datasource.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonIgnoreProperties(ignoreUnknown = true)
data class YearData(@JsonProperty("Год/Месяц") val year: Int,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Январь") val jan: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Февраль") val feb: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Март") val mar: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Апрель") val apr: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Май") val may: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Июнь") val jun: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Июль") val jul: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Август") val aug: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Сентябрь") val sep: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Октябрь") val oct: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Ноябрь") val nov: List<Day>,
                    @JsonDeserialize(converter = CommaSeparatedListConverter::class) @JsonProperty("Декабрь") val dec: List<Day>
)

data class Day(val date: Int, val holidayType: HolidayType = HolidayType.DAY_OFF)

enum class HolidayType(val description: String) {
    SHORT("Short day"),
    DAY_OFF("Day off");
}
