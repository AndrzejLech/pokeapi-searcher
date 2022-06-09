package andrzej.lech.pokeapisearcher.network.models.pokemonDetails.move

import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.BaseModel

class VersionGroupDetails(
    val level_learned_at: Int,
    val move_learn_method: BaseModel,
    val version_group: BaseModel
)