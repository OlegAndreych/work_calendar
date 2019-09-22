package org.andreych.workcalendar.restservice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.andreych.workcalendar.service.CalendarService
import org.springframework.context.annotation.DependsOn
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult


@RestController
@DependsOn("calendarDataUpdater")
class RestController(private val calendarService: CalendarService) {

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
    fun workCalendar(): DeferredResult<ResponseEntity<ByteArrayResource>> {
        val deferredResult = DeferredResult<ResponseEntity<ByteArrayResource>>()
        CoroutineScope(Dispatchers.Default).launch {
            val bytes: Array<Byte>? = calendarService.getCalendarBytes()

            val responseEntity = if (bytes != null) {
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

            deferredResult.setResult(responseEntity)
        }
        return deferredResult
    }
}