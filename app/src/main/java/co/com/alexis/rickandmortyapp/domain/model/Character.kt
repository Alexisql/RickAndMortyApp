package co.com.alexis.rickandmortyapp.domain.model

data class Character(
    val id: Int,
    var name: String,
    val status: String,
    val image: String,
)