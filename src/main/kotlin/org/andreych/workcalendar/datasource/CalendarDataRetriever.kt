package org.andreych.workcalendar.datasource

import org.andreych.workcalendar.datasource.model.YearData
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@ConfigurationProperties("open.data")
class RetrieverConfiguration {
    lateinit var accessToken: String
}

class CalendarDataRetriever(restTemplateBuilder: RestTemplateBuilder, config: RetrieverConfiguration) {

    companion object {
        private const val URL_TEMPLATE = "/dataset/7708660670-proizvcalendar/version/20151123T183036/content/?access_token={access_token}"
    }

    private val accessToken = mapOf("access_token" to config.accessToken)

    private val restTemplate: RestTemplate = restTemplateBuilder
            .rootUri("http://data.gov.ru/api")
            .build()

    fun getData(): List<YearData> {
        val parsedResponse: Array<YearData>? = restTemplate.getForObject(URL_TEMPLATE, accessToken)
        return parsedResponse?.asList() ?: emptyList()
    }
}