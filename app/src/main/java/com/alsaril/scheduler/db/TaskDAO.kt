package com.alsaril.scheduler.db

import android.content.ContentValues
import com.alsaril.scheduler.MyApp
import com.alsaril.scheduler.RANDOM
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

object TaskDAO {
    private val dbHelper = DBHelper(MyApp.instance)

    fun save(task: Task) = async {
        val db = dbHelper.writableDatabase

        val cv = ContentValues()
        cv.put("name", task.name)

        return@async Task(db.insert("task", null, cv), task.name)
    }

    fun load() = async {
        val db = dbHelper.readableDatabase

        val columns = arrayOf("id", "name")

        db.query("task", columns, null, null, null, null, null).use {
            val tasks = ArrayList<Task>()

            if (!it.moveToFirst()) {
                return@async tasks
            }

            do {
                tasks.add(Task(it.getLong(0), it.getString(1)))
            } while (it.moveToNext())

            return@async tasks
        }
    }

    fun random(): Deferred<Task?> = async {
        val list = load().await()
        return@async if (list.isEmpty()) null else list[RANDOM.nextInt(list.size)]
    }
}