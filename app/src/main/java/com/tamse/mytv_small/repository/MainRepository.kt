package com.tamse.mytv_small.repository

import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.data.model.ChannelDetail
import com.tamse.mytv_small.data.model.PlayUrl
import com.tamse.mytv_small.data.model.TopHeadLineChannelDetail
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getTopHeadLine(): Flow<List<Channel>>

    suspend fun getTopHeadLineChannelDetail(channelId: String): Flow<ChannelDetail>

    suspend fun getTopHeadLinePlay(channelId: String, mfCode: String): Flow<PlayUrl>
}