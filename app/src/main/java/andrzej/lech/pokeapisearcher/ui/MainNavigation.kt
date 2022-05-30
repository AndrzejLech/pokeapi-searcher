package andrzej.lech.pokeapisearcher.ui

import andrzej.lech.pokeapisearcher.network.models.Pokemon

interface MainNavigation {
    fun navigateToDetail(pokemon: Pokemon)
    fun navigateToMain()
}