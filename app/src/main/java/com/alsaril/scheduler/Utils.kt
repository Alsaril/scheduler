package com.alsaril.scheduler

import android.app.Activity
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast
import java.util.*

val TASK_NAME_KEY = "task_name_key"

val RANDOM = Random()

fun Fragment.toast(@StringRes res: Int) {
    Toast.makeText(activity, res, Toast.LENGTH_SHORT).show()
}


fun Activity.toast(@StringRes res: Int) {
    Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
}