package co.com.alexis.rickandmortyapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName("results") val characters: List<CharacterDto>
)
