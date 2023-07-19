package com.tamse.mytv_small.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamse.mytv_small.data.model.ChannelDetail
import com.tamse.mytv_small.data.model.PlayUrl
import com.tamse.mytv_small.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelDetailViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _play = MutableLiveData<PlayUrl>()
    val play: LiveData<PlayUrl> = _play

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
}