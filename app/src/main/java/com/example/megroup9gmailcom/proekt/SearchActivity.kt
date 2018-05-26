package com.example.megroup9gmailcom.proekt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.EditText
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SearchActivity : AppCompatActivity() {
    var th = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //val intent = Intent(this, MainActivity::class.java)
        //startActivity(intent)
        PersistantStorage.init(this)
        //val a = PersistantStorage.getProperty("login")
        //val b = PersistantStorage.getProperty("name")
        //val c = PersistantStorage.getProperty("pass")
        //val d = PersistantStorage.getProperty("email")
    }


    fun searchStart(view: View) {
        PersistantStorage.addProperty("tovarName", (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText3) as EditText).getText().toString())
        PersistantStorage.addProperty("category", (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText6) as EditText).getText().toString())
        val intent = Intent(this, TovarListActivity::class.java)
        //val message = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText3) as EditText).getText().toString()
        //val message1 = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText3) as EditText).getText().toString()
        //val os = arrayOf(message, message1)
        //intent.putExtra(AlarmClock.EXTRA_MESSAGE, os)
        startActivity(intent)
    }

    fun createStart(view: View) {
        val intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }









/*
    private val okClient by lazy {
        OkHttpClient()
    }
    private fun loadImageUsingOkHTTP(url: String) {
        val intent = Intent(this, SearchActivity::class.java)
        okClient.newCall(Request.Builder()
                .url(url)
                .build()).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                runOnUiThread {
                    val inputStream = response?.body()?.string()
                    if(inputStream.equals("")){
                        PersistantStorage.addProperty("name", "error")
                        PersistantStorage.addProperty("pass", "error")
                        PersistantStorage.addProperty("login", "error")
                        PersistantStorage.addProperty("email", "error")
                        PersistantStorage.addProperty("pol", "error")
                        PersistantStorage.addProperty("telephone", "error")
                    }
                    else {
                        //imageView.setImageBitmap(bitmap)
                        //chronometer.stop()
                        //val responseData = response?.body()?.string()
                        val json = JSONObject(inputStream)
                        PersistantStorage.init(th)
                        PersistantStorage.addProperty("name", json.getString("name"))
                        PersistantStorage.addProperty("pass", json.getString("pass"))
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
    */
}
