package andrzej.lech.pokeapisearcher.network.models

data class ApiResponse(
    var count: Int,
    var next: String,
    var previous: String,
    var results: List<Pokemon>
)