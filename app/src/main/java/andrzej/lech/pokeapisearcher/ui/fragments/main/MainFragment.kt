package andrzej.lech.pokeapisearcher.ui.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import andrzej.lech.pokeapisearcher.MainActivity
import andrzej.lech.pokeapisearcher.R
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.MainNavigation
import andrzej.lech.pokeapisearcher.ui.adapters.pokemonAdapter.OnPokemonItemClickListener
import andrzej.lech.pokeapisearcher.ui.adapters.pokemonAdapter.PokemonListAdapter

class MainFragment : Fragment() {
    private val TAG = "MainFragment"
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var pokemonList: List<Pokemon>
    private val pokemonListAdapter = PokemonListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        recyclerView = view.findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = linearLayoutManager

        pokemonListAdapter.setItemClickListener(object : OnPokemonItemClickListener {
            override fun onDataClick(pokemon: Pokemon) {
                navigateToPokemonDetail(pokemon)
            }
        })
        recyclerView.adapter = pokemonListAdapter

        mainViewModel.pokemonList.observe(
            viewLifecycleOwner
        ) { response ->
            pokemonListAdapter.pokemonList = response
            Log.d(TAG, response.toString())
        }

        if (savedInstanceState == null) {
            mainViewModel.getPokemonList()
        }
    }

    private fun navigateToPokemonDetail(pokemon: Pokemon) {
        val activity = activity

        if (activity is MainNavigation) {
            activity.navigateToDetail(pokemon)
        } else {
            Log.d(TAG, "This is not MainNavigation")
        }
    }
}