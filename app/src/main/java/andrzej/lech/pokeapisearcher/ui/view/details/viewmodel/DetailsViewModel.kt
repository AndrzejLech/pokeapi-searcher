package andrzej.lech.pokeapisearcher.ui.view.details.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import andrzej.lech.pokeapisearcher.network.RetrofitHandler
import andrzej.lech.pokeapisearcher.network.models.PokemonDetails
import andrzej.lech.pokeapisearcher.ui.utils.Errors
import andrzej.lech.pokeapisearcher.ui.view.details.viewstate.DetailsViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "DetailsViewModel"
    private val retrofitHandler = RetrofitHandler(application.baseContext)

    private val _pokemonDetails: MutableLiveData<PokemonDetails> = MutableLiveData()
    val pokemonDetails: LiveData<PokemonDetails> = _pokemonDetails

    private val _viewState: MutableLiveData<DetailsViewState> = MutableLiveData()
    val viewState: LiveData<DetailsViewState> = _viewState

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getPokemonDetails(name: String) {
        viewModelScope.launch {
            try {
                val pokemonDetail = retrofitHandler.getPokemonDetails(name)

                _pokemonDetails.value = pokemonDetail
                _viewState.value = DetailsViewState.Details(pokemonDetail)
                Log.d(TAG, pokemonDetail.toString())

            } catch (exception: Exception) {
                when (exception) {
                    is CancellationException -> {
                        throw exception
                    }
                    is IOException -> {
                        _errorLiveData.value = Errors.NO_NETWORK_CONNECTION.message
                        _viewState.value = DetailsViewState.NoData
                    }
                    else -> {
                        _viewState.value = DetailsViewState.UnhandledError(exception)
                        Log.d(TAG, exception.toString())
                    }
                }
            }
        }
    }
}