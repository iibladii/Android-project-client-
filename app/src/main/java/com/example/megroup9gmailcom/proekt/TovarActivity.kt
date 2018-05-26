package com.example.megroup9gmailcom.proekt

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.BitmapFactory
import android.widget.*
import com.example.megroup9gmailcom.proekt.R.id.imageView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class TovarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tovar)
        PersistantStorage.init(this)
        loadUsingOkHTTP("http://10.0.2.2:8080/torg/tovarInfo?productNumber="+PersistantStorage.getProperty("productNum"))
    }

    private val okClient by lazy {
        OkHttpClient()
    }
    private fun loadUsingOkHTTP(url: String) {
        val intent = Intent(this, SearchActivity::class.java)
        okClient.newCall(Request.Builder()
                .url(url)
                .build()).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                runOnUiThread {
                    val inputStream = response?.body()?.string()
                    val json = JSONObject(inputStream)
                    (findViewById(com.example.megroup9gmailcom.proekt.R.id.textView17) as TextView).setText(json.getString("product_name"))
                    (findViewById(com.example.megroup9gmailcom.proekt.R.id.textView15) as TextView).setText(json.getString("price"))
                    (findViewById(com.example.megroup9gmailcom.proekt.R.id.textView22) as TextView).setText(json.getString("telephome"))
                    (findViewById(com.example.megroup9gmailcom.proekt.R.id.textView19) as TextView).setText(json.getString("username"))
                    (findViewById(com.example.megroup9gmailcom.proekt.R.id.textView20) as TextView).setText(json.getString("email"))
                    (findViewById(com.example.megroup9gmailcom.proekt.R.id.textView27) as TextView).setText(json.getString("remark"))
                    val str = json.getString("picture")
                    if(!str.equals("")) {
                        loadImageUsingOkHTTP(str)
                    }
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }
        })
    }

    private fun loadImageUsingOkHTTP(bit: String) {
        okClient.newCall(Request.Builder()
                .url(bit)
                .build()).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val bitmap = BitmapFactory.decodeStream(response?.body()?.byteStream())
                runOnUiThread {
                    if(bitmap!=null) {
                        (findViewById(R.id.imageView) as ImageView).setImageBitmap(bitmap)
                        (findViewById(R.id.imageView) as ImageView).setImageBitmap(bitmap)
                        (findViewById(R.id.imageView) as ImageView).setImageBitmap(bitmap)
                    }
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }
        })
    }

}
