package com.teyyihan.mediumproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.webkit.*
import com.teyyihan.mediumproject.R
import androidx.appcompat.app.AppCompatActivity
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private val TAG = "Teooo mainactivity"
    private lateinit var socket : Socket


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         socket = IO.socket("http://192.168.1.2:3100");
         socket.connect()


        val jsonString = "{myID: '123123'}"
        try {
            val jsonData = JSONObject(jsonString)
            socket.emit("joinRoom", jsonData)
        } catch (e: JSONException) { }


        socket.on("changes", {
            it.forEach {
                Log.d(TAG, "onchange: "+it)
            }
        })


    }
}

