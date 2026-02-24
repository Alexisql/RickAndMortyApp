package co.com.alexis.rickandmortyapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.alexis.rickandmortyapp.domain.model.Character
import co.com.alexis.rickandmortyapp.domain.repository.ICharacterRepository
import co.com.alexis.rickandmortyapp.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: ICharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ResultState<List<Character>>>(ResultState.Idle)
    val uiState: StateFlow<ResultState<List<Character>>> = _uiState.asStateFlow()

    init {
        getCharacter()
    }

    fun getCharacter() {
        _uiState.value = ResultState.Loading
        viewModelScope.launch {
            characterRepository.getCharacters()
                .onSuccess {
                    _uiState.value = ResultState.Success(it)
                }
                .onFailure {
                    _uiState.value = ResultState.Error(it.message ?: "Error Unknown")
                }
        }
    }

}