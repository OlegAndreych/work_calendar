package org.andreych.workcalendar.storage

import net.fortuna.ical4j.data.CalendarOutputter
import net.fortuna.ical4j.model.Calendar
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

/**
 * Хранит актуальные данные iCalendar.
 */
class CalendarStorage {

    @Volatile
    private var calendar: Array<Byte>? = null

    fun update(calendar: Calendar) {
        val calendarBytes = ByteArrayOutputStream().use { baos ->
            GZIPOutputStream(baos).use { gzos ->
                CalendarOutputter().output(calendar, gzos)
            }
            baos.toByteArray()
        }
        this.calendar = calendarBytes.toTypedArray()
    }

    fun getBytes(): Array<Byte>? {

        return this.calendar
    }
}