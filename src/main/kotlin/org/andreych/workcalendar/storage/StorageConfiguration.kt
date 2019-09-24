package org.andreych.workcalendar.storage

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jdbc.database")
class StorageConfiguration {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
}