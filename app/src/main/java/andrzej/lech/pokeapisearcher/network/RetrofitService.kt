package andrzej.lech.pokeapisearcher.network

import andrzej.lech.pokeapisearcher.network.models.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RetrofitService {
    @GET("pokemon/")
    suspend fun getPokemonList(): Response<ApiResponse>
}