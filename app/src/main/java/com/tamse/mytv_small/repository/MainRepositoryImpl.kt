package com.tamse.mytv_small.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.tamse.mytv_small.data.api.ApiService
import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.data.model.ChannelDetail
import com.tamse.mytv_small.data.model.PlayUrl
import com.tamse.mytv_small.data.model.listChanneltoDatabaseChannel
import com.tamse.mytv_small.database.ChannelDao
import com.tamse.mytv_small.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(private val apiService: ApiService, private val channelDao: ChannelDao): MainRepository {
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

    override suspend fun insertChannels(channels: List<Channel>) = withContext(Dispatchers.IO) {
        channelDao.insertAll(listChanneltoDatabaseChannel(channels))
    }


    override val channels: LiveData<List<Channel>> = channelDao.getChannels().map {
        it.asDomainModel()
    }
}