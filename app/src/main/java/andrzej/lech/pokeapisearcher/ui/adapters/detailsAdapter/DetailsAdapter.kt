package andrzej.lech.pokeapisearcher.ui.adapters.detailsAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import andrzej.lech.pokeapisearcher.R
import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.Stat

class DetailsAdapter : ListAdapter<Stat, DetailsAdapter.StatsViewHolder>(DetailsDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_details, parent, false)
        return StatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class StatsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ) {
        val name = itemView.findViewById<TextView>(R.id.item_title)
        val value = itemView.findViewById<TextView>(R.id.item_value)

        fun onBind(stat: Stat) {
            name.text = stat.stat.name
            value.text = stat.base_stat.toString()
        }
    }

}

class DetailsDiff: DiffUtil.ItemCallback<Stat>() {
    override fun areItemsTheSame(oldItem: Stat, newItem: Stat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Stat, newItem: Stat): Boolean {
        return oldItem == newItem
    }

}