package co.com.alexis.rickandmortyapp.data.remote.service

import co.com.alexis.rickandmortyapp.data.remote.dto.ResultDto
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): ResultDto

}