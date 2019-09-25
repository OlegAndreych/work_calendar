package org.andreych.workcalendar.storage.conf

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.andreych.workcalendar.storage.CalendarStorage
import org.andreych.workcalendar.storage.Migrator
import org.andreych.workcalendar.storage.StorageConfiguration
import org.postgresql.ds.PGSimpleDataSource
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties(StorageConfiguration::class)
class CalendarStorageConf {
    @Bean
    @DependsOn(value = ["migrator"])
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

    @Bean
    fun migrator(dataSource: DataSource): Migrator {
        return Migrator(dataSource)
    }
}