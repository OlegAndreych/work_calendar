package org.andreych.workcalendar.restservice.conf

import org.andreych.workcalendar.restservice.RestController
import org.andreych.workcalendar.service.CalendarService
import org.andreych.workcalendar.service.conf.CalendarServiceConf
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(CalendarServiceConf::class)
class RestServiceConfiguration {
    @Bean
    fun restController(calendarService: CalendarService): RestController = RestController(calendarService)
}