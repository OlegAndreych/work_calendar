package org.andreych.workcalendar.updater.conf

import net.fortuna.ical4j.util.RandomUidGenerator
import net.fortuna.ical4j.util.UidGenerator
import org.andreych.workcalendar.datasource.api.CalendarDatasource
import org.andreych.workcalendar.storage.CalendarStorage
import org.andreych.workcalendar.updater.CalendarDataUpdater
import org.andreych.workcalendar.updater.mapper.CalendarMapper
import org.andreych.workcalendar.updater.mapper.EventMapper
import org.andreych.workcalendar.utils.PidProvider
import org.springframework.beans.factory.ObjectFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
//@Import(CalendarDatasourceConf::class, CalendarStorageConf::class)
class CalendarDataUpdaterConf {
    @Bean
    fun calendarDataUpdater(dataRetriever: CalendarDatasource, calendarStorage: CalendarStorage, calendarMapper: CalendarMapper): CalendarDataUpdater = CalendarDataUpdater(dataRetriever,
            calendarStorage, calendarMapper)

    @Bean
    fun uidGenerator(): UidGenerator {
        return RandomUidGenerator()
    }

    @Bean
    fun calendarMapper(eventMapper: EventMapper): CalendarMapper = CalendarMapper(eventMapper)

    @Bean
    fun eventMapper(uidGeneratorFactory: ObjectFactory<UidGenerator>): EventMapper = EventMapper(uidGeneratorFactory)
}