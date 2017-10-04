package org.andreych.russiancalendar.restservice

import org.andreych.russiancalendar.storage.CalendarStorage
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class RestController(private val calendarStorage: CalendarStorage) {

    companion object {
        private val headers = HttpHeaders()

        init {
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate")
            headers.add("Pragma", "no-cache")
            headers.add("Expires", "0")
            headers.add("Content-Encoding", "gzip")
            headers.add("Content-Disposition", "attachment; filename=\"calendar.ics\"")
        }
    }

    @GetMapping("/")
    fun workCalendar(): ResponseEntity<ByteArrayResource> {
        val bytes = calendarStorage.getBytes()

        return if (bytes != null) {
            ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("text/calendar"))
                    .body(ByteArrayResource(bytes.toByteArray()))
        } else {
            ResponseEntity
                    .notFound()
                    .build()
        }
    }
}