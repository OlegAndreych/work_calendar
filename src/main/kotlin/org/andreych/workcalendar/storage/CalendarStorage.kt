package org.andreych.workcalendar.storage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    suspend fun update(calendar: Calendar) {
        withContext(Dispatchers.Default) {
            val calendarBytes = ByteArrayOutputStream().use { baos ->
                GZIPOutputStream(baos).use { gzos ->
                    CalendarOutputter().output(calendar, gzos)
                }
                baos.toByteArray()
            }
            this@CalendarStorage.calendar = calendarBytes.toTypedArray()
        }
    }

    fun getBytes(): Array<Byte>? {
        return this.calendar
    }
}