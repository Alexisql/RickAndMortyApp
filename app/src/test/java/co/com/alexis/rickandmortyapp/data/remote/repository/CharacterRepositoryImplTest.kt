package co.com.alexis.rickandmortyapp.data.remote.repository

import co.com.alexis.rickandmortyapp.MainDispatcherRule
import co.com.alexis.rickandmortyapp.data.remote.dto.CharacterDto
import co.com.alexis.rickandmortyapp.data.remote.dto.ResultDto
import co.com.alexis.rickandmortyapp.data.remote.service.CharacterService
import co.com.alexis.rickandmortyapp.data.remote.util.RickAndMortyException
import co.com.alexis.rickandmortyapp.domain.repository.ICharacterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: ICharacterRepository
    private val characterService: CharacterService = mockk()

    @Before
    fun initBefore() {
        repository = CharacterRepositoryImpl(characterService, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `getCharacters should return list of characters when service call is successful`() =
        runTest {
            // Given
            val mockList = ResultDto(
                characters = listOf(
                    CharacterDto(
                        id = 1,
                        name = "Rick Sanchez",
                        status = "Alive",
                        species = "Human",
                        type = "",
                        gender = "Male",
                        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        episode = listOf(),
                        created = "2017-11-04T18:48:46"
                    )
                )
            )
            coEvery { characterService.getCharacters() } returns mockList

            // When
            val response = repository.getCharacters()

            // Then
            Assert.assertTrue(response.isSuccess)
            Assert.assertEquals("Rick Sanchez", response.getOrNull()?.first()?.name)
        }

    @Test
    fun `getCharacters should throw exception when service call fails`() = runTest {
        // Given
        coEvery {
            characterService.getCharacters()
        } throws okio.IOException()

        // When
        val response = repository.getCharacters()

        // Then
        Assert.assertTrue(response.isFailure)
        Assert.assertTrue(response.exceptionOrNull() is RickAndMortyException.NetworkException)
    }

}