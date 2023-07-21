package com.tamse.mytv_small.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.data.model.ChannelDetail
import com.tamse.mytv_small.data.model.EncryptUrlModel
import com.tamse.mytv_small.data.model.PlayUrl
import com.tamse.mytv_small.repository.MainRepository
import com.tamse.mytv_small.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _channels = MutableLiveData<List<Channel>>()
    private val _status = MutableLiveData<Status>()

    private  val _channelDetail = MutableLiveData<ChannelDetail>()

    private val _play = MutableLiveData<PlayUrl?>()
    val play: LiveData<PlayUrl?> = _play


    private val _channel = MutableLiveData<Channel>()

    val channel: LiveData<Channel> = _channel


    val channels: LiveData<List<Channel>> = _channels


    val status: LiveData<Status> = _status

    val channelDetail: LiveData<ChannelDetail> = _channelDetail


    fun fetchChannels() {
        viewModelScope.launch {
            mainRepository.getTopHeadLine().catch { e ->
                e.printStackTrace()
                _channels.value = listOf()
                _status.value = Status.ERROR
            }.collect {
                _channels.value = it
                _status.value = Status.SUCCESS


            }

//
//                _channels.value = topHeadLine.channels
        }
    }
    fun setNullForPlay() {
        _play.value = null
    }
    fun fetchPlayUrl(channelDetail: ChannelDetail) {
        viewModelScope.launch {
                channelDetail.channelId?.let { channelId -> channelDetail.mfCode?.let { mfCode ->
                    mainRepository.getTopHeadLinePlay(channelId,
                        mfCode
                    ).catch {
                        e -> e.printStackTrace()
                    }.collect {
                        _play.value = it
                    }
                }
            }
        }
    }
    fun fetchChannelClicked(channel: Channel) {
        viewModelScope.launch {
            channel.contentID?.let { contentID ->
                mainRepository.getTopHeadLineChannelDetail(contentID)
                    .catch {
                            e -> e.printStackTrace()
                    }.collect {
                        _channelDetail.value = it
                        _play.value = null
                        fetchPlayUrl(it)
                    }
            }
        }
    }
}