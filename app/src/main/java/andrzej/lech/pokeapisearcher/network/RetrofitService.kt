package andrzej.lech.pokeapisearcher.network

import andrzej.lech.pokeapisearcher.network.models.ApiResponse
import andrzej.lech.pokeapisearcher.network.models.PokemonDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface RetrofitService {
    @GET("pokemon/")
    suspend fun getPokemonList(): Response<ApiResponse>

    @GET
    suspend fun getPokemonDetails(@Url url: String): Response<PokemonDetails>
}