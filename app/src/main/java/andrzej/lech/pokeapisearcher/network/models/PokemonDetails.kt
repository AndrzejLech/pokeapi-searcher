package andrzej.lech.pokeapisearcher.network.models

import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.Ability
import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.BaseModel
import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.Sprites
import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.Stat
import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.Type
import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.TypeDetails
import andrzej.lech.pokeapisearcher.network.models.pokemonDetails.move.Move
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PokemonDetails(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<BaseModel>,
    val game_indices: List<BaseModel>,
    val height: Int,
    val heldItems: List<BaseModel>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    var name: String,
    val order: Int,
    val past_types: List<Type>,
    val species: BaseModel,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<TypeDetails>,
    val weight: Int

): Serializable