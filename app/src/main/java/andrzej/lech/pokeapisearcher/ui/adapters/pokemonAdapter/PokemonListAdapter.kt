package andrzej.lech.pokeapisearcher.ui.adapters.pokemonAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import andrzej.lech.pokeapisearcher.R
import andrzej.lech.pokeapisearcher.network.models.Pokemon

class PokemonListAdapter(pokemonList: List<Pokemon>) :
    ListAdapter<Pokemon, PokemonListAdapter.PokemonViewHolder>(PokemonDiff()) {
    var pokemonList: List<Pokemon> = emptyList()
        set(value) {
            field = value
            submitList(pokemonList)
            Log.d("PokemonListAdapter", value.toString())
        }
    private lateinit var onPokemonItemClickListener: OnPokemonItemClickListener

    init {
        this.pokemonList = pokemonList
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_list, parent, false)
        return PokemonViewHolder(view, onPokemonItemClickListener)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.onBind(pokemonList[position])
    }

    fun setItemClickListener(onPokemonItemClickListener: OnPokemonItemClickListener) {
        this.onPokemonItemClickListener = onPokemonItemClickListener
    }

    inner class PokemonViewHolder(
        itemView: View,
        private val onPokemonItemClickListener: OnPokemonItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name: TextView = itemView.findViewById(R.id.item_name)

        init {
            val dataItem = itemView.findViewById<LinearLayout>(R.id.layout_horizontal)
            dataItem.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position: Int = layoutPosition
            val currentData = pokemonList[position]

            onPokemonItemClickListener.onDataClick(currentData)
        }

        fun onBind(pokemon: Pokemon) {
            name.text = pokemon.name
        }
    }
}

class PokemonDiff : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

}