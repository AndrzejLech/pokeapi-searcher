package andrzej.lech.pokeapisearcher.ui.view.main.viewstate

import andrzej.lech.pokeapisearcher.network.models.Pokemon

sealed class PokemonListViewState{
    object Loading : PokemonListViewState()

    data class Items(val pokes: List<Pokemon>) : PokemonListViewState()

    object NoData : PokemonListViewState()

    data class UnhandledError(val cause: Throwable) : PokemonListViewState()
}
