package org.andreych.workcalendar.storage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.fortuna.ical4j.data.CalendarOutputter
import net.fortuna.ical4j.model.Calendar
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.io.ByteArrayOutputStream
import java.sql.ResultSet
import java.util.zip.GZIPOutputStream

/**
 * Хранит актуальные данные iCalendar.
 */
class CalendarStorage(private val jdbcTemplate: JdbcTemplate) {

    companion object {
        //@formatter:off
        private const val INSERT_NEWS =
                "INSERT INTO calendar (original_json, compressed_ical) " +
                "VALUES (?,?) " +
                "ON CONFLICT DO NOTHING"
        //@formatter:on

        private const val SELECT_NEWS =
                "SELECT compressed_ical FROM calendar ORDER BY id DESC LIMIT 1"
    }

    suspend fun update(data: Pair<String?, Calendar>) {
        val bytes = withContext(Dispatchers.Default) {
            val calendarBytes = ByteArrayOutputStream().use { baos ->
                GZIPOutputStream(baos).use { gzos ->
                    CalendarOutputter().output(data.second, gzos)
                }
                baos.toByteArray()
            }
            calendarBytes
        }

        withContext(Dispatchers.IO) {
            jdbcTemplate.update { con ->
                val ps = con.prepareStatement(INSERT_NEWS)
                ps.setString(1, data.first)
                ps.setBytes(2, bytes)
                ps
            }
        }
    }

    suspend fun getBytes(): ByteArray? = withContext(Dispatchers.IO) {
        return@withContext jdbcTemplate.query(SELECT_NEWS, RowMapper { rs: ResultSet, _: Int ->
            return@RowMapper rs.getBytes(1)
        })[0]
    }
}