package andrzej.lech.pokeapisearcher.ui.main.adapters.pokemonAdapter

import andrzej.lech.pokeapisearcher.network.models.Pokemon

interface OnPokemonItemClickListener {
    fun onDataClick(pokemon: Pokemon)
}