package co.com.alexis.rickandmortyapp.ui.home.contract

sealed interface HomeEffect {
    data class ShowError(val message: String) : HomeEffect
}