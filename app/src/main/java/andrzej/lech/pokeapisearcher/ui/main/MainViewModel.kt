package andrzej.lech.pokeapisearcher.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import andrzej.lech.pokeapisearcher.network.RetrofitHandler
import andrzej.lech.pokeapisearcher.network.models.ApiResponse
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.main.adapters.pokemonAdapter.OnPokemonItemClickListener
import andrzej.lech.pokeapisearcher.ui.main.adapters.pokemonAdapter.PokemonListAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel

class MainViewModel(application: Application) : AndroidViewModel(application), OnPokemonItemClickListener {
   private val retrofitHandler = RetrofitHandler()
    private lateinit var pokemonListAdapter: PokemonListAdapter

    fun getPokemonList(): Channel<ApiResponse> {
     return retrofitHandler.getPokemonList()
    }

    fun setDataToRecyclerView(pokemonList: List<Pokemon>): PokemonListAdapter{
        pokemonListAdapter = PokemonListAdapter(pokemonList)
        pokemonListAdapter.setItemClickListener(this)
        return pokemonListAdapter
    }

    override fun onDataClick(pokemon: Pokemon) {
    }
}