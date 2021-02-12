package org.andreych.workcalendar

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.andreych.workcalendar.datasource.conf.CalendarDatasourceConf
import org.andreych.workcalendar.restservice.conf.RestServiceConfiguration
import org.andreych.workcalendar.updater.conf.CalendarDataUpdaterConf
import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@EnableScheduling
@SpringBootConfiguration
@EnableAutoConfiguration(exclude = [FlywayAutoConfiguration::class])
@Import(CalendarDatasourceConf::class, RestServiceConfiguration::class, CalendarDataUpdaterConf::class)
class Main {
    @Bean
    fun jacksonKotlinModule(): Module {
        return KotlinModule()
    }

    @Bean
    fun taskScheduler(): TaskScheduler {
        return ThreadPoolTaskScheduler()
    }
}

fun main() {
    val springApplication = SpringApplication(Main::class.java)
    springApplication.run()
}