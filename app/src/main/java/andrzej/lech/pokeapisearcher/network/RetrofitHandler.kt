package andrzej.lech.pokeapisearcher.network

import android.content.Context
import android.util.Log
import andrzej.lech.pokeapisearcher.network.models.ApiResponse
import andrzej.lech.pokeapisearcher.network.models.PokemonDetails
import andrzej.lech.pokeapisearcher.ui.utils.PokemonNameHandler
import java.io.IOException

class RetrofitHandler(context: Context) {
    private val TAG = "RetrofitHandler"
    private val retrofitInstance =
        RetrofitHelper.getInstance(context).create(RetrofitService::class.java)

    suspend fun getPokemonList(): ApiResponse? {
        Log.d(TAG, "Starting get...")
        val result = retrofitInstance.getPokemonList()
        val body = result.body() ?: throw IOException("empty body")
        if (result.isSuccessful) {
            return body
        } else throw IOException("response unsuccessful ${result.code()}")
    }

    suspend fun getPokemonDetails(name: String): PokemonDetails {

        val lowercaseName = PokemonNameHandler.lowercaseName(name)
        val url = "https://pokeapi.co/api/v2/pokemon/$lowercaseName"
        Log.d(TAG, url)
        val result = retrofitInstance.getPokemonDetails(url)
        Log.d(TAG, result.body().toString())
        val body: PokemonDetails = requireNotNull(result.body())
        body.name = name

        return body
    }
}