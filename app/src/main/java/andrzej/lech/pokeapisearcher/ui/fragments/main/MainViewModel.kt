package andrzej.lech.pokeapisearcher.ui.fragments.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import andrzej.lech.pokeapisearcher.MainActivity
import andrzej.lech.pokeapisearcher.network.RetrofitHandler
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.adapters.pokemonAdapter.OnPokemonItemClickListener
import andrzej.lech.pokeapisearcher.ui.adapters.pokemonAdapter.PokemonListAdapter
import andrzej.lech.pokeapisearcher.ui.navigator.Destinations
import andrzej.lech.pokeapisearcher.ui.navigator.Navigator
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application),
    OnPokemonItemClickListener {
    private val retrofitHandler = RetrofitHandler()
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList
    val navigator = Navigator()

    fun getPokemonList() {
        viewModelScope.launch {
            val pokemonList = retrofitHandler.getPokemonList()?.results ?: emptyList()
            _pokemonList.value = pokemonList
            Log.d("ViewModel", pokemonList.size.toString())
        }
    }

    fun navigateToDetails(activity: MainActivity) {
        navigator.navigateTo(Destinations.Details.screen, activity)
    }

    override fun onDataClick(pokemon: Pokemon) {

    }
}