package org.andreych.russiancalendar.updater

import org.andreych.russiancalendar.datasource.CalendarDatasource
import org.andreych.russiancalendar.storage.CalendarStorage
import org.andreych.russiancalendar.storage.mapper.CalendarMapper
import org.apache.commons.lang3.time.DateUtils
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * Сервис обновления данных производственного календаря.
 */
@Service
class CalendarDataUpdater(private val datasource: CalendarDatasource,
                          private val calendarStorage: CalendarStorage,
                          private val calendarMapper: CalendarMapper) {

    companion object {
        val LOG = LoggerFactory.getLogger(CalendarDataUpdater::class.java)
    }

    @PostConstruct
    fun init() {
        update()
    }

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_DAY)
    fun update() {
        LOG.info("Updating calendar data.")
        val data = datasource.getData()
        val calendar = calendarMapper.map(data)
        calendarStorage.update(calendar)
        LOG.info("Calendar data has been updated.")
    }
}