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

    private val scope = CoroutineScope(Dispatchers.Default)

    @Scheduled(fixedRate = 15 * 60 * 1000, initialDelay = 0)
    fun update() {
        LOG.info("Launching scheduled update!")
        scope.launch {
            LOG.info("Updating calendar data.")
            val data = dataRetriever.getData()
            val calendar = calendarMapper.map(data.second)
            calendarStorage.update(Pair(data.first, calendar))
            LOG.info("Calendar data has been updated.")
        }
    }
}