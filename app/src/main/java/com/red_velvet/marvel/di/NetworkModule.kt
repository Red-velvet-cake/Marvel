package com.red_velvet.marvel.di

import com.red_velvet.marvel.data.remote.AuthorizationInterceptor
import com.red_velvet.marvel.data.remote.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const
    val BASE_URL = "http://gateway.marvel.com/v1/public/"

    @Singleton
    @Provides
    fun provideOkHttpClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authorizationInterceptor).build()

    }

    @Singleton
    @Provides
    fun provideMarvelService(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): MarvelService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(MarvelService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}





