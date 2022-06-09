package andrzej.lech.pokeapisearcher.network.models

import java.io.Serializable

data class Pokemon (
    var name: String,
    val url: String
) : Serializable