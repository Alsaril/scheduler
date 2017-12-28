package com.alsaril.scheduler

import android.app.Activity
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.alsaril.scheduler.fragment.CustomFragment
import java.util.*
import kotlin.reflect.KProperty

val TASK_NAME_KEY = "task_name_key"

val RANDOM = Random()

fun Fragment.toast(@StringRes res: Int) {
    Toast.makeText(activity, res, Toast.LENGTH_SHORT).show()
}


fun Activity.toast(@StringRes res: Int) {
    Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
}

fun <T : View> CustomFragment.bindView(id: Int): Lazy<T, CustomFragment> {
    val delegate = Lazy<T, CustomFragment> { this.view?.findViewById(id)!! }
    addToDestroy(delegate)
    return delegate
}

class Lazy<out V, in T>(private val initializer: () -> V) : Disposable {
    private object EMPTY

    private var value: Any? = EMPTY

    operator fun getValue(thisRef: T, property: KProperty<*>): V {
        if (value == EMPTY) {
            value = initializer()
        }
        @Suppress("UNCHECKED_CAST")
        return value as V
    }

    override fun dispose() {
        value = EMPTY
    }
}

interface Disposable {
    fun dispose()
}