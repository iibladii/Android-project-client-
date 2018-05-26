package com.example.megroup9gmailcom.proekt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import okhttp3.*
import java.io.IOException

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        PersistantStorage.init(this)
    }

    fun Сreate(view: View) {
        var productName = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText10) as EditText).getText().toString()
        var login = PersistantStorage.getProperty("login")
        var categoryName = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText11) as EditText).getText().toString()
        var price = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText12) as EditText).getText().toString()
        var picture = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText13) as EditText).getText().toString()
        var remark = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText4) as EditText).getText().toString()
        if(!(findViewById(com.example.megroup9gmailcom.proekt.R.id.editText10) as EditText).getText().toString().equals("") &&
                !(findViewById(com.example.megroup9gmailcom.proekt.R.id.editText12) as EditText).getText().toString().equals("")
        )
        {
            loadUsingOkHTTP("http://10.0.2.2:8080/torg/setti?userLogin="+login+"&price="+price+"&picture="+picture+"&productName="+productName+"&categoryName="+categoryName+"&remark="+remark)
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        else
        {
            val toast = Toast.makeText(applicationContext,
                    "Для добавления нового товара требуется заполнить все поля помеченые звёздочкой",
                    Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }



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
                val txt = response?.body()?.string()

                runOnUiThread {
                    if(txt.equals("success")) {
                        val toast = Toast.makeText(applicationContext,
                                "Товар успешно создан",
                                Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()

                        startActivity(intent)
                    }
                    else
                    {
                        val toast = Toast.makeText(applicationContext,
                                "Товар небыл добавлен, проверьте корректность заполнения полей",
                                Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }
        })
    }

}
