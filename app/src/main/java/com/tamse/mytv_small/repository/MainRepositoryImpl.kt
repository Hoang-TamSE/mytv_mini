package com.tamse.mytv_small.repository

import com.tamse.mytv_small.data.api.ApiService
import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.data.model.ChannelDetail
import com.tamse.mytv_small.data.model.PlayUrl
import com.tamse.mytv_small.data.model.TopHeadLineChannelDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService): MainRepository {
    override suspend fun getTopHeadLine(): Flow<List<Channel>> {
        return flow {
            emit(apiService.getTopHeadLine())
        }.map {
            it.channels
        }
    }

    override suspend fun getTopHeadLineChannelDetail(channelId: String): Flow<ChannelDetail> {
        return flow {
            emit(apiService.getChannelDetail(channelId))
        }.map {
            it.data!!
        }
    }

    override suspend fun getTopHeadLinePlay(channelId: String, mfCode: String): Flow<PlayUrl> {
        return flow {
            emit(apiService.getPlayUrl(channelId, mfCode))
        }.map {
            it.data!!
        }
    }
}