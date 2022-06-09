package andrzej.lech.pokeapisearcher.ui.view.main.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import andrzej.lech.pokeapisearcher.network.RetrofitHandler
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.utils.Errors
import andrzej.lech.pokeapisearcher.ui.utils.PokemonNameHandler
import andrzej.lech.pokeapisearcher.ui.view.main.viewstate.PokemonListViewState
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "ViewModel"
    private val retrofitHandler = RetrofitHandler(application.baseContext)

    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _viewState: MutableLiveData<PokemonListViewState> = MutableLiveData()
    val viewState: LiveData<PokemonListViewState> = _viewState

    fun getPokemonList() {
        viewModelScope.launch {
            try {
                _viewState.value = PokemonListViewState.Loading
                Log.d(TAG, "Current Thread: " + Thread.currentThread().name)
                val pokemonList = retrofitHandler.getPokemonList()?.results ?: emptyList()
                if (pokemonList.isEmpty()) {
                    _viewState.value = PokemonListViewState.NoData
                    _errorLiveData.value = Errors.EMPTY_LIST.message
                } else {
                    PokemonNameHandler.capitalizeNames(pokemonList)
                    _pokemonList.value = pokemonList
                    _viewState.value = PokemonListViewState.Items(pokemonList)
                    Log.d(TAG, pokemonList.size.toString())
                }
            } catch (exception: Exception) {
                if (exception is IOException) {
                    _viewState.value = PokemonListViewState.NoData
                    _errorLiveData.value = Errors.NO_NETWORK_CONNECTION.message
                } else {
                    _viewState.value = PokemonListViewState.UnhandledError(exception)
                }
            }
        }
    }
}