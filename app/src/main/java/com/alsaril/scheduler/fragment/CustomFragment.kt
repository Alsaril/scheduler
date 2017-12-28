package com.alsaril.scheduler.fragment

import android.support.v4.app.Fragment
import com.alsaril.scheduler.Disposable
import com.alsaril.scheduler.Lazy

abstract class CustomFragment : Fragment() {

    private val disposable = mutableListOf<Disposable>()

    override fun onDestroyView() {
        super.onDestroyView()

        for (d in disposable) {
            d.dispose()
        }
        disposable.clear()
    }

    fun <T> addToDestroy(delegate: Lazy<T, CustomFragment>) {
        disposable.add(delegate)
    }
}