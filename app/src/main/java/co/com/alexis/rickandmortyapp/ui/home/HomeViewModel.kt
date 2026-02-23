package co.com.alexis.rickandmortyapp.ui.home

import androidx.lifecycle.viewModelScope
import co.com.alexis.rickandmortyapp.domain.Character
import co.com.alexis.rickandmortyapp.ui.home.contract.HomeEffect
import co.com.alexis.rickandmortyapp.ui.util.BaseViewModel
import co.com.alexis.rickandmortyapp.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<ResultState<List<Character>>, HomeEffect>(ResultState.Idle) {

    init {
        getCharacter()
    }

    private fun getCharacter() {
        updateState(ResultState.Loading)
        viewModelScope.launch {
            delay(2000)
            updateState(
                ResultState.Success(
                    listOf(
                        Character(
                            id = 1,
                            name = "Rick Sanchez",
                            status = "Alive",
                            image = ""
                        )
                    )
                )
            )
        }
    }

}