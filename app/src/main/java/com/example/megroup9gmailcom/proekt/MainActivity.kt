package com.example.megroup9gmailcom.proekt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.provider.AlarmClock
import android.view.View
import android.widget.EditText
import android.R.id.edit
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import android.content.Context
import android.view.Gravity
import android.widget.Toast
import android.app.NotificationManager
import android.support.v4.app.NotificationCompat
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import okhttp3.*
import java.io.IOException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var th = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PersistantStorage.init(this)
    }

    fun sendMessage(view: View) {
    loadImageUsingOkHTTP("http://10.0.2.2:8080/torg/getUser?login=" + (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText) as EditText).getText().toString() + "&pass=" + (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText2) as EditText).getText().toString() + "")
    }

    private val okClient by lazy {
        OkHttpClient()
    }
    private fun loadImageUsingOkHTTP(url: String) {
        val intent = Intent(this, SearchActivity::class.java)
        okClient.newCall(Request.Builder()
                .url(url)
                .build()).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val inputStream = response?.body()?.string()
                runOnUiThread {
                    val json = JSONObject(inputStream)
                    if(json.getString("name").equals("error")){
                        PersistantStorage.addProperty("name", "error")
                        PersistantStorage.addProperty("login", "error")
                        PersistantStorage.addProperty("email", "error")
                        PersistantStorage.addProperty("pol", "error")
                        PersistantStorage.addProperty("telephone", "error")
                        val toast = Toast.makeText(applicationContext,
                                "Текущая комбинация логина и пароля не обнаружена",
                                Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                    else {
                        PersistantStorage.addProperty("name", json.getString("name"))
                        PersistantStorage.addProperty("login", json.getString("login"))
                        PersistantStorage.addProperty("email", json.getString("email"))
                        PersistantStorage.addProperty("pol", json.getString("pol"))
                        PersistantStorage.addProperty("telephone", json.getString("telephone"))
                        startActivity(intent)
                    }
                }
            }
            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }
        })
    }

    fun sendMessageRegistrate(view: View) {
        val intent = Intent(this, RegistrateActivity::class.java)
        startActivity(intent)
    }


}
