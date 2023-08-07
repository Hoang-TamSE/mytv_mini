package com.tamse.mytv_small.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tamse.mytv_small.data.api.ApiService
import com.tamse.mytv_small.database.ChannelDao
import com.tamse.mytv_small.database.ChannelsDatabase
import com.tamse.mytv_small.repository.MainRepository
import com.tamse.mytv_small.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://emcb2cstage.mytv.vn/v8_emc/channel/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesMainRepository(apiService: ApiService, channelDao: ChannelDao): MainRepository =
        MainRepositoryImpl(apiService, channelDao)


    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(app,
        ChannelsDatabase::class.java,
        "database_channel")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideTopHead(channelDatabase: ChannelsDatabase): ChannelDao = channelDatabase.channelDao()
}