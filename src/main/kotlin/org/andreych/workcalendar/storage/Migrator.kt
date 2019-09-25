package org.andreych.workcalendar.storage

import org.flywaydb.core.Flyway
import javax.annotation.PostConstruct
import javax.sql.DataSource

class Migrator(private val dataSource: DataSource) {

    @PostConstruct
    fun migrate() {
        val flyway = Flyway.configure().dataSource(dataSource).load()
        flyway.migrate()
    }
}