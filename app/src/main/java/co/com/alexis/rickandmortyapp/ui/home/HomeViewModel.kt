package co.com.alexis.rickandmortyapp.ui.home

import androidx.lifecycle.viewModelScope
import co.com.alexis.rickandmortyapp.domain.model.Character
import co.com.alexis.rickandmortyapp.domain.repository.ICharacterRepository
import co.com.alexis.rickandmortyapp.ui.home.contract.HomeEffect
import co.com.alexis.rickandmortyapp.ui.util.BaseViewModel
import co.com.alexis.rickandmortyapp.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: ICharacterRepository
) : BaseViewModel<ResultState<List<Character>>, HomeEffect>(ResultState.Idle) {

    init {
        getCharacter()
    }

    private fun getCharacter() {
        updateState(ResultState.Loading)
        viewModelScope.launch {
            characterRepository.getCharacters()
                .onSuccess {
                    updateState(ResultState.Success(it))
                }
                .onFailure {
                    updateState(ResultState.Idle)
                    emitEffect(HomeEffect.ShowError(it.message ?: "Error"))
                }
        }
    }

}