package org.andreych.workcalendar.service

import org.andreych.workcalendar.storage.CalendarStorage
import org.andreych.workcalendar.updater.CalendarDataUpdater
import org.springframework.stereotype.Service

@Service
class CalendarService(private val calendarStorage: CalendarStorage, private val calendarDataUpdater: CalendarDataUpdater) {

    fun getCalendarBytes() {

    }
}