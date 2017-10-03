package org.andreych.russiancalendar.utils

import java.lang.management.ManagementFactory

object PidProvider {
    val pid = ManagementFactory.getRuntimeMXBean().name.split('@').first()
}