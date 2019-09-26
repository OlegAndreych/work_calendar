package org.andreych.workcalendar.datasource

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.andreych.workcalendar.datasource.model.YearData
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@ConfigurationProperties("open.data")
class RetrieverConfiguration {
    lateinit var accessToken: String
}

class CalendarDataRetriever(
        restTemplateBuilder: RestTemplateBuilder,
        config: RetrieverConfiguration,
        private val objectMapper: ObjectMapper) {

    companion object {
        private const val URL_TEMPLATE =
                "/dataset/7708660670-proizvcalendar/version/20151123T183036/content/?access_token={access_token}"
        private val LOG = LoggerFactory.getLogger(CalendarDataRetriever::class.java)
    }

    private val accessToken = mapOf("access_token" to config.accessToken)

    private val restTemplate: RestTemplate = restTemplateBuilder
            .rootUri("http://data.gov.ru/api")
            .build()

    suspend fun getData(): Pair<String?, List<YearData>> {
        val deferred = withContext(Dispatchers.IO) {
            return@withContext async {
                LOG.info("Fetching calendar data from government service.")
                val response: String? = restTemplate.getForObject(URL_TEMPLATE, accessToken)
                val parsedResponse: Array<YearData>? = objectMapper.readValue(response, Array<YearData>::class.java)
                return@async Pair(response, parsedResponse?.asList() ?: emptyList())
            }
        }
        val result = deferred.await()
        LOG.info("Calendar data has been fetched.")
        return result
    }
}