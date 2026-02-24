package co.com.alexis.rickandmortyapp.domain.repository

import co.com.alexis.rickandmortyapp.domain.model.Character

interface ICharacterRepository {
    suspend fun getCharacters(): Result<List<Character>>
}