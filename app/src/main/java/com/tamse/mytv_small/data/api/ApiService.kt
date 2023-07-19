package com.tamse.mytv_small.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.data.model.TopHeadLine
import com.tamse.mytv_small.data.model.TopHeadLineChannelDetail
import com.tamse.mytv_small.data.model.TopHeadLinePlay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton


//private const val BASE_URL = "https://emcb2cstage.mytv.vn/v8_emc/channel/"

//private const val BASE_URL = "https://emcb2cstage.mytv.vn/v8_emc/channel/"
//
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()
@Singleton
interface ApiService {

    @POST("list")
    suspend fun getTopHeadLine(): TopHeadLine

    @FormUrlEncoded
    @POST("detail")
    suspend fun getChannelDetail(@Field("channel_id") channelId: String): TopHeadLineChannelDetail
//    suspend fun getChannelDetail(@FieldMap params: HashMap<String, String>): TopHeadLine

    @FormUrlEncoded
    @POST("play")
    suspend fun getPlayUrl(@Field("channel_id") channelId: String, @Field("mf_code") mfCode: String) : TopHeadLinePlay
}


//object ChannelApi {
//    val retrofitService: ChannelApiService by lazy { retrofit.create(ChannelApiService::class.java) }
//}

