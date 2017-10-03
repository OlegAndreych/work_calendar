package org.andreych.russiancalendar.utils

import org.junit.Assert.assertNotNull
import org.junit.Test

internal class PidProviderTest {

    @Test
    fun shouldProvidePid() {
        val pid = PidProvider.pid
        assertNotNull("Pid is null.", pid)
        pid.toLong()
    }
}