package andrzej.lech.pokeapisearcher.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val baseUrl = "https://pokeapi.co/api/v2/"

    fun getInstance(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        ChuckerInterceptor.Builder(context)
                            .collector(ChuckerCollector(context))
                            .maxContentLength(250000L)
                            .redactHeaders(emptySet())
                            .alwaysReadResponseBody(false)
                            .build()
                    )
                    .build()
            )
            .build()
    }
}