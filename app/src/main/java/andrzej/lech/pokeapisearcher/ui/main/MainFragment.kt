package andrzej.lech.pokeapisearcher.ui.main

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
import andrzej.lech.pokeapisearcher.network.models.ApiResponse
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class MainFragment : Fragment() {
    private val TAG = "MainFragment"
    private lateinit var recyclerView: RecyclerView
    private lateinit var job: Job

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var pokemonList: List<Pokemon>

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

        job = CoroutineScope(Dispatchers.Main).launch {
            pokemonList = mainViewModel.getPokemonList().receive().results
            Log.d(TAG, mainViewModel.getPokemonList().receive().results.toString())
            recyclerView.adapter = mainViewModel.setDataToRecyclerView(pokemonList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }

}