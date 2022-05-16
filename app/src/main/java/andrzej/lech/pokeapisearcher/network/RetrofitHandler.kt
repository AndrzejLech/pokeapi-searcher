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

    fun getPokemonList(): Channel<ApiResponse> {
        val channel: Channel<ApiResponse> = Channel()
        CoroutineScope(Dispatchers.Main).launch {
            val result = retrofitInstance.getPokemonList()
            Log.d(TAG, result.body().toString())
            result.body()?.results?.forEach {
                it.name = it.name.replaceFirstChar { firstChar ->
                    if (firstChar.isLowerCase())
                        firstChar.titlecase(Locale.ROOT)
                    else firstChar.toString()
                }
            }
            result.body()?.let { channel.send(it) }
        }
        return channel
    }
}