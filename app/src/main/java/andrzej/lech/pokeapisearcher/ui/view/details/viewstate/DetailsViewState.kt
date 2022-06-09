package andrzej.lech.pokeapisearcher.ui.view.details.viewstate

import andrzej.lech.pokeapisearcher.network.models.PokemonDetails

sealed class DetailsViewState {
    object Loading : DetailsViewState()

    data class Details(val details: PokemonDetails) : DetailsViewState()

    object NoData : DetailsViewState()

    data class UnhandledError(val cause: Throwable) : DetailsViewState()
}
