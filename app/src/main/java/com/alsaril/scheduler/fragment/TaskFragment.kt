package com.alsaril.scheduler.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.alsaril.scheduler.R
import com.alsaril.scheduler.activity.MainActivity
import com.alsaril.scheduler.db.TaskDAO
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotterknife.bindView

class TaskFragment : Fragment() {

    val taskName by bindView<TextView>(R.id.task_name)
    val new by bindView<Button>(R.id.new_task)
    val add by bindView<Button>(R.id.add_task)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        new.setOnClickListener {
            update()
        }

        add.setOnClickListener {
            (activity as MainActivity).add()
        }

        update()
    }

    fun update() = launch(UI) {
        taskName.text = TaskDAO.random().await()?.name ?: getString(R.string.no_tasks)
    }
}