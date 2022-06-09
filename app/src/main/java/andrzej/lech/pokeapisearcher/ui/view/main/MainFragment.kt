package andrzej.lech.pokeapisearcher.ui.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import andrzej.lech.pokeapisearcher.MainActivity
import andrzej.lech.pokeapisearcher.R
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.MainNavigation
import andrzej.lech.pokeapisearcher.ui.adapters.pokemonAdapter.OnPokemonItemClickListener
import andrzej.lech.pokeapisearcher.ui.adapters.pokemonAdapter.PokemonListAdapter
import andrzej.lech.pokeapisearcher.ui.view.main.viewmodel.MainViewModel
import andrzej.lech.pokeapisearcher.ui.view.main.viewstate.PokemonListViewState

class MainFragment : Fragment() {
    private val TAG = "MainFragment"
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val pokemonListAdapter = PokemonListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loading = view.findViewById<ProgressBar>(R.id.progress_bar)
        val noPokemon = view.findViewById<LinearLayout>(R.id.no_pokes_container)
        val unhandledError = view.findViewById<LinearLayout>(R.id.unhandled_error_container)
        val retryButton = view.findViewById<Button>(R.id.rerty_button)
        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        recyclerView = view.findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = linearLayoutManager

        pokemonListAdapter.setItemClickListener(object : OnPokemonItemClickListener {
            override fun onDataClick(pokemon: Pokemon) {
                navigateToPokemonDetail(pokemon)
            }
        })

        recyclerView.adapter = pokemonListAdapter

        viewModel.pokemonList.observe(
            viewLifecycleOwner
        ) { response ->
            pokemonListAdapter.pokemonList = response
            Log.d(TAG, response.toString())
        }

        viewModel.errorLiveData.observe(
            viewLifecycleOwner
        ) { errorMessage ->
            if (errorMessage.isNotBlank()) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is PokemonListViewState.Loading -> {
                    loading.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                    noPokemon.visibility = View.INVISIBLE
                    unhandledError.visibility = View.INVISIBLE
                }
                is PokemonListViewState.Items -> {
                    loading.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                    noPokemon.visibility = View.INVISIBLE
                    unhandledError.visibility = View.INVISIBLE
                }
                is PokemonListViewState.NoData -> {
                    loading.visibility = View.INVISIBLE
                    recyclerView.visibility = View.INVISIBLE
                    noPokemon.visibility = View.VISIBLE
                    unhandledError.visibility = View.INVISIBLE
                }
                is PokemonListViewState.UnhandledError -> {
                    loading.visibility = View.INVISIBLE
                    recyclerView.visibility = View.INVISIBLE
                    noPokemon.visibility = View.INVISIBLE
                    unhandledError.visibility = View.VISIBLE
                }
                else -> {}
            }
        }

        retryButton.setOnClickListener {
            viewModel.getPokemonList()
        }


        Log.d(TAG, "savedInstanceState: $savedInstanceState")
        if (savedInstanceState == null) {
            viewModel.getPokemonList()
            Log.d(TAG,"viewState: ${viewModel.viewState.value}")
            Log.d(TAG,"errorLiveData: ${viewModel.errorLiveData.value}")
            Log.d(TAG,"pokemonList: ${viewModel.pokemonList.value}")
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