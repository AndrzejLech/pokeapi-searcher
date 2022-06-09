package andrzej.lech.pokeapisearcher.ui.view.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import andrzej.lech.pokeapisearcher.MainActivity
import andrzej.lech.pokeapisearcher.R
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.MainNavigation
import andrzej.lech.pokeapisearcher.ui.adapters.detailsAdapter.DetailsAdapter
import andrzej.lech.pokeapisearcher.ui.utils.TypeHandler
import andrzej.lech.pokeapisearcher.ui.utils.UnitHandler
import andrzej.lech.pokeapisearcher.ui.view.details.viewmodel.DetailsViewModel
import andrzej.lech.pokeapisearcher.ui.view.details.viewstate.DetailsViewState
import coil.load

class DetailsFragment : Fragment() {
    private val TAG = "DetailsFragment"
    private lateinit var viewModel: DetailsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var statsAdapter: DetailsAdapter
    private lateinit var typeHandler: TypeHandler
    private lateinit var unitHandler: UnitHandler

    companion object {
        fun newInstance(pokemon: Pokemon): DetailsFragment {
            val bundle = bundleOf("data" to pokemon)
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemon: Pokemon = arguments?.get("data") as Pokemon
        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        statsAdapter = DetailsAdapter()
        typeHandler = TypeHandler()
        unitHandler = UnitHandler()

        val name = view.findViewById<TextView>(R.id.name)
        val order = view.findViewById<TextView>(R.id.order)
        val type1 = view.findViewById<TextView>(R.id.type_1)
        val type2 = view.findViewById<TextView>(R.id.type_2)
        val weight = view.findViewById<TextView>(R.id.weight_value)
        val height = view.findViewById<TextView>(R.id.height_value)
        val sprite = view.findViewById<ImageView>(R.id.sprite)
        val retryButton = view.findViewById<Button>(R.id.rerty_button)

        val detailContainer = view.findViewById<CardView>(R.id.details_container)
        val noData = view.findViewById<LinearLayout>(R.id.no_pokes_container)
        val unhandledError = view.findViewById<LinearLayout>(R.id.unhandled_error_container)

        Log.d(TAG, pokemon.name)
        viewModel.getPokemonDetails(pokemon.name)
        recyclerView = view.findViewById(R.id.stats_container)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = statsAdapter



        viewModel.pokemonDetails.observe(viewLifecycleOwner) {
            val notNullContext = requireNotNull(context)
            statsAdapter.submitList(it.stats)

            name.text = it.name
            order.text = String.format(getString(R.string.order), it.order)

            val type1Name = it.types[0].type.name
            type1.text = type1Name
            type1.background = typeHandler.decideTypeDrawable(notNullContext, type1Name)

            if (it.types.size >= 2) {
                val type2Name = it.types[1].type.name
                type2.text = type2Name
                type2.background = typeHandler.decideTypeDrawable(notNullContext, type2Name)
            }

            sprite.load(it.sprites.front_default) {
                crossfade(true)
            }

            weight.text = unitHandler.makeKilograms(it.weight.toFloat())
            height.text = unitHandler.makeMeters(it.height.toFloat())
        }

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is DetailsViewState.Details -> {
                    detailContainer.visibility = View.VISIBLE
                    noData.visibility = View.INVISIBLE
                    unhandledError.visibility = View.INVISIBLE
                }
                is DetailsViewState.Loading -> {
                    detailContainer.visibility = View.INVISIBLE
                    noData.visibility = View.INVISIBLE
                    unhandledError.visibility = View.INVISIBLE
                }
                is DetailsViewState.NoData -> {
                    detailContainer.visibility = View.INVISIBLE
                    noData.visibility = View.VISIBLE
                    unhandledError.visibility = View.INVISIBLE
                }
                is DetailsViewState.UnhandledError -> {
                    detailContainer.visibility = View.INVISIBLE
                    noData.visibility = View.INVISIBLE
                    unhandledError.visibility = View.VISIBLE
                }
            }
        }

        retryButton.setOnClickListener{
            viewModel.getPokemonDetails(pokemon.name)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val activity = activity

                    if (activity is MainNavigation) {
                        activity.navigateToMain()
                    } else {
                        Log.d(TAG, "This is not MainNavigation")
                    }
                }
            }
        )
    }

}