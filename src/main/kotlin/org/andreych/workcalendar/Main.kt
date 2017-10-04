package org.andreych.workcalendar

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.module.kotlin.KotlinModule
import net.fortuna.ical4j.util.UidGenerator
import org.andreych.workcalendar.utils.PidProvider
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
class Main {
    @Bean
    fun jacksonKotlinModule(): Module {
        return KotlinModule()
    }

    @Bean
    fun taskScheduler(): TaskScheduler {
        return ThreadPoolTaskScheduler()
    }

    @Bean
    fun uidGenerator(): UidGenerator {
        return UidGenerator(PidProvider.pid)
    }
}

fun main(args: Array<String>) {
    val springApplication = SpringApplication(Main::class.java)
    springApplication.run()
}