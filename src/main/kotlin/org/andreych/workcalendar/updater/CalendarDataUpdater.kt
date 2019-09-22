package org.andreych.workcalendar.updater

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.andreych.workcalendar.datasource.api.CalendarDatasource
import org.andreych.workcalendar.storage.CalendarStorage
import org.andreych.workcalendar.updater.mapper.CalendarMapper
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled

/**
 * Сервис обновления данных производственного календаря.
 */
class CalendarDataUpdater(
        private val dataRetriever: CalendarDatasource,
        private val calendarStorage: CalendarStorage,
        private val calendarMapper: CalendarMapper
) {

    companion object {
        val LOG = LoggerFactory.getLogger(CalendarDataUpdater::class.java)!!
    }

    @Scheduled(fixedRate = 15 * 60 * 1000)
    fun update() {
        LOG.info("Launching scheduled update!")
        CoroutineScope(Dispatchers.Default).launch {
            LOG.info("Updating calendar data.")
            val data = dataRetriever.getData()
            val calendar = calendarMapper.map(data)
            calendarStorage.update(calendar)
            LOG.info("Calendar data has been updated.")
        }
    }
}