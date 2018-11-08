package org.andreych.workcalendar.service

import org.andreych.workcalendar.storage.CalendarStorage

class CalendarService(private val calendarStorage: CalendarStorage) {
    
    fun getCalendarBytes(): Array<Byte>? {
        return calendarStorage.getBytes()
    }
}