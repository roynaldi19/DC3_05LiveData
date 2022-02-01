package com.roynaldi19.bfaa05_02livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.roynaldi19.bfaa05_02livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var liveDataTimerViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        liveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        subscribe()
    }

    private fun subscribe() {
        val elapsedTimerObserver = Observer<Long?> { long ->
            val newText = this@MainActivity.resources.getString(R.string.seconds, long)
            activityMainBinding.timerTextview.text = newText
        }

        liveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimerObserver)
    }
}