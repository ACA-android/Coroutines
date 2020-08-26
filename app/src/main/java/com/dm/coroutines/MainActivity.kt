package com.dm.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // simple coroutine + suspend functions
//        Log.d(TAG, "starting coroutine")
//        GlobalScope.launch(Dispatchers.IO) {
//            val result = networkCall1()
//            Log.d(TAG, "finished job on ${Thread.currentThread()}")
//            Log.d(TAG, "result = $result")
//            withContext(Dispatchers.Main) {
//                Log.d(TAG, "setting text on ${Thread.currentThread()}")
//                textView.text = result
//            }
//        }

        // job

//        var job: Job? = null
//
//        start.setOnClickListener {
//            Log.d(TAG, "starting job")
//            job = GlobalScope.launch(Dispatchers.IO) {
//                while (isActive) {
//                    Log.d(TAG, "running...")
//                }
//                Log.d(TAG, "finished job on ${Thread.currentThread()}")
//            }
//
//            GlobalScope.launch(Dispatchers.Main) {
//                Log.d(TAG, "starting second coroutine and waiting")
//                job?.join()
//                textView.text = "canceled"
//                Toast.makeText(this@MainActivity, "call finished", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        cancel.setOnClickListener {
//            job?.cancel()
//            Log.d(TAG, "job canceled")
//        }

        // timeout
//        val job = GlobalScope.launch {
//            withTimeout(5000) {
//                Log.d(TAG, networkCall1())
//                Log.d(TAG, networkCall2())
//            }
//        }
//
//        GlobalScope.launch(Dispatchers.Main) {
//            Log.d(TAG, "starting second coroutine and waiting")
//            val time = measureTimeMillis {
//                job.join()
//            }
//            Toast.makeText(this@MainActivity, "call finished in $time ms", Toast.LENGTH_SHORT).show()
//        }

        // async and await
//        val job = GlobalScope.launch {
//
//            val result1: Deferred<String> = async { networkCall1() }
//            val result2: Deferred<String> = async { networkCall2() }
//
//            Log.d(TAG, result1.await())
//            Log.d(TAG, result2.await())
//        }
//
//        GlobalScope.launch(Dispatchers.Main) {
//            Log.d(TAG, "starting second coroutine and waiting")
//            val time = measureTimeMillis {
//                job.join()
//            }
//            Toast.makeText(this@MainActivity, "call finished in $time ms", Toast.LENGTH_SHORT).show()
//        }


//        val result1 = GlobalScope.async { networkCall1() }
//        val result2 = GlobalScope.async { networkCall2() }
//
//        GlobalScope.launch(Dispatchers.Main) {
//            val time = measureTimeMillis {
//                Log.d(TAG, result2.await())
//                Log.d(TAG, result1.await())
//            }
//            Toast.makeText(this@MainActivity, "call finished in $time ms", Toast.LENGTH_SHORT).show()
//        }


        // lifecycleScope

        lifecycleScope.launch(Dispatchers.Default) {
            var step = 0L
            while (isActive) {
                delay(100)
                Log.d(TAG, "running... ${step++}")
            }
        }

        changeBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
            finish()
        }


    }

    suspend fun networkCall1(): String {
        delay(3000)
        return "hello from network 1"
    }

    suspend fun networkCall2(): String {
        delay(4000)
        return "hello from network 2"
    }


    fun fib(n: Long): Long = if (n < 2) n else fib(n - 1) + fib(n - 2)
}