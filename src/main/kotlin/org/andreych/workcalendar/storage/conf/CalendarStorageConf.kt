package org.andreych.workcalendar.storage.conf

import org.andreych.workcalendar.storage.CalendarStorage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CalendarStorageConf {
    @Bean
    fun calendarStorage(): CalendarStorage = CalendarStorage()
}