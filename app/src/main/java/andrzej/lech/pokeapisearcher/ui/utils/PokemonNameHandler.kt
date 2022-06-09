package andrzej.lech.pokeapisearcher.ui.utils

import andrzej.lech.pokeapisearcher.network.models.Pokemon
import java.util.*

class PokemonNameHandler {
    companion object {
        fun capitalizeNames(pokemonList: List<Pokemon>) {
            pokemonList.forEach { pokemon ->
                pokemon.name =
                    pokemon.name.replaceFirstChar {
                        if (it.isLowerCase())
                            it.titlecase(Locale.getDefault())
                        else it.toString()
                    }
            }
        }

        fun lowercaseName(name: String): String {
            return name.replaceFirstChar {
                if (!it.isLowerCase())
                    it.lowercase(Locale.getDefault())
                else it.toString()
            }
        }
    }
}