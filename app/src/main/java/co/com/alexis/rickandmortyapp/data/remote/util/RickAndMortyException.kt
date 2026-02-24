package co.com.alexis.rickandmortyapp.data.remote.util

sealed class RickAndMortyException() : Exception() {
    class NetworkException() : RickAndMortyException() {
        override val message: String =
            "You don't have an internet connection, please check your connection and try again."
    }

    class ServiceException() : RickAndMortyException() {
        override val message: String = "We're having server issues, please try again later."
    }

    class UnknownException() : RickAndMortyException() {
        override val message: String = "Unknown error, please contact support"
    }
}