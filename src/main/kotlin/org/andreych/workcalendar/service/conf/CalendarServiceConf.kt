package org.andreych.workcalendar.service.conf

import org.andreych.workcalendar.service.CalendarService
import org.andreych.workcalendar.storage.CalendarStorage
import org.andreych.workcalendar.storage.conf.CalendarStorageConf
import org.andreych.workcalendar.updater.CalendarDataUpdater
import org.andreych.workcalendar.updater.conf.CalendarDataUpdaterConf
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(CalendarStorageConf::class, CalendarDataUpdaterConf::class)
class CalendarServiceConf {
    @Bean
    fun calendarService(
            calendarStorage: CalendarStorage,
            calendarDataUpdater: CalendarDataUpdater
    ): CalendarService = CalendarService(calendarStorage)
}