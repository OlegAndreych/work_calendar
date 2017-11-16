package org.andreych.workcalendar.domain

import org.apache.commons.collections4.multimap.AbstractMultiValuedMap
import java.util.*

class EnumListMultivaluedMap<K : Enum<K>, V>(map: EnumMap<K, MutableCollection<V>>) : AbstractMultiValuedMap<K, V>(map) {

    companion object {
        inline fun <reified K : Enum<K>, reified V> newInstance(): EnumListMultivaluedMap<K, V> = EnumListMultivaluedMap(EnumMap(K::class.java))
    }

    override fun createCollection(): MutableCollection<V> = ArrayList()
}