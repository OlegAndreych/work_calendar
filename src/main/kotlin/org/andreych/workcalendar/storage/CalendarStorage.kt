package org.andreych.workcalendar.storage

import net.fortuna.ical4j.data.CalendarOutputter
import net.fortuna.ical4j.model.Calendar
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

/**
 * Хранит актуальные данные iCalendar.
 */
@Service
class CalendarStorage {

    @Volatile
    private var calendar: Array<Byte>? = null

    fun update(calendar: Calendar) {
        val calendarBytes = ByteArrayOutputStream().use {
            GZIPOutputStream(it).use {
                CalendarOutputter().output(calendar, it)
            }
            it.toByteArray()
        }
        this.calendar = calendarBytes.toTypedArray()
    }

    fun getBytes(): Array<Byte>? {

        return this.calendar
    }
}