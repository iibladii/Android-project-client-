package com.example.megroup9gmailcom.proekt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import okhttp3.OkHttpClient
import okhttp3.Request
import android.graphics.BitmapFactory
import android.graphics.Point
import android.R
import android.view.Gravity
import android.widget.Toast
import android.os.SystemClock
import android.support.constraint.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import android.widget.EditText
import android.widget.RadioButton


class RegistrateActivity : AppCompatActivity() {
    //В случае ошибки пинганём сервер
    private val imageUrl = "http://10.0.2.2:8080/torg/ping"

    private val okRequest by lazy {
        Request.Builder()
                .url(imageUrl)
                .build()
    }

    private val okClient by lazy {
        OkHttpClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.megroup9gmailcom.proekt.R.layout.activity_registrate)
    }

    private fun loadImageUsingOkHTTP(url: String) {
        val intent = Intent(this, MainActivity::class.java)
        okClient.newCall(Request.Builder()
                .url(url)
                .build()).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val txt = response?.body()?.string()

                runOnUiThread {
                    if(txt.equals("success")) {
                        val toast = Toast.makeText(applicationContext,
                                "Учётная запись успешно создана!",
                                Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()

                        startActivity(intent)
                    }
                    else
                    {
                        val toast = Toast.makeText(applicationContext,
                                "Учётная запись не была создана",
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

    fun radio_button_click(view: View){
    }

    fun isRegistrate(view: View) {
        var name = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText4) as EditText).getText().toString()
        var login = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText5) as EditText).getText().toString()
        var email = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText7) as EditText).getText().toString()
        var telephone = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText8) as EditText).getText().toString()
        var pass = (findViewById(com.example.megroup9gmailcom.proekt.R.id.editText9) as EditText).getText().toString()
        var pol = "M"
        if((findViewById(com.example.megroup9gmailcom.proekt.R.id.red) as RadioButton).isChecked == false)
            pol = "G"
        if(!(findViewById(com.example.megroup9gmailcom.proekt.R.id.editText4) as EditText).getText().toString().equals("") &&
                !(findViewById(com.example.megroup9gmailcom.proekt.R.id.editText5) as EditText).getText().toString().equals("") &&
                !(findViewById(com.example.megroup9gmailcom.proekt.R.id.editText7) as EditText).getText().toString().equals("") &&
                !(findViewById(com.example.megroup9gmailcom.proekt.R.id.editText8) as EditText).getText().toString().equals("") &&
                !(findViewById(com.example.megroup9gmailcom.proekt.R.id.editText9) as EditText).getText().toString().equals("")
        )
        {
            PersistantStorage.init(this)
            PersistantStorage.addProperty("login", login)
            PersistantStorage.addProperty("pass", pass)
            loadImageUsingOkHTTP("http://10.0.2.2:8080/torg/createUser?name=" + name + "&login=" + login + "&pass=" + pass + "&email=" + email + "&pol=" + pol + "&telephone=" + telephone + "")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else
        {
            val toast = Toast.makeText(applicationContext,
                    "Создание учётной записи требует заполнения всех полей",
                    Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
//

    }

}
