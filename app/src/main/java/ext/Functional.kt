package navanvi.edm.app.ext

import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


public inline fun <T> observable(initialValue: T, crossinline onChange: (newValue: T) -> Unit):
        ReadWriteProperty<Any?, T> =
    object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) =
            onChange(newValue)
    }
