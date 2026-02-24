package co.com.alexis.rickandmortyapp.data.remote.di

import co.com.alexis.rickandmortyapp.BuildConfig.BASE_URL
import co.com.alexis.rickandmortyapp.data.remote.repository.CharacterRepositoryImpl
import co.com.alexis.rickandmortyapp.data.remote.service.CharacterService
import co.com.alexis.rickandmortyapp.domain.repository.ICharacterRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Singleton
    @Provides
    fun interceptorOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun characterService(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    @Singleton
    @Provides
    fun providerCharacterRepository(
        characterService: CharacterService,
        dispatcherIO: CoroutineDispatcher
    ): ICharacterRepository = CharacterRepositoryImpl(
        characterService = characterService,
        dispatcherIO = dispatcherIO
    )
}