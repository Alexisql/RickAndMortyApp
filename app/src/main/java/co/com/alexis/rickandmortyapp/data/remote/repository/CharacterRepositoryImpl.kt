package co.com.alexis.rickandmortyapp.data.remote.repository

import co.com.alexis.rickandmortyapp.data.remote.dto.toDomain
import co.com.alexis.rickandmortyapp.data.remote.service.CharacterService
import co.com.alexis.rickandmortyapp.data.remote.util.safeApiCall
import co.com.alexis.rickandmortyapp.domain.model.Character
import co.com.alexis.rickandmortyapp.domain.repository.ICharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val CLAZZ = "CharacterRepositoryImpl"

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService,
    private val dispatcherIO: CoroutineDispatcher
) : ICharacterRepository {

    override suspend fun getCharacters(): Result<List<Character>> =
        withContext(dispatcherIO) {
            safeApiCall(CLAZZ) {
                val response = characterService.getCharacters()
                response.characters.map { it.toDomain() }
            }
        }

}
