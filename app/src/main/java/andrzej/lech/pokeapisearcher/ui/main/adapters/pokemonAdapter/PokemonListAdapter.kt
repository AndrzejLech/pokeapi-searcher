package andrzej.lech.pokeapisearcher.ui.main.adapters.pokemonAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import andrzej.lech.pokeapisearcher.R
import andrzej.lech.pokeapisearcher.network.models.Pokemon

class PokemonListAdapter(pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonListAdapter.AdapterViewHolder>() {
    var pokemonList: List<Pokemon> = emptyList()
    private lateinit var onPokemonItemClickListener: OnPokemonItemClickListener

    init {
        this.pokemonList = pokemonList
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListAdapter.AdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return AdapterViewHolder(view, onPokemonItemClickListener)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setItemClickListener(onPokemonItemClickListener: OnPokemonItemClickListener){
        this.onPokemonItemClickListener = onPokemonItemClickListener
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val singlePokemon: Pokemon = pokemonList[position]
        holder.setupName(singlePokemon.name)
    }

    inner class AdapterViewHolder(
        @NonNull itemView: View,
        private var onPokemonItemClickListener: OnPokemonItemClickListener
    ): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val name: TextView = itemView.findViewById(R.id.item_name)

        init {
            val dataItem = itemView.findViewById<LinearLayout>(R.id.layout_horizontal)
            dataItem.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position: Int = layoutPosition
            val currentData = pokemonList[position]

            onPokemonItemClickListener.onDataClick(currentData)
        }

        internal fun setupName(name: String){
            this.name.text = name
        }
    }
}