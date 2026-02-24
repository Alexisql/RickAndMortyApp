package co.com.alexis.rickandmortyapp.data.remote.dto

import co.com.alexis.rickandmortyapp.domain.model.Character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id") var id: Int,
    @SerialName("name") val name: String,
    @SerialName("status") val status: String,
    @SerialName("species") val species: String,
    @SerialName("type") val type: String,
    @SerialName("gender") val gender: String,
    @SerialName("image") val image: String,
    @SerialName("episode") val episode: List<String>,
    @SerialName("created") val created: String,
)

fun CharacterDto.toDomain() =
    Character(id, name, status, image)
