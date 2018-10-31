package org.andreych.workcalendar.service

import org.andreych.workcalendar.storage.CalendarStorage
import org.andreych.workcalendar.updater.CalendarDataUpdater

class CalendarService(
    private val calendarStorage: CalendarStorage,
    private val calendarDataUpdater: CalendarDataUpdater
) {

    fun getCalendarBytes(): Array<Byte>? {
        val bytes = calendarStorage.getBytes()
        if (bytes == null) {
            calendarDataUpdater.update()
            return calendarStorage.getBytes()
        }
        return bytes
    }
}