package org.andreych.workcalendar.service

import org.andreych.workcalendar.storage.CalendarStorage

class CalendarService(private val calendarStorage: CalendarStorage) {

    suspend fun getCalendarBytes(): ByteArray? {
        return calendarStorage.getBytes()
    }
}