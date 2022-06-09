package andrzej.lech.pokeapisearcher.network.models.pokemonDetails.move

import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.BaseModel

class Move(
    val move: BaseModel,
    val version_group_details: List<VersionGroupDetails>
)