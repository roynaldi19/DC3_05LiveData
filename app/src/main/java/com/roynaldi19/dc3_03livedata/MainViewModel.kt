package com.roynaldi19.dc3_03livedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roynaldi19.dc3_03livedata.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask


class MainViewModel : ViewModel() {
    private val initialTime = SystemClock.elapsedRealtime()
    private val elapsedTime = MutableLiveData<Long?>()

    companion object {
        private const val ONE_SECOND = 1000
    }

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - initialTime) / 1000
                elapsedTime.postValue(newValue)
            }

        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    fun getElapsedTime(): LiveData<Long?>{
        return elapsedTime
    }
}