package org.andreych.workcalendar.storage.conf

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.andreych.workcalendar.storage.CalendarStorage
import org.andreych.workcalendar.storage.StorageConfiguration
import org.postgresql.ds.PGSimpleDataSource
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties(StorageConfiguration::class)
class CalendarStorageConf {
    @Bean
    fun calendarStorage(dataSource: DataSource): CalendarStorage = CalendarStorage(dataSource)

    @Bean
    fun dataSource(configuration: StorageConfiguration): DataSource {
        val pgDataSource = PGSimpleDataSource()

        with(configuration) {
            pgDataSource.setURL(url)
            pgDataSource.user = username
            pgDataSource.password = password
        }

        val config = HikariConfig()
        config.dataSource = pgDataSource

        return HikariDataSource(config)
    }
}