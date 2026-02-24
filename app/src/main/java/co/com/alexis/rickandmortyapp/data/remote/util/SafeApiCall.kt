package co.com.alexis.rickandmortyapp.data.remote.util

import android.util.Log
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    className: String,
    call: suspend () -> T
): Result<T> {
    return try {
        Result.success(call())
    } catch (e: IOException) {
        Log.e(className, "Network error: ${e.message}")
        Result.failure(RickAndMortyException.NetworkException())
    } catch (e: HttpException) {
        Log.e(className, "Api error: ${e.message}")
        Result.failure(RickAndMortyException.ServiceException())
    } catch (e: Exception) {
        Log.e(className, "Unknown error: ${e.message}")
        Result.failure(RickAndMortyException.UnknownException())
    }
}