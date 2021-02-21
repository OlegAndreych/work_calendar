package org.andreych.workcalendar.datasource.conf

import com.fasterxml.jackson.databind.ObjectMapper
import org.andreych.workcalendar.datasource.CalendarDataRetriever
import org.andreych.workcalendar.datasource.ConvertingCalendarDataRetriever
import org.andreych.workcalendar.datasource.api.CalendarDatasource
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CalendarDatasourceConf {
    @Bean
    fun calendarDataRetriever(
        restTemplateBuilder: RestTemplateBuilder,
        objectMapper: ObjectMapper
    ): CalendarDataRetriever = CalendarDataRetriever(restTemplateBuilder, objectMapper)

    @Bean
    fun calendarDataSource(dataRetriever: CalendarDataRetriever): CalendarDatasource =
        ConvertingCalendarDataRetriever(dataRetriever)
}