package com.example.sr20022286.weather

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var forecast = findViewById<Button>(R.id.button)

        forecast.setOnClickListener {

            var tempList = Intent(getApplicationContext(),TemperatureActivity::class.java)

            val searchEditText = findViewById<EditText>(R.id.searchEditText)
            tempList.putExtra("searchText",searchEditText.text.toString())

            startActivity(tempList)
        }




    }
}
