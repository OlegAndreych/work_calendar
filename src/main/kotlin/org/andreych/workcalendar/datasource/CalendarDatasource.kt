package org.andreych.workcalendar.datasource

import org.andreych.workcalendar.datasource.model.YearData
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Component
@ConfigurationProperties("open.data")
class Configuration {
    lateinit var accessToken: String
}

@Service
class CalendarDatasource(restTemplateBuilder: RestTemplateBuilder, config: Configuration) {

    companion object {
        private val URL = "/dataset/7708660670-proizvcalendar/version/20151123T183036/content/?access_token={access_token}"
    }

    private val accessToken = mapOf("access_token" to config.accessToken)

    private val restTemplate: RestTemplate = restTemplateBuilder
            .rootUri("http://data.gov.ru/api")
            .build()

    fun getData(): List<YearData> {
        val parsedResponse: Array<YearData>? = restTemplate.getForObject(URL, accessToken)
        return parsedResponse?.asList() ?: emptyList()
    }
}