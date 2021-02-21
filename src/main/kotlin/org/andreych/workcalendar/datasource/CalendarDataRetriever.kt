package org.andreych.workcalendar.datasource

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.andreych.workcalendar.datasource.model.YearData
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

class CalendarDataRetriever(
    restTemplateBuilder: RestTemplateBuilder,
    private val objectMapper: ObjectMapper
) {

    companion object {
        private const val URL_TEMPLATE = "/data/ru/{year}/calendar.json"
        private val LOG = LoggerFactory.getLogger(CalendarDataRetriever::class.java)
    }

    private val restTemplate: RestTemplate = restTemplateBuilder
        .rootUri("http://xmlcalendar.ru")
        .build()

    suspend fun getData(): Pair<String?, YearData> {
        LOG.info("Fetching calendar data from government service.")
        val deferred = withContext(Dispatchers.IO) {
            return@withContext async {
                val yearParam = mapOf("year" to LocalDate.now().year)
                val response: String? = restTemplate.getForObject(URL_TEMPLATE, String::class.java, yearParam)
                val value = objectMapper.readValue(response, YearData::class.java)
                return@async Pair(response, value)
            }
        }
        val result = deferred.await()
        LOG.info("Calendar data has been fetched.")
        return result
    }
}