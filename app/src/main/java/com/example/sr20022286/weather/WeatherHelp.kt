package com.example.sr20022286.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    /*
     * Enter city in " https://developer.yahoo.com/weather " website.
     * Get endpoint url and paste in another tab to get query json file.
     * Paste query in " https://jsonformatter.curiousconcept.com/ " to see json file.
     *
     * Use endpoint's URL to set baseUrl and @GET - remove "q=..." till & sign.
     * Send city in getForecast
     */
    @GET("yql?format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    fun getForecast(@Query("q") cityName: String) : Call<Weather>

}

class Weather(val query: WeatherQuery)
class WeatherQuery(val results: WeatherResults)
class WeatherResults(val channel: WeatherChannel)
class WeatherChannel(val title: String, val  item: WeatherItem)
class WeatherItem(val forecast: List<Forecast>)
class Forecast(val date: String, val day: String, val high: String, val low: String, val text: String)


class WeatherRetriever{
    val service: WeatherAPI

    init {
        val  retrofit = Retrofit.Builder().baseUrl("https://query.yahooapis.com/v1/public/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(WeatherAPI::class.java)
    }

    fun getForecast(callback : Callback<Weather>, searchText: String){

        var searchCity = searchText
        if(searchCity == ""){
            searchCity = "Bangalore"
        }

        val city = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"$searchCity\")"
        val call = service.getForecast(city)
        call.enqueue(callback)

    }
}