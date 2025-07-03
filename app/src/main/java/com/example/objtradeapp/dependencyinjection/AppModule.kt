package com.example.objtradeapp.dependencyinjection

import com.example.objtradeapp.repository.AdsRepository
import com.example.objtradeapp.repository.ProfilsRepository
import com.example.objtradeapp.repository.UsersRepository
import com.example.objtradeapp.services.AdsApi
import com.example.objtradeapp.services.ProfilsApi
import com.example.objtradeapp.services.UsersApi
import com.example.objtradeapp.util.Constants.network.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Singleton
    @Provides
    fun provideUserRepository(api: UsersApi)= UsersRepository(api)
    @Singleton
    @Provides
    fun provideAdsRepository(api: AdsApi)= AdsRepository(api)

    @Singleton
    @Provides
    fun provideProfilsRepository(api: ProfilsApi)= ProfilsRepository(api)

    @Singleton
    @Provides
    fun provideUsersApi(): UsersApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(UsersApi::class.java)
    }
    @Singleton
    @Provides
    fun provideAdsApi(): AdsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(AdsApi::class.java)
    }
    @Singleton
    @Provides
    fun provideProfilsApi(): ProfilsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ProfilsApi::class.java)
    }


}