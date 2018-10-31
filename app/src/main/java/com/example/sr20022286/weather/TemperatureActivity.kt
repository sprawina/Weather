package com.example.sr20022286.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_temperature.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TemperatureActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)


        rv_tempListView.layoutManager = LinearLayoutManager(this)


        var  retriever = WeatherRetriever()

        val  callback = object : Callback<Weather>{

            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println("Working")

                title = response?.body()?.query?.results?.channel?.title    //set title on app bar

                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast

                var forecastStrings = mutableListOf<String>()

                if(forecasts!=null){
                    for(forecast in forecasts){
                        val str = "${forecast.date} - High:${forecast.high} Low:${forecast.low} - ${forecast.text}"
                        forecastStrings.add(str)
                    }
                }

                rv_tempListView.adapter = TemperatureAdapter(forecastStrings, this@TemperatureActivity)

            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("Not Working")
            }


        }

        val searchText = intent.extras.getString("searchText")  //get text entered in EditText from MainActivity

        retriever.getForecast(callback, searchText)




    }
}
