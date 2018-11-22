package org.andreych.workcalendar.service.conf

import org.andreych.workcalendar.service.CalendarService
import org.andreych.workcalendar.storage.CalendarStorage
import org.andreych.workcalendar.storage.conf.CalendarStorageConf
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(CalendarStorageConf::class)
open class CalendarServiceConf {
    @Bean
    open fun calendarService(
            calendarStorage: CalendarStorage
    ): CalendarService = CalendarService(calendarStorage)
}