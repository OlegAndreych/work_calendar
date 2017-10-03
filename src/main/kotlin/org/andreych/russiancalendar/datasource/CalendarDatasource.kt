package org.andreych.russiancalendar.datasource

import org.andreych.russiancalendar.datasource.model.YearData
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Component
data class Configuration(@Value("\${open.data.access_token}") val accessToken: String)

@Service
class CalendarDatasource(restTemplateBuilder: RestTemplateBuilder, private val config: Configuration) {

    private val restTemplate: RestTemplate = restTemplateBuilder
            .rootUri("http://data.gov.ru/api")
            .build()

    fun getData(): List<YearData> {
        return restTemplate.getForObject("/dataset/7708660670-proizvcalendar/version/20151123T183036/content/?access_token={access_token}", Array<YearData>::class.java, mutableMapOf("access_token" to config.accessToken))?.asList() ?: emptyList()
    }
}