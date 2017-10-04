package org.andreych.workcalendar.utils

import java.lang.management.ManagementFactory

object PidProvider {
    val pid = ManagementFactory.getRuntimeMXBean().name.split('@').first()
}