package andrzej.lech.pokeapisearcher.network

import android.util.Log
import andrzej.lech.pokeapisearcher.network.models.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.util.*

class RetrofitHandler {
    private val TAG = "RetrofitHandler"
    private val retrofitInstance = RetrofitHelper.getInstance().create(RetrofitService::class.java)

   suspend fun getPokemonList(): ApiResponse? {
            val result = retrofitInstance.getPokemonList()
            Log.d(TAG, result.body().toString())

            return result.body()
    }
}