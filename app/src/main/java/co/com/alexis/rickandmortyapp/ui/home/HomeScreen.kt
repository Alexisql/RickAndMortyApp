package co.com.alexis.rickandmortyapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.com.alexis.rickandmortyapp.domain.model.Character
import co.com.alexis.rickandmortyapp.ui.component.ErrorDialog
import co.com.alexis.rickandmortyapp.ui.component.ErrorHandler
import co.com.alexis.rickandmortyapp.ui.component.LocalErrorHandler
import co.com.alexis.rickandmortyapp.ui.component.SpacerComponent
import co.com.alexis.rickandmortyapp.ui.home.component.HomeSkeleton
import co.com.alexis.rickandmortyapp.ui.home.component.ItemCharacter
import co.com.alexis.rickandmortyapp.ui.util.ResultState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.uiState.collectAsStateWithLifecycle()
    val errorHandler = LocalErrorHandler.current

    HomeContent(
        state = state,
        errorHandler = errorHandler,
        onRetry = {
            homeViewModel.getCharacter()
        }
    )
}

@Composable
private fun HomeContent(
    state: ResultState<List<Character>>,
    errorHandler: ErrorHandler,
    onRetry: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when (state) {
                is ResultState.Loading -> {
                    HomeSkeleton(modifier = Modifier.padding(top = 16.dp))
                }

                is ResultState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(state.data) { item ->
                            SpacerComponent(10)
                            ItemCharacter(
                                character = item
                            )
                        }
                    }
                }

                is ResultState.Error -> {
                    errorHandler.showError(
                        ErrorDialog(
                            message = state.error,
                            onRetry = {
                                onRetry()
                            }
                        )
                    )
                }

                else -> {
                    Unit
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    HomeContent(
        state = ResultState.Loading,
        errorHandler = ErrorHandler(),
        onRetry = {}
    )
}