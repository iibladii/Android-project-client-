package com.example.megroup9gmailcom.proekt

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_tovar.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class TovarListActivity : AppCompatActivity() {

    var th = this
    var idL = Array(2, { i -> (i).toString() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tovar_list)
        PersistantStorage.init(th)
        loadUsingOkHTTP("http://10.0.2.2:8080/torg/getti?product_name="+PersistantStorage.getProperty("tovarName")+"&category_name="+PersistantStorage.getProperty("category"))
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
                    val json = JSONArray(inputStream)
                    var a = json.length()
                    val range = 0..(a!!-1)
                    var data = Array(a!!, { i -> (i).toString() })
                    idL = Array(a!!, { i -> (i).toString() })
                    for (x in range) {
                        var jo = JSONObject(json.get(x).toString())
                        data[x] = " №" + x.toString() + " " + jo.getString("productName") + "  " + jo.getString("price")+" RUR"
                        idL[x] = jo.getString("id")
                    }
                    var gridview: GridView = findViewById(R.id.gridview)
                    /////Свойства списка////////////////////////////////////////////////////////////
                    gridview.adapter = ArrayAdapter(th, R.layout.item, R.id.tvText, data)

                    gridview.onItemClickListener =
                            AdapterView.OnItemClickListener {
                                parent, v, position, id ->Toast.makeText(th, "$position", Toast.LENGTH_SHORT).show()
                                PersistantStorage.addProperty("productNum", idL[position])
                                val intent = Intent(th, TovarActivity::class.java)
                                startActivity(intent)
                            }
                    ////////////////////////////////////////////////////////////////////////////////
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }
        })
    }

}
