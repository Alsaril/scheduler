package com.alsaril.scheduler.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.alsaril.scheduler.R
import com.alsaril.scheduler.db.Task
import com.alsaril.scheduler.db.TaskDAO
import com.alsaril.scheduler.toast
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotterknife.bindView

class NewTaskFragment : Fragment() {
    val taskName by bindView<EditText>(R.id.task_name)
    val ok by bindView<Button>(R.id.ok)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ok.setOnClickListener {
            save()
        }
    }

    private fun save() = launch(UI) {
        val name = taskName.text

        if (name.isBlank()) {
            toast(R.string.empty_fields)
            return@launch
        }

        TaskDAO.save(Task(0, name.toString())).await()
        name.clear()
    }
}