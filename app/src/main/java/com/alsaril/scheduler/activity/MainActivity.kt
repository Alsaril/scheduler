package com.alsaril.scheduler.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.alsaril.scheduler.R
import com.alsaril.scheduler.fragment.NewTaskFragment
import com.alsaril.scheduler.fragment.TaskFragment

class MainActivity : AppCompatActivity() {

    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(TaskFragment())
    }

    private fun changeFragment(fragment: Fragment, bundle: Bundle? = null) {
        this.fragment = fragment
        bundle?.let { fragment.arguments = it }

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun add() {
        changeFragment(NewTaskFragment())
    }
}
